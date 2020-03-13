package com.microservice.depotdirect.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class OutputStudentDto {
        private String studentName;
        private String rollNo;
        private String marksScored;
    }
