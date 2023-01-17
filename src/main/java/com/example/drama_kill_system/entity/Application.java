package com.example.drama_kill_system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author luhe
 * @since 2023-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shop_id", type = IdType.AUTO)
    private Integer shopId;

    private Integer dramaId;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime dealTime;


}
