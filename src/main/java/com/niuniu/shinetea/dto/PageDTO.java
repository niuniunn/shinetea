package com.niuniu.shinetea.dto;

import lombok.Data;

@Data
public class PageDTO {

    private Object data;

    private Integer page;

    private Integer size;

    private Long total;
}
