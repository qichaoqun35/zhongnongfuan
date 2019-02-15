package com.zhongnongfuan.app.bean;

/**
 * @author qichaoqun
 * @date 2019/1/19
 */
public class Machine {
    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Machine() {
    }

    public Machine(String machineName, String location, String condition) {
        this.machineName = machineName;
        this.location = location;
        this.condition = condition;
    }


    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    private String machineName;
    private String location;
    private String condition;

}
