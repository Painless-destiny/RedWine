package com.redwine.controller;


import com.redwine.entity.User;
import com.redwine.service.UserService;
import com.redwine.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyh
 * @since 2023-02-21 12:13:23
 */
@RestController
@RequestMapping("/redwine/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param
     * @return
     */
    @PostMapping("reg")
    public R reg(@RequestBody User user) {
        userService.reg(user);
        return R.ok();
    }

    /**
     * 用户登录
     * @param
     * @return
     */
    @PostMapping("login")
    public R login(String username, String password) {
        User login = userService.login(username, password);
        return R.ok().data("login", login);
    }
}
