package cn.evolvefield.kook.onebot.dto.response.common;

import cn.evolvefield.mirai.onebot.dto.response.ActionData;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/9 23:26
 * Version: 1.0
 */
public class MiraiFailure extends ActionData<String> {
    public MiraiFailure(){
        this.setStatus("failed");
        this.setRetCode(102);
        this.setData(null);
    }
}
