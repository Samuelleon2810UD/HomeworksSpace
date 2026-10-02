package com.memento_text.originator;

import com.memento_text.memento.Memento;

public class Editor {
    private String content = "";

    public Memento save() {
        return new Memento(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void restore(Memento memento) {
        content = memento.getContent();
    }
}
