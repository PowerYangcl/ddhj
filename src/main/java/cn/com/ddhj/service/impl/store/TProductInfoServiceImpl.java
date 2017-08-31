package cn.com.ddhj.service.impl.store;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.store.TProductInfoDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TProductInfoMapper;
import cn.com.ddhj.mapper.TProductPicMapper;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.model.TProductPic;
import cn.com.ddhj.result.PageResult;
import cn.com.ddhj.result.file.FileResult;
import cn.com.ddhj.result.product.TPageProductListResult;
import cn.com.ddhj.result.product.TProductInfoResult;
import cn.com.ddhj.service.file.IFileService;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITProductInfoService;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

@Service
public class TProductInfoServiceImpl extends BaseServiceImpl<TProductInfo, TProductInfoMapper, TProductInfoDto>
		implements ITProductInfoService {

	@Autowired
	private TProductInfoMapper mapper;
	@Autowired
	private TProductPicMapper picMapper;
	@Autowired
	private IFileService fileService;

	@Override
	public BaseResult insertSelective(TProductInfo entity) {
		BaseResult result = new BaseResult();
		try {
			if (StringUtils.isNotBlank(entity.getImages())) {
				String[] images = entity.getImages().split(",");
				if (images.length > 0) {
					entity.setMainPicUrl(images[0]);
					result = super.insertSelective(entity);
					if (result.getResultCode() == 1) {
						String time = DateUtil.getSysDateTime();
						List<TProductPic> pics = new ArrayList<TProductPic>();
						// 添加图片到商品图片表
						for (String str : images) {
							TProductPic pic = new TProductPic();
							pic.setUuid(WebHelper.getInstance().genUuid());
							pic.setProductCode(entity.getProductCode());
							pic.setPicUrl(str);
							pic.setCreateUser(entity.getCreateUser());
							pic.setCreateTime(time);
							pics.add(pic);
						}
						picMapper.batchInsert(pics);
					}
				} else {
					result.setResultCode(-1);
					result.setResultMessage("商品图片不能为空");
				}
			} else {
				result.setResultCode(-1);
				result.setResultMessage("商品图片不能为空");
			}
		} catch (Exception e) {
			result.setResultCode(-1);
			result.setResultMessage("添加商品失败，失败原因：" + e.getMessage());
		}
		return result;
	}

	@Override
	public BaseResult updateByCode(TProductInfo entity) {
		BaseResult result = new BaseResult();
		try {
			if (StringUtils.isNotBlank(entity.getImages())) {
				String[] images = entity.getImages().split(",");
				if (images.length > 0) {
					entity.setMainPicUrl(images[0]);
					result = super.updateByCode(entity);
					if (result.getResultCode() == 1) {
						String time = DateUtil.getSysDateTime();
						picMapper.deleteByProductCode(entity.getProductCode());
						List<TProductPic> pics = new ArrayList<TProductPic>();
						// 添加图片到商品图片表
						for (String str : images) {
							TProductPic pic = new TProductPic();
							pic.setUuid(WebHelper.getInstance().genUuid());
							pic.setProductCode(entity.getProductCode());
							pic.setPicUrl(str);
							pic.setCreateUser(entity.getUpdateUser());
							pic.setCreateTime(time);
							pics.add(pic);
						}
						picMapper.batchInsert(pics);
					}
				} else {
					result.setResultCode(-1);
					result.setResultMessage("商品图片不能为空");
				}
			} else {
				result.setResultCode(-1);
				result.setResultMessage("商品图片不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(-1);
			result.setResultMessage("编辑商品失败，失败原因：" + e.getMessage());
		}
		return result;
	}

	@Override
	public TProductInfo selectByCode(String code, HttpServletRequest request) {
		TProductInfo info = super.selectByCode(code);
		if (info != null) {
			/**
			 * 查询商品图片合集
			 */
			List<TProductPic> list = picMapper.selectByProductCode(code);
			if (list != null && list.size() > 0) {
				JSONArray array = new JSONArray();
				List<String> imgs = new ArrayList<String>();
				JSONArray pics = new JSONArray();
				for (TProductPic pic : list) {
					String url = pic.getPicUrl();
					String img = "<img src='" + pic.getPicUrl() + "' class='file-preview-image'>";
					imgs.add(img);
					JSONObject obj = new JSONObject();
					obj.put("caption", url);
					obj.put("width", "120px");
					obj.put("url", "delfile.htm?uuid=" + pic.getUuid());
					obj.put("key", pic.getId());
					array.add(obj);
					JSONObject picObj = new JSONObject();
					picObj.put("path", url.substring(0, url.lastIndexOf("/")));
					picObj.put("name", url.substring(url.lastIndexOf("/") + 1, url.length()));
					pics.add(picObj);
				}
				info.setPics(JSONArray.toJSONString(pics));
				info.setInitialPreview(JSONArray.toJSONString(imgs));
				info.setInitialPreviewConfig(JSONArray.toJSONString(array));
			}
		}
		return info;
	}

	/**
	 * 查询商品列表.后台管理使用
	 */
	public PageResult findDataPage(TProductInfoDto dto) {
		PageResult result = new PageResult();
		PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
		List<TProductInfo> list = mapper.findEntityAll(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(Constant.RESULT_SUCCESS);
		} else {
			list = new ArrayList<TProductInfo>();
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("查询商品列表为空");
		}
		PageInfo<TProductInfo> page = new PageInfo<TProductInfo>(list);
		result.setPage(page);
		return result;
	}

	/**
	 * 查询商品列表.接口使用
	 * 
	 * @author zht
	 * @param dto
	 * @return
	 */
	public TPageProductListResult findProductListPage(TProductInfoDto dto) {
		TPageProductListResult recResult = new TPageProductListResult();
		PageResult result = findDataPage(dto);
		if (result.getResultCode() > 0) {
			recResult.setProductList(result.getPage().getList());
			recResult.setRecCount(result.getPage().getTotal());
		}
		recResult.setResultCode(result.getResultCode());
		recResult.setResultMessage(result.getResultMessage());
		return recResult;
	}

	/**
	 * @description: 获取商品详细信息
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=product_detail&api_key=appfamilyhas&apiInput={"productCode":"801613242"} @返回参数如下：
	 *                                                                                                                               {
	 *                                                                                                                               "resultCode":
	 *                                                                                                                               1,
	 *                                                                                                                               "resultMessage":
	 *                                                                                                                               "查询成功",
	 *                                                                                                                               "productName":
	 *                                                                                                                               "测试0",
	 *                                                                                                                               "stockNum":
	 *                                                                                                                               99,
	 *                                                                                                                               "productCode":
	 *                                                                                                                               "801613242",
	 *                                                                                                                               "discriptPicList":
	 *                                                                                                                               [
	 *                                                                                                                               "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/275b1/f56c0f2c02fd44dc85428116438e4c8f.jpg",
	 *                                                                                                                               "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/275b1/5c3c40cebeab451cac0548cee4875ada.jpg",
	 *                                                                                                                               "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/275b1/c8085670a5d44aa0b01c6b675881ae9b.jpg",
	 *                                                                                                                               "http://image-family.huijiayou.cn/cfiles/staticfiles/imzoom/29a16/7612922a79ce4faa92884678e9093e69.jpg",
	 *                                                                                                                               "http://image-family.huijiayou.cn/cfiles/staticfiles/imzoom/29a16/6ac99b8bb2c945c09b65cc53ca5ecf73.jpg"
	 *                                                                                                                               ],
	 *                                                                                                                               "productTip":
	 *                                                                                                                               "",
	 *                                                                                                                               "mainpicUrl":
	 *                                                                                                                               [
	 *                                                                                                                               "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/2729e/31c4f7b50a9e4a779dd7b57ccbf0b1aa.jpg"
	 *                                                                                                                               ],
	 *                                                                                                                               "currentPrice":
	 *                                                                                                                               1000,
	 *                                                                                                                               "telCS":
	 *                                                                                                                               "010-66668888",
	 *                                                                                                                               "QQCS":
	 *                                                                                                                               "66828979658"
	 *                                                                                                                               }
	 * 
	 * @param productCode
	 * @author Yangcl
	 * @date 2017年7月26日 下午3:34:47
	 * @version 1.0.0.1
	 */
	public JSONObject getProductInfo(String productCode) {
		JSONObject re = new JSONObject();
		if (StringUtils.isBlank(productCode)) {
			re.put("resultCode", -1);
			re.put("resultMessage", "商品编号不得为空");
			return re;
		}
		TProductInfoResult e = mapper.getProductInfo(productCode);
		if (e == null) {
			re.put("resultCode", -1);
			re.put("resultMessage", "未找到对应的商品信息");
			return re;
		}

		re.put("resultCode", 1);
		re.put("resultMessage", "查询成功");
		re.put("productCode", e.getProductCode());
		re.put("productName", e.getProductName());
		if (e.getMainPicUrl() != null) {
			re.put("mainpicUrl", new ArrayList<String>(Arrays.asList(e.getMainPicUrl().split(","))));
		} else {
			re.put("mainpicUrl", "");
		}
		re.put("currentPrice", e.getCurrentPrice());
		re.put("stockNum", e.getStockNum());

		List<TProductPic> list = picMapper.selectByProductCode(productCode);
		if (list != null) {
			List<String> pics = new ArrayList<>();
			for (TProductPic p : list) {
				pics.add(p.getPicUrl());
			}
			re.put("discriptPicList", pics);
		} else {
			re.put("discriptPicList", "");
		}
		re.put("productTip", e.getProductTip());
		re.put("telCS", "010-66668888");
		re.put("QQCS", "66828979658");
		return re;
	}

	@Override
	public JSONArray uploadFile(HttpServletRequest request, HttpServletResponse response) {
		JSONArray array = new JSONArray();
		try {
			List<FileResult> list = fileService.uploadProductImg(request);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					FileResult result = list.get(i);
					JSONObject fileObj = new JSONObject();
					fileObj.put("path", result.getUrl());
					fileObj.put("name", result.getName());
					array.add(fileObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public BaseResult delFile(String uuid, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			if (StringUtils.isNotBlank(uuid)) {
				picMapper.deleteByUuid(uuid);
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("删除成功");
			} else {
				result.setResultCode(0);
				result.setResultMessage("文件不存在");
			}
		} catch (Exception e) {
			result.setResultCode(0);
			result.setResultMessage("文件删除失败，失败原因：" + e.getMessage());
		}
		return result;
	}
}
