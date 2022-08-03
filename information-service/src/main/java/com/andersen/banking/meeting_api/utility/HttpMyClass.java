package com.andersen.banking.meeting_api.utility;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HttpMyClass implements Serializable {

    private boolean error;

    private String msg;

    private List<String> data;

}
