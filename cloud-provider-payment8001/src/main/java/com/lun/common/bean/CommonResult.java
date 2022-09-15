package com.lun.common.bean;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String msg;

    private T data;


    public CommonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }


}
