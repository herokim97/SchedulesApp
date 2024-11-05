package org.example.schedulesapp.dto;


import lombok.Getter;
import org.example.schedulesapp.entity.SchedulesApp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class SchedulesResponseDto {

    private Long id;
    private String title;
    private String work;
    private String userId;
    private String date;
    private String createdAt;
    private String updatedAt;

    public SchedulesResponseDto(SchedulesApp schedulesApp) {
        this.id = schedulesApp.getId();
        this.title = schedulesApp.getTitle();
        this.work = schedulesApp.getWork();
        this.date = schedulesApp.getDate();
        this.userId = schedulesApp.getUserId();
        this.createdAt = schedulesApp.getCreateDate();
        this.updatedAt = schedulesApp.getUpdateDate();
    }

}
