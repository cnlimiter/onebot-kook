package cn.evolvefield.kook.onebot.dto.event.meta;

import cn.evolvefield.mirai.onebot.dto.event.Event;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/7 1:09
 * Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class MetaEvent extends Event {
    @JSONField(name = "meta_event_type")
    private String metaEventType;

    @Override
    public void setPostType(String postType) {
        super.setPostType("meta_event");
    }
}
