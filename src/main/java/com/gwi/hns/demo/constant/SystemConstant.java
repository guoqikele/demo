package com.gwi.hns.demo.constant;

public enum SystemConstant {
    SESSION_ID("sessionid"), ACCESS_TOKEN("authorization");

    private String attribute;

    private SystemConstant(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

}
