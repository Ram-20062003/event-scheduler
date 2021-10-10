package com.example.eventscheduler;

public class Display_Item {
    private String name;
    private String alarm;

    public Display_Item(String name, String alarm) {
        this.name = name;
        this.alarm = alarm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }
}
