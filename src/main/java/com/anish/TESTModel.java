package com.anish;

import java.time.LocalDateTime;

public class TESTModel {

    private String name ;
    private LocalDateTime localDateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "TESTModel{" +
                "name='" + name + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
