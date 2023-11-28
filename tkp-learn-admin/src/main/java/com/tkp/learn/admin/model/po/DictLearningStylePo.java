package com.tkp.learn.admin.model.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author itw_liupeng01
 * @since 2020-08-03
 */
@Data
@TableName(value = "dict_learning_style")
public class DictLearningStylePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String learningStyle;

    private String learningStyleName;


}
