package cn.com.ddhj.model.lp;

import java.math.BigDecimal;

import cn.com.ddhj.model.BaseModel;

public class TLpEnvironment extends BaseModel {

	private String lpCode;

	private String city;

	private BigDecimal air;

	private BigDecimal afforest;

	private BigDecimal volume;

	private BigDecimal water;

	private BigDecimal rubbish;

	private BigDecimal chemical;

	private BigDecimal nosie;

	private BigDecimal radiation;

	private BigDecimal hazardousArticle;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLpCode() {
		return lpCode;
	}

	public void setLpCode(String lpCode) {
		this.lpCode = lpCode;
	}

	public BigDecimal getAir() {
		return air;
	}

	public void setAir(BigDecimal air) {
		this.air = air;
	}

	public BigDecimal getAfforest() {
		return afforest;
	}

	public void setAfforest(BigDecimal afforest) {
		this.afforest = afforest;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getWater() {
		return water;
	}

	public void setWater(BigDecimal water) {
		this.water = water;
	}

	public BigDecimal getRubbish() {
		return rubbish;
	}

	public void setRubbish(BigDecimal rubbish) {
		this.rubbish = rubbish;
	}

	public BigDecimal getChemical() {
		return chemical;
	}

	public void setChemical(BigDecimal chemical) {
		this.chemical = chemical;
	}

	public BigDecimal getNosie() {
		return nosie;
	}

	public void setNosie(BigDecimal nosie) {
		this.nosie = nosie;
	}

	public BigDecimal getRadiation() {
		return radiation;
	}

	public void setRadiation(BigDecimal radiation) {
		this.radiation = radiation;
	}

	public BigDecimal getHazardousArticle() {
		return hazardousArticle;
	}

	public void setHazardousArticle(BigDecimal hazardousArticle) {
		this.hazardousArticle = hazardousArticle;
	}

}