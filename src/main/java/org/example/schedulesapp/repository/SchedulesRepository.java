package org.example.schedulesapp.repository;

import org.example.schedulesapp.dto.SchedulesResponseDto;
import org.example.schedulesapp.entity.Schedules;

import java.util.List;
import java.util.Optional;

public interface SchedulesRepository {
    SchedulesResponseDto saveSchedules (Schedules schedules);
    List<SchedulesResponseDto> findAllSchedules ();
    Optional<Schedules> findScheduleById (Long id);

    Schedules findSchedulesByIdOrElseThrow (Long id);
}
