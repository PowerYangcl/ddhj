package cn.com.ddhj.model;

import java.util.Date;

/**
 * @descriptions 【7*24小时城市天气空气质量预报】
 *
 * @author Yangcl 
 * @home https://github.com/PowerYangcl
 * @date 2016年12月7日 下午9:44:22
 * @version 1.0.1
 */
public class TCityWeatherForecast {
    private Integer id;
    private String province;
    private String city;
    private String area;
    private String areaid;
    private Double lon;
    private Double lat;
    private String sevenAqi;						// 【根据地区编码查询每日AQI 均值】未来7天的数据
    private String fifteenWeather;			// 【15天概览天气预报数据】
    private Date createTime;
    private Date updateTime;

    
    
    
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getSevenAqi() {
        return sevenAqi;
    }

    public void setSevenAqi(String sevenAqi) {
        this.sevenAqi = sevenAqi == null ? null : sevenAqi.trim();
    }

    public String getFifteenWeather() {
        return fifteenWeather;
    }

    public void setFifteenWeather(String fifteenWeather) {
        this.fifteenWeather = fifteenWeather == null ? null : fifteenWeather.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}