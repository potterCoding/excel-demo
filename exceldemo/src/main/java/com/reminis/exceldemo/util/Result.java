package com.reminis.exceldemo.util;

import lombok.Data;

@Data
public class Result<T> {

    private int code = 0;

    private String message = "SUCCESS";

    private Integer number = 0;

    private T data;

}
