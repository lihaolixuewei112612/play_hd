package com.example.demo.result;

import lombok.Data;

/**
 * @author Evan
 * @date 2019/4
 */
@Data
public class Result {
    private int code;
    private String message;
    private Object result;

    public Result(int code) {
        this.code = code;
//        this.message = message;
//        this.result = data;
    }
}
