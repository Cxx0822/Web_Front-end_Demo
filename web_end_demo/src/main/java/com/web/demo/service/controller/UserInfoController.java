package com.web.demo.service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.web.demo.service.entity.UserInfo;
import com.web.demo.service.mapper.UserInfoMapper;
import com.web.demo.service.result.R;
import com.web.demo.service.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cxx
 * @since 2021-11-11
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @ApiOperation("所有用户信息列表")
    @GetMapping("listAll")
    public R listAll(){
        List<UserInfo> userInfoList = userInfoService.list();
        return R.ok().data("userInfoList", userInfoList);
    }

    // Swagger-ui测试接口
    @ApiOperation("注册用户信息")
    @PostMapping("register")
    public R register(@ApiParam(value = "用户信息", required = true) @RequestBody UserInfo userInfo){
        String name = userInfo.getName();
        Integer age = userInfo.getAge();

        if(name == null || name.length() == 0 || age == null){
            return R.error().message("请输入正确的用户信息");
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        List<UserInfo> userInfoList = userInfoMapper.selectByMap(map);

        if(userInfoList.size() != 0){
            return R.error().message("用户已存在");
        }else{
            boolean result = userInfoService.save(userInfo);
            if (result) {
                return R.ok().message("注册成功");
            } else {
                return R.error().message("注册失败");
            }
        }
    }

    // Postman测试接口
    @ApiOperation("注册用户信息")
    @RequestMapping(value = "register2", method = RequestMethod.POST)
    public R register2(UserInfo userInfo){
        String name = userInfo.getName();
        Integer age = userInfo.getAge();

        if(name == null || name.length() == 0 || age == null){
            return R.error().message("请输入正确的用户信息");
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        List<UserInfo> userInfoList = userInfoMapper.selectByMap(map);

        if(userInfoList.size() != 0){
            return R.error().message("用户已存在");
        }else{
            boolean result = userInfoService.save(userInfo);
            if (result) {
                return R.ok().message("注册成功");
            } else {
                return R.error().message("注册失败");
            }
        }
    }

    // Swagger-ui测试接口
    @ApiOperation("根据姓名查询用户信息")
    @GetMapping("get-userInfo-byName/{name}")
    public R getUserInfoByName(@ApiParam(value = "姓名", required = true) @PathVariable String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);

        List<UserInfo> userInfoList = userInfoMapper.selectByMap(map);

        if (userInfoList.size() == 0) {
            return R.error().message("查询失败，没有该用户信息");
        }else {
            return R.ok().data("userInfoList", userInfoList);
        }
    }

    // Postman测试接口
    @ApiOperation("根据姓名查询用户信息")
    @RequestMapping(value = "get-userInfo-byName", method = RequestMethod.GET)
    public R getUserInfoByName2(String name){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);

        List<UserInfo> userInfoList = userInfoMapper.selectByMap(map);

        if (userInfoList.size() == 0) {
            return R.error().message("查询失败，没有该用户信息");
        }else {
            return R.ok().data("userInfoList", userInfoList);
        }
    }

    @ApiOperation("根据用户姓名删除用户信息")
    @DeleteMapping("delete-userInfo-byName/{name}")
    public R deleteUserInfoByName(@ApiParam(value = "姓名", required = true) @PathVariable String name) {
        Map<String,Object> map = new HashMap<>();
        map.put("name", name);
        int rows = userInfoMapper.deleteByMap(map);
        if(rows == 0){
            return R.error().message("该用户名不存在");
        }else {
            return R.ok().message("删除成功");
        }
    }

    @ApiOperation("根据姓名更新用户信息")
    @PostMapping("update-userInfo-byName/{name}")
    public R updateUserInfoByName(@ApiParam(value = "姓名", required = true) @PathVariable String name,
                                  @ApiParam(value = "用户信息", required = true) @RequestBody UserInfo userInfo) {
        UpdateWrapper<UserInfo> userInfoUpdateWrapper = new UpdateWrapper<>();
        userInfoUpdateWrapper.eq("name", name);

        int row = userInfoMapper.update(userInfo, userInfoUpdateWrapper);

        if(row == 0){
            return R.error().message("用户名不存在");
        }else {
            return R.ok().message("修改成功");
        }
    }
}

