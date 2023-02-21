package com.redwine.service;

import com.redwine.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2023-02-21 12:13:23
 */
public interface UserService extends IService<User> {
    /**
     *  用户注册
     * @param
     */
    void reg(User user);


    /**
     * 用户登录
     */
    User login(String username, String password);
}
