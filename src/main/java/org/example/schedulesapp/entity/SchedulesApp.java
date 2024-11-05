package org.example.schedulesapp.entity;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.example.schedulesapp.dto.SchedulesRequestDto;

@Getter
@ToString
public class SchedulesApp {

    private Long id;
    private String title;
    private String work;
    private String userId;
    private String password;
    //약속 일자
    private String date;
    //생성 일자
    private String createDate;
    //수정된 일자
    private String updateDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SchedulesApp(Long id, String title, String work, String userId, String password, String date) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.work = work;
        this.userId = userId;
        this.password = password;
        this.createDate = LocalDateTime.now().withNano(0).format(formatter);
        this.updateDate = LocalDateTime.now().withNano(0).format(formatter);
    }

    public void update(SchedulesRequestDto dto) {
        this.title = dto.getTitle();
        this.work = dto.getWork();
        this.updateDate = LocalDateTime.now().withNano(0).format(formatter);
    }

    public void updateUserId(SchedulesRequestDto dto) {
        this.userId = dto.getUserId();
        this.updateDate = LocalDateTime.now().withNano(0).format(formatter);
    }
    public void updateWork(SchedulesRequestDto dto) {
        this.work = dto.getWork();
        this.updateDate = LocalDateTime.now().withNano(0).format(formatter);
    }

}
