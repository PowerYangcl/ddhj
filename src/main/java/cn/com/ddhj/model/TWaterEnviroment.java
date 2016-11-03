package cn.com.ddhj.model;

import java.util.Date;
/**
 * @description: 水质实体类 
 * 
 * @author Yangcl
 * @home https://github.com/PowerYangcl
 * @date 2016年11月3日 下午2:59:57 
 * @version 1.0.0
 */
public class TWaterEnviroment {
	private Integer id;
    private String uid;
    private String state;
    private String city;
    private Double lat;
    private Double lng;
    private String ph;
    private String phquality;
    private String oxygen;
    private String oxygenquality;
    private String nitrogen;
    private String nitrogenquality;
    private String permangan;
    private String permanganquality;
    private String orgacarbon;
    private String orgacarbonquality;
    private String section;
    private String profile;
    private Date createTime;
    private Date updateTime;
    private Integer type;
    
    
    
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getPhquality() {
		return phquality;
	}
	public void setPhquality(String phquality) {
		this.phquality = phquality;
	}
	public String getOxygen() {
		return oxygen;
	}
	public void setOxygen(String oxygen) {
		this.oxygen = oxygen;
	}
	public String getOxygenquality() {
		return oxygenquality;
	}
	public void setOxygenquality(String oxygenquality) {
		this.oxygenquality = oxygenquality;
	}
	public String getNitrogen() {
		return nitrogen;
	}
	public void setNitrogen(String nitrogen) {
		this.nitrogen = nitrogen;
	}
	public String getNitrogenquality() {
		return nitrogenquality;
	}
	public void setNitrogenquality(String nitrogenquality) {
		this.nitrogenquality = nitrogenquality;
	}
	public String getPermangan() {
		return permangan;
	}
	public void setPermangan(String permangan) {
		this.permangan = permangan;
	}
	public String getPermanganquality() {
		return permanganquality;
	}
	public void setPermanganquality(String permanganquality) {
		this.permanganquality = permanganquality;
	}
	public String getOrgacarbon() {
		return orgacarbon;
	}
	public void setOrgacarbon(String orgacarbon) {
		this.orgacarbon = orgacarbon;
	}
	public String getOrgacarbonquality() {
		return orgacarbonquality;
	}
	public void setOrgacarbonquality(String orgacarbonquality) {
		this.orgacarbonquality = orgacarbonquality;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}