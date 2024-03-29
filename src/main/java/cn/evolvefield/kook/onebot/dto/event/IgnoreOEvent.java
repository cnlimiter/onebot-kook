package cn.evolvefield.kook.onebot.dto.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/3 13:20
 * Version: 1.0
 */
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class IgnoreOEvent extends OEvent {
    public IgnoreOEvent(){
        this.setPostType("IGNORED");
        this.setTime(System.currentTimeMillis());
    }
    public IgnoreOEvent(long id){
        this.setPostType("IGNORED");
        this.setTime(System.currentTimeMillis());
        this.setSelfId(id);
    }


}
