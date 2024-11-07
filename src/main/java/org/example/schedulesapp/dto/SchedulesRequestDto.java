package org.example.schedulesapp.dto;

import lombok.Getter;
import lombok.ToString;


@Getter
public class SchedulesRequestDto {

    private String title;
    private String work;
    private String userId;
    private String passWord;
    private String date;

}
