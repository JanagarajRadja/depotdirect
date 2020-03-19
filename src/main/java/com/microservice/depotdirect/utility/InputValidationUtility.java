package com.microservice.depotdirect.utility;

import com.microservice.depotdirect.dto.InputStudentDto;
import com.microservice.depotdirect.exception.InputValidationException;
import org.springframework.stereotype.Service;

@Service
public class InputValidationUtility {


    public void validateInput(InputStudentDto inputStudentDto) throws InputValidationException {
        if(inputStudentDto.getRollNo() == null || inputStudentDto.getRollNo().trim().isEmpty()
        || inputStudentDto.getRollNo().trim().length() != 5){
            throw new InputValidationException("poda dai");
        }
    }
}
