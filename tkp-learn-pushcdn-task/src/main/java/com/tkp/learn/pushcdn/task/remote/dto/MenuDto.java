package com.tkp.learn.pushcdn.task.remote.dto;


public class MenuDto {

    private String menuCode;

    private String menuName;

    private String iconPath;

    private int sortNo;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    @Override
    public String toString() {
        return "MenuDto{" +
                "menuCode='" + menuCode + '\'' +
                ", menuName='" + menuName + '\'' +
                ", iconPath='" + iconPath + '\'' +
                ", sortNo=" + sortNo +
                '}';
    }
}
