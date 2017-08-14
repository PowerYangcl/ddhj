package cn.com.ddhj.model.lp;

import cn.com.ddhj.model.BaseModel;

/**
 * 楼盘环境参数
 * 
 * @author user
 *
 */
public class TLpEnvironment extends BaseModel {

	private String lpCode;

	private Integer air;
	private String airDesc;

	private Integer afforest;
	private String afforestDesc;

	private Integer volume;
	private String volumeDesc;

	private Integer water;
	private String waterDesc;

	private Integer rubbish;
	private String rubbishDesc;

	private Integer chemical;
	private String chemicalDesc;

	private Integer nosie;
	private String nosieDesc;

	private Integer radiation;
	private String radiationDesc;

	private Integer hazardousArticle;
	private String hazardousArticleDesc;

	public Integer getHazardousArticle() {
		return hazardousArticle;
	}

	public void setHazardousArticle(Integer hazardousArticle) {
		this.hazardousArticle = hazardousArticle;
	}

	public String getHazardousArticleDesc() {
		return hazardousArticleDesc;
	}

	public void setHazardousArticleDesc(String hazardousArticleDesc) {
		this.hazardousArticleDesc = hazardousArticleDesc;
	}

	public Integer getRadiation() {
		return radiation;
	}

	public void setRadiation(Integer radiation) {
		this.radiation = radiation;
	}

	public String getRadiationDesc() {
		return radiationDesc;
	}

	public void setRadiationDesc(String radiationDesc) {
		this.radiationDesc = radiationDesc;
	}

	public String getAirDesc() {
		return airDesc;
	}

	public void setAirDesc(String airDesc) {
		this.airDesc = airDesc;
	}

	public String getAfforestDesc() {
		return afforestDesc;
	}

	public void setAfforestDesc(String afforestDesc) {
		this.afforestDesc = afforestDesc;
	}

	public String getVolumeDesc() {
		return volumeDesc;
	}

	public void setVolumeDesc(String volumeDesc) {
		this.volumeDesc = volumeDesc;
	}

	public String getWaterDesc() {
		return waterDesc;
	}

	public void setWaterDesc(String waterDesc) {
		this.waterDesc = waterDesc;
	}

	public String getRubbishDesc() {
		return rubbishDesc;
	}

	public void setRubbishDesc(String rubbishDesc) {
		this.rubbishDesc = rubbishDesc;
	}

	public String getChemicalDesc() {
		return chemicalDesc;
	}

	public void setChemicalDesc(String chemicalDesc) {
		this.chemicalDesc = chemicalDesc;
	}

	public String getNosieDesc() {
		return nosieDesc;
	}

	public void setNosieDesc(String nosieDesc) {
		this.nosieDesc = nosieDesc;
	}

	public String getLpCode() {
		return lpCode;
	}

	public void setLpCode(String lpCode) {
		this.lpCode = lpCode;
	}

	public Integer getAir() {
		return air;
	}

	public void setAir(Integer air) {
		this.air = air;
	}

	public Integer getAfforest() {
		return afforest;
	}

	public void setAfforest(Integer afforest) {
		this.afforest = afforest;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Integer getWater() {
		return water;
	}

	public void setWater(Integer water) {
		this.water = water;
	}

	public Integer getRubbish() {
		return rubbish;
	}

	public void setRubbish(Integer rubbish) {
		this.rubbish = rubbish;
	}

	public Integer getChemical() {
		return chemical;
	}

	public void setChemical(Integer chemical) {
		this.chemical = chemical;
	}

	public Integer getNosie() {
		return nosie;
	}

	public void setNosie(Integer nosie) {
		this.nosie = nosie;
	}

}