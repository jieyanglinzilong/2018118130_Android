package com.example.a04;

public class Msg {
    public static final int Type_Received=0;
    public static final int Type_send=1;
    private String content;
    private int type;

    public String getContent() {
        return content;
    }


    public int getType() {
        return type;
    }


    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

}
