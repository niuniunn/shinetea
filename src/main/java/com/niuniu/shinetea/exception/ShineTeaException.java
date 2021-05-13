package com.niuniu.shinetea.exception;

import com.niuniu.shinetea.enums.ResultEnum;

public class ShineTeaException extends RuntimeException{
    private Integer code;

    public ShineTeaException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public ShineTeaException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
