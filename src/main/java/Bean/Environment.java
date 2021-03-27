package Bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: project2->Bean->Environment
 * @description: 封装数据日志文件
 * @author: Mr.黄
 * @create: 2019-09-29 09:51
 **/
public class Environment implements Serializable {
    private String srcId;
    private String dstID;
    private String devId;
    private String sersorAddress;
    private String count;
    private String cmd;
    private String temperature;
    private String humidity;
    private String sunshine;
    private String co2;
    private String status;
    private String gather_date;

    public Environment() {
    }

    public Environment(String srcId, String dstID,
                       String devId, String sersorAddress,
                       String count, String cmd,
                       String temperature, String humidity,
                       String sunshine, String co2,
                       String status, String gather_date) {
        this.srcId = srcId;
        this.dstID = dstID;
        this.devId = devId;
        this.sersorAddress = sersorAddress;
        this.count = count;
        this.cmd = cmd;
        this.temperature = temperature;
        this.humidity = humidity;
        this.sunshine = sunshine;
        this.co2 = co2;
        this.status = status;
        this.gather_date = gather_date;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getDstID() {
        return dstID;
    }

    public void setDstID(String dstID) {
        this.dstID = dstID;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getSersorAddress() {
        return sersorAddress;
    }

    public void setSersorAddress(String sersorAddress) {
        this.sersorAddress = sersorAddress;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getSunshine() {
        return sunshine;
    }

    public void setSunshine(String sunshine) {
        this.sunshine = sunshine;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGather_date() {
        return gather_date;
    }

    public void setGather_date(String gather_date) {
        this.gather_date = gather_date;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "srcId='" + srcId + '\'' +
                ", dstID='" + dstID + '\'' +
                ", devId='" + devId + '\'' +
                ", sersorAddress='" + sersorAddress + '\'' +
                ", count='" + count + '\'' +
                ", cmd='" + cmd + '\'' +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", sunshine='" + sunshine + '\'' +
                ", co2='" + co2 + '\'' +
                ", status='" + status + '\'' +
                ", gather_date='" + gather_date + '\'' +
                '}';
    }
}
