package com.posco.insta.user.controller;

import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @GetMapping("/")
    public List<UserDto> getUser(){
        return userService.findUser();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id){
        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
       return userService.findUserById(userDto);
    }

    @PostMapping("/create")
    public int createPost(@RequestParam String userId, @RequestParam String img, @RequestParam String name, @RequestParam String password){
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setImg(img);
        userDto.setName(name);
        userDto.setPassword(password);

        return userService.insertUser(userDto);
    }

}
