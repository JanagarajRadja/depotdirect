package com.microservice.depotdirect.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InputValidationException  extends Exception{
    public String exceptionMessage;
}
