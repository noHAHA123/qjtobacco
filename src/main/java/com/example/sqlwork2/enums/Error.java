package com.example.sqlwork2.enums;

public interface Error {

    /**
     * @return 错误码
     */
    int getCode();

    /**
     * @return 错误描述
     */
    String getMessage();

}
