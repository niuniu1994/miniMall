package com.mini.dao;

import com.mini.BaseDaoTest;
import com.mini.entity.LocalAuth;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-12 20:40
 **/
public class LocalAuthDaoTest extends BaseDaoTest {
    @Resource
    LocalDao localDao;

    @Test
    public void testAnamePass(){
        String name = "test";
        String password = "0000";
        LocalAuth localAuth = localDao.queryLocalByUserNameAndPwd(name,password);
        System.out.println(localAuth);
    }
}
