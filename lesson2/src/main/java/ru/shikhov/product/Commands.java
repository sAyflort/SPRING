package ru.shikhov.product;

public enum Commands {
    NEWCART("NEWCART"),
    SHOWPRODUCT("SHOWPRODUCT"),
    SHOWCART("SHOWCART"),
    ADD("ADD"),
    END("END"),
    DELETE("DEL");

    public String command;

    Commands(String command) {
        this.command = command;
    }
}
