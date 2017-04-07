package com.lpk.energy.weather;

/**
 * Created by user on 2017-04-07.
 */
public class weatherDo {
    private int hour;
    private double temp;
    private String wfEn;
    public  weatherDo()
    {}
    public weatherDo(int hour, double temp, String wfEn) {
        this.hour = hour;
        this.temp = temp;
        this.wfEn = wfEn;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getWfEn() {
        return wfEn;
    }

    public void setWfEn(String wfEn) {
        this.wfEn = wfEn;
    }
}
