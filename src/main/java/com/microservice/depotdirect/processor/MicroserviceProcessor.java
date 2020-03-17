package com.microservice.depotdirect.processor;

import com.microservice.depotdirect.dto.InputStudentDto;
import com.microservice.depotdirect.dto.OutputStudentDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MicroserviceProcessor {

    public Map<String, OutputStudentDto> outputMap = new HashMap<>();

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
        OutputStudentDto outputStudentDto = new OutputStudentDto();
        outputStudentDto.setStudentName(inputStudentDto.getStudentName());
        outputStudentDto.setRollNo( inputStudentDto.getRollNo());
        outputStudentDto.setMarkList(inputStudentDto.getMarkList());

        try {
            int totalMarks = sumOfVariables(inputStudentDto.getMarkList());
            outputStudentDto.setMarksScored(String.valueOf(totalMarks));
            outputStudentDto.setEligibility(getEligibility(totalMarks));
        } catch (Exception e) {
            throw e;
        }

        return outputStudentDto;
    }

    private boolean getEligibility(Integer marksScored) {
        boolean eligibility = false;
        if(marksScored>= 400) eligibility = true;
        return eligibility;
    }

    public Map<String, OutputStudentDto> getStudentMarksInMap(InputStudentDto inputStudentDto) throws Exception {
        try {
            outputMap.put(inputStudentDto.getRollNo(), studentMarks(inputStudentDto));
        }catch(Exception e){
            throw  e;
        }

        return outputMap;
    }

    public String getOutputMap(String rollNo) {
        if(rollNo == null || rollNo.isEmpty()){
            return outputMap.toString();
        }
        else if (outputMap.containsKey(rollNo)){
            return outputMap.get(rollNo).toString();
        }
        else
            {
             return "Record not Found" ;
        }
    }
}
