package cn.evolvefield.kook.onebot.dto.event.notice.group;

import cn.evolvefield.mirai.onebot.dto.event.notice.NoticeEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created on 2022/7/8.
 *
 * @author cnlimiter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class GroupCardChangeNoticeEvent extends NoticeEvent {

    @JSONField(name = "card_new")
    private String cardNew;

    @JSONField(name = "group_id")
    private long groupId;

    @JSONField(name = "card_old")
    private String cardOld;

    @JSONField(name = "user_id")
    private long userId;

}
