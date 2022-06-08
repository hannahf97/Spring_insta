package com.posco.insta.user.controller;

import com.posco.insta.config.SecurityService;
import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    SecurityService securityService;
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

    @PostMapping("/")
    public Integer createPost(@RequestParam String userId, @RequestParam String img, @RequestParam String name, @RequestParam String password){
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setImg(img);
        userDto.setName(name);
        userDto.setPassword(password);

        return userService.insertUser(userDto);
    }

    @DeleteMapping("/{id}")
    public Integer deletePost(@PathVariable String id){
        UserDto userDto = new UserDto();
        userDto.setId(Integer.parseInt(id));
        return userService.deleteUser(userDto);
    }

    @PutMapping("/{id}")
    public Integer updateUserById(@RequestBody UserDto userDto, @PathVariable String id){
        userDto.setId(Integer.valueOf(id));
        return userService.updateUserById(userDto);

    }

    @PostMapping("/login")
    public Map selectUserByIdAndPassword(@RequestBody UserDto userDto){
        UserDto loginUser = userService.login(userDto);
        String token = securityService.createToken(loginUser.getUserId().toString(), 3*24*60*60*1000);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("name", loginUser.getName());
        map.put("img", loginUser.getImg());
        return map;
        //return userService.findUserByIdAndPassword(userDto);
    }

    @GetMapping("/token")
    public String getToken(@RequestParam(value = "token") String token){
        String subject = securityService.getSubject(token);
        return subject;
    }




}
