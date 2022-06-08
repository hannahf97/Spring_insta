package com.posco.insta.user.service;

import com.posco.insta.user.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> findUser();
    UserDto findUserById(UserDto userDto);
    Integer insertUser(UserDto userDto);
    Integer deleteUser(UserDto userDto);

    Integer updateUserById(UserDto userDto);

    UserDto login(UserDto userDto);

}
