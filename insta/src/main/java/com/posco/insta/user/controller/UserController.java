package com.posco.insta.user.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
    @TokenRequired
    public List<UserDto> getUser(){
        return userService.findUser();
    }

    @GetMapping("/{id}")
    @TokenRequired
    public UserDto getUserById(@PathVariable String id){
        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
       return userService.findUserById(userDto);
    }

    @PostMapping("/")
    public ResponseEntity<?> createPost(@RequestBody UserDto userDto){

        HttpStatus httpStatus = userService.insertUser(userDto)==1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping("/")
    @TokenRequired
    public Integer deletePost(){

        UserDto userDto = new UserDto();
        userDto.setId(securityService.getIdAtToken());
        return userService.deleteUser(userDto);
    }

    @PutMapping("/")
    @TokenRequired
    public Integer updateUserById(@RequestBody UserDto userDto){

        userDto.setId(securityService.getIdAtToken());
        return userService.updateUserById(userDto);

    }

    @PostMapping("/login")
    public Map selectUserByIdAndPassword(@RequestBody UserDto userDto){
        UserDto loginUser = userService.login(userDto);
        String token = securityService.createToken(loginUser.getId().toString());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("name", loginUser.getName());
        map.put("img", loginUser.getImg());
        return map;
        //return userService.findUserByIdAndPassword(userDto);
    }

    @GetMapping("/token")
    @TokenRequired
    public String getToken(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenBearer = request.getHeader("Authorization");
        String subject = securityService.getSubject(tokenBearer);
        return subject;
    }

    @GetMapping("/me")
    @TokenRequired
    public UserDto getUserByMe(){
        UserDto userDto = new UserDto();
        userDto.setId(securityService.getIdAtToken());
        return userService.findUserById(userDto);

    }
//token 인증하는 방법 header에도 넣고 parameter에도 넣는다

    @TokenRequired
    @GetMapping("/check")
    public Boolean check(){
        return true;
    }





}
