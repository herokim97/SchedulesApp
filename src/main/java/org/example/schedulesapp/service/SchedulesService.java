package org.example.schedulesapp.service;

import org.example.schedulesapp.dto.SchedulesRequestDto;
import org.example.schedulesapp.dto.SchedulesResponseDto;
import org.example.schedulesapp.entity.Schedules;

import java.util.List;

public interface SchedulesService {

    SchedulesResponseDto saveSchedules(SchedulesRequestDto dto);
    List<SchedulesResponseDto> findAllSchedules(String userId, String updatedAt);
    SchedulesResponseDto findScheduleById(Long id);
    SchedulesResponseDto updateSchedules(Long id, String userId, String work, String passWord);
    SchedulesResponseDto updateWork(Long id, String userId, String work, String passWord);
    void deleteSchedules(Long id, String passWord);
}
