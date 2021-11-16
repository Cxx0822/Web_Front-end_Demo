package com.web.demo.service.controller;


import com.web.demo.service.entity.UserConfig;
import com.web.demo.service.entity.UserInfo;
import com.web.demo.service.mapper.UserConfigMapper;
import com.web.demo.service.result.R;
import com.web.demo.service.service.UserConfigService;
import com.web.demo.service.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/user-config")
public class UserConfigController {
    @Autowired
    private UserConfigService userConfigService;

    @Autowired
    private UserConfigMapper userConfigMapper;

    // Swagger-ui测试接口
    @ApiOperation("登录")
    @PostMapping("/login/{username}/{password}")
    public R login(@ApiParam(value = "用户名", required = true) @PathVariable String username,
                   @ApiParam(value = "密码", required = true) @PathVariable String password){
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<UserConfig> userConfigList = userConfigMapper.selectByMap(map);

        if(userConfigList.size() == 0){
            return R.error().message("用户不存在");
        }else{
            String truePassword = userConfigList.get(0).getPassword();
            if(password.equals(truePassword)){
                HashMap<String, String> tokenMap = new HashMap<>();
                map.put("username", username);
                return R.ok().data("token", JWTUtil.getToken(tokenMap));
            }else {
                return R.error().message("密码错误");
            }
        }
    }

    // Postman测试接口
    @ApiOperation("登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public R login2(String username, String password){
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<UserConfig> userConfigList = userConfigMapper.selectByMap(map);

        if(userConfigList.size() == 0){
            return R.error().message("用户不存在");
        }else{
            String truePassword = userConfigList.get(0).getPassword();
            if(password.equals(truePassword)){
                HashMap<String, String> tokenMap = new HashMap<>();
                map.put("username", username);
                return R.ok().data("token", JWTUtil.getToken(tokenMap));
            }else {
                return R.error().message("密码错误");
            }
        }
    }

    @ApiOperation("所有用户配置信息列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public R listAll(){
        List<UserConfig> userConfigList = userConfigService.list();
        return R.ok().data("userConfigList", userConfigList);
    }

    @ApiOperation("注册用户配置信息")
    @PostMapping("register")
    public R register(@ApiParam(value = "用户信息", required = true) @RequestBody UserConfig userConfig){
        String username = userConfig.getUsername();
        String password = userConfig.getPassword();

        if(username == null || username.length() == 0 || password == null || password.length() == 0){
            return R.error().message("请输入正确的用户信息");
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<UserConfig> userConfigList = userConfigMapper.selectByMap(map);

        if(userConfigList.size() != 0){
            return R.error().message("用户已存在");
        }else{
            boolean result = userConfigService.save(userConfig);
            if (result) {
                return R.ok().message("注册成功");
            } else {
                return R.error().message("注册失败");
            }
        }
    }

    @ApiOperation("退出登录")
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public R logout(){
        return R.ok().message("退出成功");
    }
}

