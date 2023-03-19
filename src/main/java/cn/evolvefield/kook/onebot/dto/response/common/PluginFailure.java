package cn.evolvefield.kook.onebot.dto.response.common;

import cn.evolvefield.kook.onebot.dto.response.ActionData;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/9 23:26
 * Version: 1.0
 */
public class PluginFailure extends ActionData<String> {
    public PluginFailure(){
        this.setStatus("failed");
        this.setRetCode(103);
        this.setData(null);
    }
}
