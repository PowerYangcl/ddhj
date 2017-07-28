package cn.com.ddhj.service.impl.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.store.TAreaDto;
import cn.com.ddhj.mapper.store.TAreaMapper;
import cn.com.ddhj.model.store.TArea;
import cn.com.ddhj.result.DataResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITAreaService;
import cn.com.ddhj.util.Constant;

@Service
public class TAreaServiceImpl extends BaseServiceImpl<TArea, TAreaMapper, TAreaDto> implements ITAreaService {

	@Autowired
	private TAreaMapper mapper;

	@Override
	public DataResult findDataByParent(String parentCode) {
		DataResult result = new DataResult();
		try {
			List<TArea> list = mapper.findDataByParent(parentCode);
			if (list != null && list.size() > 0) {
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("获取城市列表成功");
				result.setData(list);
			} else {
				result.setResultCode(Constant.RESULT_NULL);
				result.setResultMessage("获取城市列表为空");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("获取城市列表失败");
		}
		return result;
	}

}
