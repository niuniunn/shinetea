package com.niuniu.shinetea.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class MemberForm {

    private Integer id;

    @NotBlank(message = "openid不能为空")
    private String openid;

    @NotBlank(message = "昵称你能为空")
    private String nickname;

    private String tel;

    private Integer gender;

    private String birthday;
}
