package com.niuniu.shinetea.VO;


import lombok.Data;

/*http请求返回的最外层的对象*/
@Data
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;
}
