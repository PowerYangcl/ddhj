package cn.com.ddhj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.service.ITLandedPropertyService;
import cn.com.ddhj.util.DateUtil;
import cn.com.ddhj.util.PureNetUtil;

/**
 * 
 * 类: TLandedPropertyServiceImpl <br>
 * 描述: 地产楼盘列表业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午5:31:35
 */
@Service
public class TLandedPropertyServiceImpl extends BaseServiceImpl<TLandedProperty, TLandedPropertyMapper, BaseDto>
		implements ITLandedPropertyService {

	@Autowired
	private TLandedPropertyMapper mapper;

	/**
	 * 
	 * 方法: insertDataFromAPI <br>
	 * 描述: TODO
	 * 
	 * @see cn.com.ddhj.service.ITLandedPropertyService#insertDataFromAPI()
	 */
	@Override
	public void insertDataFromAPI() {
		String key = "f74ac8fbf0d992b02420a03387ed8341";
		String url = "http://v.juhe.cn/estate/query";
		Map<String, String> param = new HashMap<String, String>();
		param.put("city", "北京");
		param.put("q", "");
		param.put("key", key);
		param.put("page", String.valueOf(1));
		param.put("dtype", "json");
		String responseJson = PureNetUtil.post(url, param);
		if (responseJson != null && !"".equals(responseJson)) {
			JSONObject result = JSONObject.parseObject(responseJson);
			if (result != null && result.getString("result") != null && !"".equals(result.getString("result"))) {
				JSONArray array = JSONArray.parseArray(result.getString("result"));
				List<TLandedProperty> list = new ArrayList<TLandedProperty>();
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = array.getJSONObject(i);
					TLandedProperty model = obj.toJavaObject(TLandedProperty.class);
					model.setUuid(UUID.randomUUID().toString().replace("-", ""));
					model.setCode(WebHelper.getInstance().getUniqueCode("LP"));
					model.setCreateUser("system");
					model.setCreateTime(DateUtil.getSysDateTime());
					model.setUpdateUser("system");
					model.setUpdateTime(DateUtil.getSysDateTime());
					list.add(model);
				}

				mapper.batchInsertTLandedProperty(list);
			}
		}
	}

}
