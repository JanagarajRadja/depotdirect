package com.microservice.depotdirect.dto;

import lombok.*;


@Data
@Getter
@Setter
public class InputStudentDto {
    private String studentName;
    private String rollNo;
    private String [] markList;

}
