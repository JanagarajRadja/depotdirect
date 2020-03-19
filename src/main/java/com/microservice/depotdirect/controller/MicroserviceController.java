package com.microservice.depotdirect.controller;

import com.microservice.depotdirect.dto.InputStudentDto;
import com.microservice.depotdirect.dto.OutputStudentDto;
import com.microservice.depotdirect.processor.MicroserviceProcessor;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "Microservice")
@RestController
@RequestMapping("microservice")
public class MicroserviceController {

    @Autowired
    private MicroserviceProcessor microserviceProcessor;

    @GetMapping(value = "/v1/getHello", produces = MediaType.TEXT_HTML_VALUE)
    public String getHello() {
        String msg = "HELLO";
        return msg;
    }

    @PostMapping(path = "/v1/getSquare")
    public int getSquare(int input) {
        int output = 0;
        output = microserviceProcessor.squareOfValue(input);
        return output;
    }

    @PostMapping(path = "/v2/getSquare/{input}")
    public int getSquareV2(@PathVariable int input) {
        int output = 0;
        output = microserviceProcessor.squareOfValue(input);
        return output;
    }

    @PostMapping(path = "/v1/sumOfVariables")
    public Integer sumOfVariables(String[] requestList) throws Exception {
        Integer output = 0;
        output = microserviceProcessor.sumOfVariables(requestList);
        return output;
    }

    @PostMapping(path = "/v1/studentMarks")
    public OutputStudentDto studentMarks(InputStudentDto inputStudentDto) throws Exception {
        OutputStudentDto outputStudentDto;
        outputStudentDto = microserviceProcessor.studentMarks(inputStudentDto);
        return outputStudentDto;
    }

    @PostMapping(path = "/v1/studentMarksInMap")
    public Map<String, OutputStudentDto> studentMarksInMap(InputStudentDto inputStudentDto) throws Exception {
        Map<String, OutputStudentDto> outputMap;
        outputMap = microserviceProcessor.getStudentMarksInMap(inputStudentDto);
        return outputMap;
    }

    @GetMapping(value = "/v1/getStudentMarksInMap")
    public String getStudentMarksInMap(@RequestParam(value = "rollNo", required = false) String rollNo) {
        return microserviceProcessor.getOutputMap(rollNo);
    }

    @DeleteMapping(path = "/v1/deleteStudentMarkInMap")
    public String deleteStudentMarkInMap(@RequestParam(value = "rollNo", required = false) String rollNo) {
        return microserviceProcessor.deleteMap(rollNo);
    }


}
