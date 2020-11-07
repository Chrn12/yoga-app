package com.woniu.yoga.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author fei
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TStudentMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_message_id", type = IdType.AUTO)
    private Integer tMessageId;

    private Integer tMessageSid;

    private Integer tMessageUid;

    /**
     * 1学生，2场馆，3教练
     */
    private Integer tMessageRole;

    private String tMessageContent;

    /**
     * 0未读1已读
     */
    private Integer tMessageStatus;

    private LocalDateTime tMessageTime;


}
