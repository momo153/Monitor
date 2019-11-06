package com.petrochina.e7.monitor.commons.core.domain;

public class Tree {

    private String text;
    private String href = "#" + text;
    private Tree[] nodes ;

    public Tree() {
    }

    public Tree(String text) {
        this.text = text;
    }

    public Tree(String text, String href) {
        this.text = text;
        this.href = href;
    }

    public Tree(String text, Tree[] nodes) {
        this.text = text;
        this.nodes = nodes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Tree[] getNodes() {
        return nodes;
    }

    public void setNodes(Tree[] nodes) {
        this.nodes = nodes;
    }
}
