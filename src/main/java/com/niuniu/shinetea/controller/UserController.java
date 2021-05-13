package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.dataobject.User;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.service.UserService;
import com.niuniu.shinetea.service.impl.TokenService;
import com.niuniu.shinetea.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    //登录
    @PostMapping("/login")
    public ResultVO login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletResponse response) {
        User user = userService.findByUsername(username);
        if(username == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMessage());
        }
        if(!user.getPassword().equals(password)) {
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR.getCode(), ResultEnum.PASSWORD_ERROR.getMessage());
        } else {
            String token = tokenService.getToken(user);

            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResultVOUtil.success(token);
        }
    }
}
