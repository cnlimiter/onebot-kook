package cn.evolvefield.kook.onebot.dto.response.misc;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * Created on 2022/7/8.
 *
 * @author cnlimiter
 */
@Data
public class CheckUrlSafelyResp {

    @JSONField(name = "level")
    private int level;

}
