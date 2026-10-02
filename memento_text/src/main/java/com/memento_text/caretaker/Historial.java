package com.memento_text.caretaker;

import java.util.Stack;

import com.memento_text.memento.Memento;
import com.memento_text.originator.Editor;

public class Historial {
    private Stack<Memento> mementos = new Stack<>();
    private final Editor editor;

    public Historial(Editor editor) {
        this.editor = editor;
    }

    public void hitSave() {
        mementos.push(editor.save());
    }

    public boolean hitUndo() {
        if (mementos.isEmpty()) {
            return false;
        }

        Memento memento = mementos.pop();
        editor.restore(memento);
        return true;
    }

}
