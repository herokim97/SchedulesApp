package org.example.schedulesapp.entity;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.example.schedulesapp.dto.SchedulesRequestDto;

@Getter
@AllArgsConstructor
public class Schedules {

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
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Schedules(Long id, String title, String work, String userId,  String date, String createDate, String updateDate) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.work = work;
        this.userId = userId;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Schedules(SchedulesRequestDto schedulesRequestDto) {
        this.date = schedulesRequestDto.getDate();
        this.title = schedulesRequestDto.getTitle();
        this.work = schedulesRequestDto.getWork();
        this.userId = schedulesRequestDto.getUserId();
        this.password = schedulesRequestDto.getPassWord();
        this.createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updateDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }





}
