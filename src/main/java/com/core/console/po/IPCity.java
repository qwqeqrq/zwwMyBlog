package com.core.console.po;

import java.io.Serializable;

/**
 * Created by zww on 2019-08-19.ip地区类 淘宝查ip之后未被使用
 */
public class IPCity implements Serializable {

    private String country;//国家
    private String city;//城市
    private String isp;//运营商

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }


    public String toString(String ip) {
        return "IP{"+ ip +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", isp='" + isp + '\'' +
                '}';
    }
}
