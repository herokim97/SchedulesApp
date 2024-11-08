package org.example.schedulesapp.controller;


import org.example.schedulesapp.dto.SchedulesRequestDto;
import org.example.schedulesapp.dto.SchedulesResponseDto;
import org.example.schedulesapp.entity.Schedules;
import org.example.schedulesapp.service.SchedulesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedules")
public class SchedulesController {
    // as-is
//    private final Map<Long, Schedules> scheduelsList = new HashMap<>();
    // to-be
    private final SchedulesService schedulesService;

    public SchedulesController(SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }
    //생성자 주입


    //PostMapping 생성
    @PostMapping
    public ResponseEntity<SchedulesResponseDto> createSchedules(@RequestBody SchedulesRequestDto dto) {
        /*------
        as-is)기존 자료 구조에 식별자를 증가시키던 로직
//        Long schedulesId = schedulesList.isEmpty() ? 1 : Collections.max(schedulesList.keySet()) + 1;


//        Schedules schedules = new Schedules(schedulesId, dto.getTitle(), dto.getWork(),dto.getUserId(),dto.getPassWord(),dto.getDate());

//        schedulesList.put(schedulesId, schedules);
//        return new ResponseEntity<>(new SchedulesResponseDto(schedules), HttpStatus.CREATED);
        to-be) SchedulesRepository로 전달
        --------*/
        return new ResponseEntity<>(schedulesService.saveSchedules(dto), HttpStatus.OK);

    }

    //전체 조회
    @GetMapping
    public List<SchedulesResponseDto> findAllSchedules(@RequestParam(required = false) String userId, @RequestParam(required = false) String updatedAt) {

        return schedulesService.findAllSchedules(userId, updatedAt);
    }

    //단건 조회 기능
    @GetMapping("/{id}")
    public ResponseEntity<SchedulesResponseDto> findScheduleById(@PathVariable Long id) {

        return new ResponseEntity<>(schedulesService.findScheduleById(id), HttpStatus.OK);
    }

    //전체 수정
    @PutMapping("/{id}")
    public ResponseEntity<SchedulesResponseDto> updatedSchedules(
            @PathVariable Long id,
            @RequestBody SchedulesRequestDto dto) {
        return new ResponseEntity<>(schedulesService.updateSchedules(id, dto.getWork(), dto.getUserId(), dto.getPassWord()),HttpStatus.OK);
    }

    //단건 수정
    @PatchMapping("/{id}")
    public ResponseEntity<SchedulesResponseDto> updateWorkSchedules(
            @PathVariable Long id,
            @RequestBody SchedulesRequestDto dto) {

        return new ResponseEntity<>(schedulesService.updateWork(id, dto.getWork(), dto.getUserId(), dto.getPassWord()),HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               @RequestBody SchedulesRequestDto dto) {
        schedulesService.deleteSchedules(id, dto.getPassWord());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
