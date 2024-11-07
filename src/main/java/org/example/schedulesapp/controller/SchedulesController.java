package org.example.schedulesapp.controller;


import org.example.schedulesapp.dto.SchedulesRequestDto;
import org.example.schedulesapp.dto.SchedulesResponseDto;
import org.example.schedulesapp.entity.Schedules;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedulesapp")
public class SchedulesController {

    private final Map<Long, Schedules> schedulesList = new HashMap<>();

    //PostMapping 생성
    @PostMapping
    public ResponseEntity<SchedulesResponseDto> createSchedules(@RequestBody SchedulesRequestDto dto) {
        Long schedulesId = schedulesList.isEmpty() ? 1 : Collections.max(schedulesList.keySet()) + 1;

        Schedules schedules = new Schedules(schedulesId, dto.getTitle(), dto.getWork(),dto.getUserId(),dto.getPassWord(),dto.getDate());

        schedulesList.put(schedulesId, schedules);


        return new ResponseEntity<>(new SchedulesResponseDto(schedules), HttpStatus.CREATED);
    }

    //전체 조회
    @GetMapping
    public List<SchedulesResponseDto> findAllSchedules() {

        List<SchedulesResponseDto> responseList = new ArrayList<>();

        // schedulesList.values()를 Stream으로 변환 후, 수정일 기준 내림차순 정렬
        schedulesList.values().stream()
                .sorted(Comparator.comparing(Schedules::getUpdateDate).reversed()) // modifyDate 기준 내림차순 정렬
                .forEach(schedules -> responseList.add(new SchedulesResponseDto(schedules)));

        return responseList;
    }

    //단건 조회 기능
    @GetMapping("/{id}")
    public ResponseEntity<SchedulesResponseDto> findScheduleById(@PathVariable Long id) {
        Schedules schedules = schedulesList.get(id);

        if(schedules == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new SchedulesResponseDto(schedules), HttpStatus.OK);
    }

    //단건 일부 수정 및 전체 수정 기능
    @PatchMapping("/{id}")
    public ResponseEntity<SchedulesResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody SchedulesRequestDto dto
    ) {
        Schedules schedules = schedulesList.get(id);

        if(schedules == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //공백일 땐?
//        Objects.equals()
//        StringUtils.isEmpty()
        if(dto.getPassWord() == null || dto.getPassWord().isEmpty() || !dto.getPassWord().equals(schedules.getPassword())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(dto.getWork() != null) {
            schedules.updateWork(dto);
        }
        if(dto.getUserId() != null && dto.getPassWord().equals(schedules.getPassword())) {
            schedules.updateUserId(dto);
        }
        if(dto.getWork() != null && dto.getUserId() != null && dto.getPassWord().equals(schedules.getPassword())) {
            schedules.updateWork(dto);
            schedules.updateUserId(dto);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new SchedulesResponseDto(schedules), HttpStatus.OK);
    }

}
