package org.example.schedulesapp.repository;

import org.example.schedulesapp.dto.SchedulesResponseDto;
import org.example.schedulesapp.entity.Schedules;

import java.util.List;
import java.util.Optional;

public interface SchedulesRepository {
    SchedulesResponseDto saveSchedules (Schedules schedules);
    List<SchedulesResponseDto> findAllSchedules (String userId, String updatedAt);
    Optional<Schedules> findScheduleById (Long id);

    Schedules findSchedulesByIdOrElseThrow (Long id);

    int updateSchedules(Long id, String userId, String work, String passWord);
    int updateWork(Long id, String work, String passWord);
    int updateUserId(Long id, String userId,String passWord);

    int deleteSchedulesById(Long id, String passWord);

}
