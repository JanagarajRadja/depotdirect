package com.microservice.depotdirect.processor;

import com.microservice.depotdirect.dto.InputStudentDto;
import com.microservice.depotdirect.dto.OutputStudentDto;
import org.springframework.stereotype.Service;

@Service
public class MicroserviceProcessor {

    public int squareOfValue(int input) {
        int output = 0;
        output= input*input;
        return  output;
    }

    public Integer sumOfVariables(String[] requestList) throws Exception {
        Integer output = 0;
        try {
            for (String input : requestList) {
                output = output + Integer.valueOf(input);
            }
        } catch (Exception e) {
            throw new Exception("Please Enter Number Only");
        }
        return output;
    }

    public OutputStudentDto studentMarks(InputStudentDto inputStudentDto) throws Exception {
        MicroserviceProcessor microserviceProcessor=new MicroserviceProcessor();
        OutputStudentDto outputStudentDto = new OutputStudentDto();
        outputStudentDto.setStudentName(inputStudentDto.getStudentName());
        outputStudentDto.setRollNo(inputStudentDto.getRollNo());
        try {
        outputStudentDto.setMarksScored(microserviceProcessor.sumOfVariables(inputStudentDto.getMarkList()).toString());
        } catch (Exception e) {
            throw new Exception("Datatype Exception");
        }
        return outputStudentDto;
    }
}
