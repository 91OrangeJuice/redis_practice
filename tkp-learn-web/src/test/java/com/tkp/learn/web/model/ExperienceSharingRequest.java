package com.tkp.learn.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExperienceSharingRequest {
    private String lessonId;
    private String content;
}
