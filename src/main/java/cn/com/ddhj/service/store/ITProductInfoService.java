package cn.com.ddhj.service.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.store.TProductInfoDto;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.result.PageResult;
import cn.com.ddhj.result.product.TPageProductListResult;
import cn.com.ddhj.service.IBaseService;

public interface ITProductInfoService extends IBaseService<TProductInfo, TProductInfoDto> {

	PageResult findDataPage(TProductInfoDto dto);

	JSONObject getProductInfo(String productCode);

	TProductInfo selectByCode(String code, HttpServletRequest request);

	TPageProductListResult findProductListPage(TProductInfoDto dto);

	/**
	 * 文件上传
	 * 
	 * @author zhy
	 * @date 2016-04-15
	 * @version 1.0.0
	 * @param uploadFile
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 */
	JSONArray uploadFile(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 删除已上传的文件
	 * 
	 * @author zhy
	 * @date 2016-04-15
	 * @version 1.0.0
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	BaseResult delFile(String file, HttpServletRequest request, HttpServletResponse response);
}
