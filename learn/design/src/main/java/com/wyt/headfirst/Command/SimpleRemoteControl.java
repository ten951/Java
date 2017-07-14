package com.wyt.headfirst.Command;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/21.
 */
public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl() {
    }


    public void setCommand(Command slot) {
        this.slot = slot;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
