package cn.com.ddhj.service.impl.adver;

import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.adver.TUserAdvertisingDto;
import cn.com.ddhj.mapper.adver.TUserAdvertisingMapper;
import cn.com.ddhj.model.adver.TUserAdvertising;
import cn.com.ddhj.service.adver.ITUserAdvertisingService;
import cn.com.ddhj.service.impl.BaseServiceImpl;

@Service
public class TUserAdvertisingServiceImpl
		extends BaseServiceImpl<TUserAdvertising, TUserAdvertisingMapper, TUserAdvertisingDto>
		implements ITUserAdvertisingService {

}
