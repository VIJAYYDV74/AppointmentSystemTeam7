package com.team7.appointmentsystem.resultapis;


public class HomepageAPI1 {
    private String businessName;
    private int count;

    public HomepageAPI1(){

    }

    public HomepageAPI1(String businessName, int count) {
        this.businessName = businessName;
        this.count = count;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "HomepageAPI1{" +
                "businessName='" + businessName + '\'' +
                ", count=" + count +
                '}';
    }
}
