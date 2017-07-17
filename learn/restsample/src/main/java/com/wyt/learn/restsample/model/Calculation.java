package com.wyt.learn.restsample.model;

import java.util.List;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public class Calculation {
    String function;
    private List<String> input;
    private List<String> output;

    public Calculation(List<String> input, List<String> output, String function) {
        this.function = function;
        this.input = input;
        this.output = output;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }
}
