package cn.com.ddhj.service.store;

import cn.com.ddhj.dto.store.TAreaDto;
import cn.com.ddhj.model.store.TArea;
import cn.com.ddhj.result.DataResult;
import cn.com.ddhj.service.IBaseService;

public interface ITAreaService extends IBaseService<TArea, TAreaDto>{

	DataResult findDataByParent(String parentCode);
}
