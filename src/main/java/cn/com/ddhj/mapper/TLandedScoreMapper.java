package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.LandedScoreAverageDto;
import cn.com.ddhj.model.TLandedScore;
import cn.com.ddhj.result.LandedScoreResult;

public interface TLandedScoreMapper {

	public int insertSelective(TLandedScore ls);

	public List<LandedScoreResult> findLandedScoreAverage(LandedScoreAverageDto dto);     
	
	public List<LandedScoreResult> findLandedScoreAverageYear(LandedScoreAverageDto dto);     
     
	public List<LandedScoreResult> findLandedScoreAverageQuarter(LandedScoreAverageDto dto);   
	
	public List<LandedScoreResult> findLandedScoreAverageMonth(LandedScoreAverageDto dto);   
}