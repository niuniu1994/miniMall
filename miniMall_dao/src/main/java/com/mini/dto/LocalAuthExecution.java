package com.mini.dto;

import com.mini.entity.LocalAuth;
import com.mini.enums.LocalAuthStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-12 19:07
 **/
@Getter
@Setter
public class LocalAuthExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    private int count;

    private LocalAuth localAuth;

    private List<LocalAuth> localAuthList;

    public LocalAuthExecution() {
    }

    // 失败的构造器
    public LocalAuthExecution(LocalAuthStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 成功的构造器
    public LocalAuthExecution(LocalAuthStateEnum stateEnum, LocalAuth localAuth) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.localAuth = localAuth;
    }

    // 成功的构造器
    public LocalAuthExecution(LocalAuthStateEnum stateEnum,
                              List<LocalAuth> localAuthList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.localAuthList = localAuthList;
    }
}
