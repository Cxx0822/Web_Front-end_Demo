package com.web.demo.service.service.impl;

import com.web.demo.service.entity.UserInfo;
import com.web.demo.service.mapper.UserInfoMapper;
import com.web.demo.service.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cxx
 * @since 2021-11-11
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
