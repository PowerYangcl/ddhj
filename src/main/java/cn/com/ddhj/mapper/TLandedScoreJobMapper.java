package cn.com.ddhj.mapper;

import cn.com.ddhj.model.TLandedScoreJob;

public interface TLandedScoreJobMapper {

	public int insertSelectiveYear(TLandedScoreJob ls);
	
	public int insertSelectiveQuarter(TLandedScoreJob ls);
	
	public int insertSelectiveMonth(TLandedScoreJob ls);
     
}