package cn.evolvefield.kook.onebot.dto.response.guild;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author cnlimiter
 */
@Data
public class GuildMemberProfileResp {

    @JSONField(name = "tiny_id")
    private String tinyId;

    @JSONField(name = "nickname")
    private String nickname;

    @JSONField(name = "avatar_url")
    private String avatarUrl;

    @JSONField(name = "join_time")
    private String joinTime;

    @JSONField(name = "roles")
    private List<RoleInfo> roles;

    @Data
    public static class RoleInfo {

        @JSONField(name = "role_id")
        private String roleId;

        @JSONField(name = "role_name")
        private String roleName;

    }

}
