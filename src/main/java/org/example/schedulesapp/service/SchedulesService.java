package org.example.schedulesapp.service;

import org.example.schedulesapp.dto.SchedulesRequestDto;
import org.example.schedulesapp.dto.SchedulesResponseDto;
import org.example.schedulesapp.entity.Schedules;

import java.util.List;

public interface SchedulesService {

    SchedulesResponseDto saveSchedules(SchedulesRequestDto dto);
    List<SchedulesResponseDto> findAllSchedules();
    SchedulesResponseDto findScheduleById(Long id);
}
