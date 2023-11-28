package com.tkp.learn.web.model.bo;

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
public class PersonExperienceSharingBo {
    private String courseExperienceId;
    private String courseName;
    private String content;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime publishTime;
    private long wonPraiseNum;
    private long newWonPraiseNum;
    private String BaseFileId;
    private List<PraiseWorkBo> PraiseWorkList;
    public String getContent() {
        if(StringUtils.isEmpty(content)){
            return content;
        }
        return EmojiParser.parseToUnicode(content);
    }
}
