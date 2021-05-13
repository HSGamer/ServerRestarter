package me.hsgamer.serverrestarter.api;

public abstract class Action {
    protected boolean valid;
    protected String data;

    protected Action(String data) {
        this.valid = true;
        this.data = data;
    }

    public abstract void execute();
}
