package com.andersen.banking.meeting_impl.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    private String message;
    private String debugMessage;

    public ApiError(String message, String debugMessage){
        this.message=message;
        this.debugMessage=debugMessage;
    }
}
