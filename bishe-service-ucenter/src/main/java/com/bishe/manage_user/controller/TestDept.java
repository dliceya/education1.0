package com.bishe.manage_user.controller;

import java.util.List;

public class TestDept {
    private String label;

    private List<TestDept> children;

    public TestDept(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TestDept(String label, List<TestDept> children) {
        this.label = label;
        this.children = children;
    }

    public void setChildren(List<TestDept> children) {
        this.children = children;
    }

    public List<TestDept> getChildren() {
        return children;
    }
}
