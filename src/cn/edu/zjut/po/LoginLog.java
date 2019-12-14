package cn.edu.zjut.po;

import java.sql.Timestamp;
import java.util.Objects;

public class LoginLog {
    private long id;
    private int userId;
    private Timestamp loginTime;
    private String ip;
    private String device;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginLog loginLog = (LoginLog) o;
        return id == loginLog.id &&
                userId == loginLog.userId &&
                Objects.equals(loginTime, loginLog.loginTime) &&
                Objects.equals(ip, loginLog.ip) &&
                Objects.equals(device, loginLog.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, loginTime, ip, device);
    }
}
