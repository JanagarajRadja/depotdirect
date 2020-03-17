package com.microservice.depotdirect.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@ToString
public class OutputStudentDto {
        private String studentName;
        private String rollNo;
        private String [] markList;
        private String marksScored;
        private boolean eligibility;
    }
