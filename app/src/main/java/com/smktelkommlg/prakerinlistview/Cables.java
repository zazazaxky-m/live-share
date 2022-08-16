package com.smktelkommlg.prakerinlistview;

public class Cables {
    int id;
    String site_a, site_b, type, status;

    public Cables(int id, String site_a, String site_b, String type, String status){
        this.id = id;
        this.site_a = site_a;
        this.site_b = site_b;
        this.type = type;
        this.status = status;

    }
    public int getId(){
        return id;
    }
    public String getSite_a() { return site_a;}
    public String getSite_b() { return site_b;}
    public String getType() { return type;}
    public String getStatus() { return status;}



}
