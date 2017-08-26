package cn.com.ddhj.mapper.adver;

import cn.com.ddhj.dto.adver.TUserAdvertisingDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.adver.TUserAdvertising;

public interface TUserAdvertisingMapper extends BaseMapper<TUserAdvertising, TUserAdvertisingDto> {

	TUserAdvertising selectByUserAndAdver(TUserAdvertising entity);

	Integer deleteByUserAndAdver(TUserAdvertising entity);
}