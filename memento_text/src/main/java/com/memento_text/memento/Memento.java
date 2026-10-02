package com.memento_text.memento;

public class Memento {
    
    private final String content;

    public Memento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
