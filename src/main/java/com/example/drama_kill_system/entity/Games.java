package com.example.drama_kill_system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyf
 * @since 2023-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("games")
public class Games implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "game_id", type = IdType.AUTO)
    private Integer gameId;

    private Integer shopId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String status;

    private Integer applicants_id;
}
