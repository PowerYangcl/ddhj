package cn.com.ddhj.mapper;

import java.util.Map;

import cn.com.ddhj.model.TLandedScoreJob;

public interface TLandedScoreJobMapper {

	public int countLandScoreYear(Map<String, String> param);
	public int insertSelectiveYear(TLandedScoreJob ls);
	public int updateTLandScoreYear(TLandedScoreJob ls);
	
	public int countLandScoreQuarter(Map<String, String> param);
	public int insertSelectiveQuarter(TLandedScoreJob ls);
	public int updateTLandScoreQuarter(TLandedScoreJob ls);
	
	public int countLandScoreMonth(Map<String, String> param);
	public int insertSelectiveMonth(TLandedScoreJob ls);
	public int updateTLandScoreMonth(TLandedScoreJob ls);
     
}