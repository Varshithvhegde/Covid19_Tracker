package com.example.covid19tracker;

public class Model {
    private final String sname;
    private final String dname;
    private final long death;
    private final long cured;
    private final long active;

    public Model(String sname,String dname, long death, long recovered,
                 long active)
    {
        this.sname = sname;
        this.dname = dname;
        this.death = death;
        this.cured = recovered;
        this.active = active;

    }

    public String getSname() {
        return sname;
    }

    public String getDname() {
        return dname;
    }

    public long getDeath() {
        return death;
    }

    public long getCured() {
        return cured;
    }

    public long getActive() {
        return active;
    }

}