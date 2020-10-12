package com.mini.biz.impl;

import com.mini.biz.LocalAuthBiz;
import com.mini.dao.LocalDao;
import com.mini.dto.LocalAuthExecution;
import com.mini.entity.LocalAuth;
import com.mini.exception.LocalAuthOperationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-12 19:22
 **/
@Service("localAuthBiz")
public class LocalAuthBizImpl implements LocalAuthBiz {
    @Resource
    private LocalDao localDao;

    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password) {
        return localDao.queryLocalByUserNameAndPwd(userName, password);
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return null;
    }

    @Override
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        return null;
    }

    @Override
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException {
        return null;
    }

}
