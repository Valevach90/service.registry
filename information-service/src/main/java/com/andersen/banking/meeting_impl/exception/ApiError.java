package com.andersen.banking.meeting_impl.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String message;
    private String debugMessage;
}
