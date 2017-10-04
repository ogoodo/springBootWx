package com.ogoodo.wx.utils.valid.converter;

public enum FuncEnum implements IBaseEnum {
    AVG("avg", "func-avg"),
    MAX("max", "func-max"),
    MIN("min", "func-min"),
    SUM("sum", "func-sum"),
    LAST("last", "func-last");

    private String name;
    private String desc;

    FuncEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
