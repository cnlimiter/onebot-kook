package cn.evolvefield.kook.onebot.dto.response.misc;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author cnlimiter
 */
@Data
public class WordSlicesResp {

    @JSONField(name = "slices")
    private List<String> slices;

}
