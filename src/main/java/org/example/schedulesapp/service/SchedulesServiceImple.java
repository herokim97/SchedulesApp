package org.example.schedulesapp.service;

import org.example.schedulesapp.dto.SchedulesRequestDto;
import org.example.schedulesapp.dto.SchedulesResponseDto;
import org.example.schedulesapp.entity.Schedules;
import org.example.schedulesapp.repository.SchedulesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SchedulesServiceImple implements SchedulesService {




    /* ---- 스케줄 생성 로직
    생성 후 저장을 위해 Repository 선언
     */
    private final SchedulesRepository schedulesRepository;

    public SchedulesServiceImple(SchedulesRepository schedulesRepository) {
        this.schedulesRepository = schedulesRepository;
    }

    @Override
    public SchedulesResponseDto saveSchedules(SchedulesRequestDto dto) {
        Schedules schedules = new Schedules(dto);

        //이를 schedulesRepository에 saveScheduels 함수를 통해 DB에 저장
        return schedulesRepository.saveSchedules(schedules);

    }

    //전체 조회
    @Override
    public List<SchedulesResponseDto> findAllSchedules(String userId, String updatedAt) {
        return schedulesRepository.findAllSchedules(userId, updatedAt);
    }

    //단건 조회
    @Override
    public SchedulesResponseDto findScheduleById(Long id) {

        Schedules schedules = schedulesRepository.findSchedulesByIdOrElseThrow(id);

        return new SchedulesResponseDto(schedules);

    }

    @Transactional
    @Override
    public SchedulesResponseDto updateSchedules(Long id, String userId, String work, String passWord) {
        if(work == null || userId ==null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "work or userId is null retry >>");
        }
        int updatedRow = schedulesRepository.updateSchedules(id, userId, work, passWord);

        if(updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "update failed");
        }

        Schedules schedules = schedulesRepository.findSchedulesByIdOrElseThrow(id);

        return new SchedulesResponseDto(schedules);
    }

    @Transactional
    @Override
    public SchedulesResponseDto updateWork(Long id, String work, String userId, String passWord) {

        int updatedRow ;
        //입력 받은 passWord를 비교하여, 로직 수행 여부 결정
        if (passWord == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "passWord is null retry >>");
        }

        if(work == null && userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "work or userId is null retry >>");
        }

        else if(work != null && userId == null) {
            updatedRow =schedulesRepository.updateWork(id, work, passWord);
        }

        else if(work == null && userId != null) {
            updatedRow =  schedulesRepository.updateUserId(id, userId, passWord);
        }
        else {
            updatedRow = schedulesRepository.updateSchedules(id, work, userId, passWord);
        }

        if(updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "update failed");
        }

        Schedules schedules = schedulesRepository.findSchedulesByIdOrElseThrow(id);

        return new SchedulesResponseDto(schedules);

    }

    @Override
    public void deleteSchedules(Long id, String passWord) {
        if (passWord == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "passWord is null retry >>");
        }

        int deleteRow = schedulesRepository.deleteSchedulesById(id, passWord);
        if(deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "delete failed");
        }

    }


}
