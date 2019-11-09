package com.lenovo.manufacture.hxf.Bean;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("pm2.5")
    private int Pm25; // FIXME check this code
    private int co2;
    private int LightIntensity;
    private int humidity;
    private int temperature;
    private String RESULT;
    private String ERRMSG;

    public Person() {
    }

    public Person(int pm25, int co2, int lightIntensity, int humidity, int temperature, String RESULT, String ERRMSG) {
        Pm25 = pm25;
        this.co2 = co2;
        LightIntensity = lightIntensity;
        this.humidity = humidity;
        this.temperature = temperature;
        this.RESULT = RESULT;
        this.ERRMSG = ERRMSG;
    }

    public int getPm25() {
        return Pm25;
    }

    public void setPm25(int Pm25) {
        this.Pm25 = Pm25;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getLightIntensity() {
        return LightIntensity;
    }

    public void setLightIntensity(int LightIntensity) {
        this.LightIntensity = LightIntensity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }
}
