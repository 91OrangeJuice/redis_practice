package com.tkp.learn.pushcdn.task.remote.dto;

import java.util.List;

/**
 * Created by itw_wangshuai01 on 2020/6/15.
 */
public class HotLessonDto {

    private String lessonId;

    private String lessonName;

    private String description;

    private String labelCode;

    private int learnersNum;

    private String iconPath;

    private String createTime;

    private List<SimpleTagDto> tags;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public int getLearnersNum() {
        return learnersNum;
    }

    public void setLearnersNum(int learnersNum) {
        this.learnersNum = learnersNum;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<SimpleTagDto> getTags() {
        return tags;
    }

    public void setTags(List<SimpleTagDto> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "HotLessonDto{" +
                "lessonId='" + lessonId + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", description='" + description + '\'' +
                ", labelCode='" + labelCode + '\'' +
                ", learnersNum=" + learnersNum +
                ", iconPath='" + iconPath + '\'' +
                ", createTime='" + createTime + '\'' +
                ", tags=" + tags +
                '}';
    }
}
