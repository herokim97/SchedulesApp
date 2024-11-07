package org.example.schedulesapp.service;

import org.example.schedulesapp.dto.SchedulesRequestDto;
import org.example.schedulesapp.dto.SchedulesResponseDto;
import org.example.schedulesapp.entity.Schedules;
import org.example.schedulesapp.repository.SchedulesRepository;
import org.springframework.stereotype.Service;

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
    public List<SchedulesResponseDto> findAllSchedules() {
        return schedulesRepository.findAllSchedules();
    }

    //단건 조회
    @Override
    public SchedulesResponseDto findScheduleById(Long id) {

        Schedules schedules = schedulesRepository.findSchedulesByIdOrElseThrow(id);

        return new SchedulesResponseDto(schedules);

    }


}
