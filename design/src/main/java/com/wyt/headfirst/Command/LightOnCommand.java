package com.wyt.headfirst.Command;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/21.
 */
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
