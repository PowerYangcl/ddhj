package cn.com.ddhj.service.search;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.solr.data.SolrData;
import cn.com.ddhj.solr.server.SolrSearchServer;
import cn.com.ddhj.solr.util.SolrDataUtil;
import cn.com.ddhj.util.Constant;

/**
 * solr索引库数据封装类
 *
 */
@Service
public class SolrDataService {
	
	@Resource
	private TLandedPropertyMapper lrMapper;
	/***
	 * 数据封装方法  
	 * @param sellerCode   那个系统的分区
	 * @param productCode  修改单个商品的商品编号
	 * @return
	 */
	public boolean getListSolrData(String lpCode){
		boolean retb = false;
		List<SolrData> list = new SolrDataUtil().addSolrData(lpCode, lrMapper);
		if(list != null && !list.isEmpty()) {
			new SolrSearchServer().addSolrDataIndex(list, Constant.SELLER_CODE);
			retb = true;
		}
		return retb;
	}
	
	
	/***
	 * 数据封装方法  
	 * @param sellerCode   那个系统的分区
	 * @param productCode  修改单个商品的商品编号
	 * @return
	 */
	public boolean deleteSolrIndex(boolean abs, String productCode) {
		boolean retb = false;
//		if(StringUtils.isNotEmpty(productCode)) {
//			String statusSql = "select seller_code,product_status  from productcenter.pc_productinfo where product_code=:productCode";
//			Map<String, Object> mapStatus = DbUp.upTable("pc_productinfo").dataSqlOne(statusSql, new MDataMap("productCode",productCode));
//			if(null==mapStatus ||"".equals(mapStatus)){  
//				return false;
//			}
//			
//			if(abs) {
//				new SolrSearchServer().delSolrDataOne(productCode, mapStatus.get("seller_code").toString());
//				retb=true;
//			} else {
//				/**
//				 * 判断当前商品是不是上架状态 ,如果该商品为下架状态 直接删除索引
//				 */
//				if("4497153900060002".equals(mapStatus.get("product_status").toString().trim())) {
//					List<SolrData> solrdata = new SolrDataUtil().addSolrData(mapStatus.get("seller_code").toString(),productCode);
//					if(solrdata!=null && !solrdata.equals("")){
//						new SolrSearchServer().addSolrDataOne(solrdata.get(0), mapStatus.get("seller_code").toString());
//						retb=true;
//					}else{
//						//将需要过滤不允许搜索到的商品索引删除
//						new SolrSearchServer().delSolrDataOne(productCode, mapStatus.get("seller_code").toString());
//						retb=true;
//					}
//					
//				}else{
//					new SolrSearchServer().delSolrDataOne(productCode, mapStatus.get("seller_code").toString());
//					retb=true;
//				}
//			}
//		}
		return retb;
	}
	
	
}
