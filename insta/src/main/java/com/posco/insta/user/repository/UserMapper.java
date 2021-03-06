package com.posco.insta.user.repository;

import com.posco.insta.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> getUser();
    UserDto getUserById(UserDto userDto);

    Integer insertUser(UserDto userDto);

    Integer deleteUser(UserDto userDto);

    Integer updateUserById(UserDto userDto);

    UserDto getUserByUserIdAndPassword(UserDto userDto);

}
