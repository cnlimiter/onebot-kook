package cn.evolvefield.kook.onebot.dto.response.contact;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author cnlimiter
 */
@Data
public class UnidirectionalFriendListResp {

    @JSONField(name = "user_id")
    private long userId;

    @JSONField(name = "nickname")
    private String nickname;

    @JSONField(name = "source")
    private String source;

}
