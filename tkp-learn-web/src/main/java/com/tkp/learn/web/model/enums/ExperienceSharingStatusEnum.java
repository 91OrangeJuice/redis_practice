package com.tkp.learn.web.model.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum ExperienceSharingStatusEnum {
    valid("1","有效"),
    invalid("0","无效");
    private String code;
    private String message;
}
