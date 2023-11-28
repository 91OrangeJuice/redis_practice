package com.tkp.learn.web.model.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceSharingBo {
    private String id;
    private String userName;
    private String orgShortName;
    private String content;
    private long isPraise;
    private long wonPraiseNum;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime publishTime;
    private long top;
    private String headImgUrl;

    public String getContent() {
        if(StringUtils.isEmpty(content)){
            return content;
        }
        return EmojiParser.parseToUnicode(content);
    }

}
