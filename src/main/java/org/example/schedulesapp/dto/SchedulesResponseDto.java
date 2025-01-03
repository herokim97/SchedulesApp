package org.example.schedulesapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedulesapp.entity.Schedules;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class SchedulesResponseDto {

    private Long id;
    private String title;
    private String work;
    private String userId;
    private String date;
    private String createdAt;
    private String updatedAt;

    public SchedulesResponseDto(Schedules schedules) {

        this.id = schedules.getId();
        this.title = schedules.getTitle();
        this.work = schedules.getWork();
        this.date = schedules.getDate();
        this.userId = schedules.getUserId();
        this.createdAt = schedules.getCreateDate();
        this.updatedAt = schedules.getUpdateDate();
    }

}
