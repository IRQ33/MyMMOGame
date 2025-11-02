package com.irq3.mmo.game.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.bladecoder.ink.runtime.Choice;
import com.bladecoder.ink.runtime.Story;

import java.util.List;

public class DialogManager {
    String dialog;
    Story story;
    boolean choice=false;
    String line;
    private List<Choice> choices;
    boolean finished = false;

    public DialogManager(String name) {
        FileHandle file = Gdx.files.internal(name);
        dialog = file.readString();
        try {
            story = new Story(dialog);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void continute()
    {
        if(story.canContinue())
        {
            try {
                choice = false;
                this.line = story.Continue();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }else if(!story.getCurrentChoices().isEmpty())
        {
            choice = true;
            choices = story.getCurrentChoices();
        }else {
            finished = true;
            choice = false;
        }
    }
    public void choose(int index)
    {
        try {
            story.chooseChoiceIndex(index);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isChoice() {
        return choice;
    }

    public String getLine() {
        return line;
    }
}
