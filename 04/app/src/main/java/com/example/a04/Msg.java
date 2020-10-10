package com.example.a04;

public class Msg {
    public static final int Type_Received=0;
    private static final int Type_send=0;
    private String content;
    private int type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

}
