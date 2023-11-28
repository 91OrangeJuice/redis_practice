package com.tkp.learn.web.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PraiseWorkBo {
    private String userId;
    private String userName;
    private String orgShortName;
    private String status;
    private String id;
}
