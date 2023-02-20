package com.redwine.mapper;

import com.redwine.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2023-02-21 12:13:23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
