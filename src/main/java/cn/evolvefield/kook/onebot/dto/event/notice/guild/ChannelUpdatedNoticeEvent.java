package cn.evolvefield.kook.onebot.dto.event.notice.guild;

import cn.evolvefield.mirai.onebot.dto.event.notice.NoticeEvent;
import cn.evolvefield.mirai.onebot.dto.response.guild.ChannelInfoResp;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author cnlimiter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ChannelUpdatedNoticeEvent extends NoticeEvent {

    /**
     * 频道ID
     */
    @JSONField(name = "guild_id")
    private String guildId;

    /**
     * 子频道ID
     */
    @JSONField(name = "channel_id")
    private String channelId;

    /**
     * 操作者ID
     */
    @JSONField(name = "operator_id")
    private String operatorId;

    /**
     * 更新前的频道信息
     */
    @JSONField(name = "old_info")
    private ChannelInfoResp oldInfo;

    /**
     * 更新后的频道信息
     */
    @JSONField(name = "new_info")
    private ChannelInfoResp newInfo;

}
