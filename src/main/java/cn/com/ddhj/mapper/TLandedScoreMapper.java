package cn.com.ddhj.mapper;

import java.util.List;
import java.util.Map;

import cn.com.ddhj.model.TLandedScore;
import cn.com.ddhj.result.LandedScoreResult;

public interface TLandedScoreMapper {

	public int insertSelective(TLandedScore ls);

	public List<LandedScoreResult> findLandedScoreAverage(Map<String, String> param);    
     
}