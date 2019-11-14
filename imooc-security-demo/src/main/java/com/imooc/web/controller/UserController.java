package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/me")
    public Object getCurrentUser(Authentication authentication){
        return authentication;
    }

    @GetMapping("/me2")
    public Object getCurrentUser2(@AuthenticationPrincipal UserDetails user){
        return user;
    }


    @PostMapping
    public User create(@Valid @RequestBody User user){

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@PathVariable String id,@Valid @RequestBody User user, BindingResult errors){

        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField() + " " +error.getDefaultMessage();
                System.out.println(message);
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "获取用户信息")
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }


    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam("用户id") @PathVariable String id){

//        throw new UserNotExistException("1");
        System.out.println("进入getInfo 服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }



}
