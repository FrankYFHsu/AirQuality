package tw.edu.ncu.ce.networkprogramming.airquality;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpclab on 2015/5/28.
 */
public class AirQualityData {

    private String SiteName;
    private String County;
    private String PSI;
    private String MajorPollutant;
    private String Status;
    private String SO2;
    private String CO;
    private String O3;
    private String PM10;

    @SerializedName("PM2.5")
    private String PM2_5;//PM2.5 in JSON

    private String NO2;
    private String WindSpeed;
    private String WindDirec;
    private String FPMI;
    private String PublishTime;

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String siteName) {
        SiteName = siteName;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }

    public String getPSI() {
        return PSI;
    }

    public void setPSI(String PSI) {
        this.PSI = PSI;
    }

    public String getMajorPollutant() {
        return MajorPollutant;
    }

    public void setMajorPollutant(String majorPollutant) {
        MajorPollutant = majorPollutant;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSO2() {
        return SO2;
    }

    public void setSO2(String SO2) {
        this.SO2 = SO2;
    }

    public String getCO() {
        return CO;
    }

    public void setCO(String CO) {
        this.CO = CO;
    }

    public String getO3() {
        return O3;
    }

    public void setO3(String o3) {
        O3 = o3;
    }

    public String getPM10() {
        return PM10;
    }

    public void setPM10(String PM10) {
        this.PM10 = PM10;
    }

    public String getPM2_5() {
        return PM2_5;
    }

    public void setPM2_5(String PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public String getNO2() {
        return NO2;
    }

    public void setNO2(String NO2) {
        this.NO2 = NO2;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        WindSpeed = windSpeed;
    }

    public String getWindDirec() {
        return WindDirec;
    }

    public void setWindDirec(String windDirec) {
        WindDirec = windDirec;
    }

    public String getFPMI() {
        return FPMI;
    }

    public void setFPMI(String FPMI) {
        this.FPMI = FPMI;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }


    public List<String> getDetails(){
        List<String> details = new ArrayList<>();

        details.add("空氣污染指標(PSI):"+PSI);
        details.add("二氧化硫濃度:"+SO2);
        details.add("一氧化碳濃度:"+CO);
        details.add("臭氧濃度:"+O3);
        details.add("懸浮微粒濃度(PM10):"+PM10);
        details.add("細懸浮微粒濃度(PM2.5):"+PM2_5);
        details.add("二氧化氮濃度:"+NO2);
        details.add("發布時間:"+PublishTime);

        return details;
    }


}
