package com.redwine.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.redwine.entity.User;
import com.redwine.mapper.UserMapper;
import com.redwine.service.UserService;
import com.redwine.service.ex.comFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyh
 * @since 2023-02-21 12:13:23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (username==null || password==null){
            throw new comFoundException("输入用户名或密码不能为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User tu = userMapper.selectOne(queryWrapper);
        if (tu != null){
            throw new comFoundException("账号已注册");
        }
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5(user.getPassword(), salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedUser(username);
        user.setCreatedTime(new Date());
        user.setModifiedUser(username);
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new comFoundException("添加数据失败");
        }
    }

    @Override
    public User login(String username, String password) {
        if (username == null && password == null){
            throw new comFoundException("输入账号或密码为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("is_delete", 0);
        User  userTemp = userMapper.selectOne(queryWrapper);
        if (userTemp==null){
            throw new comFoundException("找不到用户信息");
        }
        String salt = userTemp.getSalt();
        String md5Password = getMd5(password, salt);
        if (!userTemp.getPassword().equals(md5Password)){
            throw new comFoundException("密码错误");
        }
        User user = new User();
        user.setUid(userTemp.getUid());
        user.setUsername(username);
        user.setAvatar(userTemp.getAvatar());
        return user;
    }

    private String getMd5(String password, String salt) {
        for (int i = 0; i < 3; i++){
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
