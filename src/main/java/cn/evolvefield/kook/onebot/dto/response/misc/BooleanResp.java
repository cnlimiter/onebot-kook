package cn.evolvefield.kook.onebot.dto.response.misc;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created on 2022/7/8.
 *
 * @author cnlimiter
 */
@Data
@AllArgsConstructor
public class BooleanResp {

    @JSONField(name = "yes")
    private boolean yes;

}
