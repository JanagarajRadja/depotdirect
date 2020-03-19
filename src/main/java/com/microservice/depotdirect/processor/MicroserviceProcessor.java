package com.microservice.depotdirect.processor;

import com.microservice.depotdirect.dto.InputStudentDto;
import com.microservice.depotdirect.dto.OutputStudentDto;
import com.microservice.depotdirect.exception.InputValidationException;
import com.microservice.depotdirect.utility.InputValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MicroserviceProcessor {

    public Map<String, OutputStudentDto> outputMap1 = new HashMap<>();

    @Autowired
    private InputValidationUtility inputValidationUtility;

    public int squareOfValue(int input) {
        int output = 0;
        output = input * input;
        return output;
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

        try {
            inputValidationUtility.validateInput(inputStudentDto);
            outputStudentDto.setStudentName(inputStudentDto.getStudentName());
            outputStudentDto.setRollNo(inputStudentDto.getRollNo());
            outputStudentDto.setMarkList(inputStudentDto.getMarkList());
            int totalMarks = sumOfVariables(inputStudentDto.getMarkList());
            outputStudentDto.setMarksScored(String.valueOf(totalMarks));
            outputStudentDto.setEligibility(getEligibility(totalMarks));
        } catch (InputValidationException ex) {
            throw ex;
        }

        return outputStudentDto;
    }

    private boolean getEligibility(Integer marksScored) {
        boolean eligibility = false;
        if (marksScored >= 400) eligibility = true;
        return eligibility;
    }

    public Map<String, OutputStudentDto> getStudentMarksInMap(InputStudentDto inputStudentDto) throws Exception {
        try {
            outputMap1.put(inputStudentDto.getRollNo(), studentMarks(inputStudentDto));
        } catch (Exception e) {
            throw e;
        }

        return outputMap1;
    }

    public String getOutputMap(String rollNo) {
        if (rollNo == null || rollNo.isEmpty()) {
            return outputMap1.toString();
        } else if (outputMap1.containsKey(rollNo)) {
            return outputMap1.get(rollNo).toString();
        } else {
            return "Record not Found";
        }
    }

    public String deleteMap(String rollNo) {
        if (rollNo == null || rollNo.isEmpty()) {
            outputMap1.clear();
            return "Map deleted";
        } else if (outputMap1.containsKey(rollNo)) {
            outputMap1.remove(rollNo);
            return "RollNo:" + rollNo + " has been deleted.";
        } else {
            return "RollNo not found";
        }
    }
}
