package cn.com.ddhj.solr.service;

/***
 * 声明：该方法只供solr5.2.1及以上版本调用   
 *     
 * 该方法只为使用solrj创建索引库提供基础数据
 * 描述：该方法为solr提供数据的方法封装，该方法查询出来的所有的商品都为上架状态，
 *     下架的商品不会被添加到索引库
 *     
 * 创建作者 : zhouguohui 
 * 创建时间 : 2015-08-15  
 * 文件版本 : V1.0 
 * 修改历史 : V1.0
 */
public class SolrDataUtil {
//	PlusServiceSeller plusSeller = new PlusServiceSeller();
//	PlusSupportStock plusStock = new PlusSupportStock();
//	
//	/**
//	 * 全量或单个更新索引库方法
//	 * 当sellerCode不为空  productCode为空时  该方法为全量更新索引库   
//	 * 当sellerCode不为空  productCode为不空时  该方法为单个商品索引库更新
//	 * 
//	 * 描述：为什么会返回list集合  因为当初在4.7.2的版本索引库更新单个和全量更新
//	 *     是分开的，导致需要修改很多地方，现在全部调用5.2.1版本都走统一的方法
//	 *     才会这样设计。
//	 * 
//	 * @param sellerCode   系统编号 如：SI2003(惠家有)  SI2009(家有汇)  SI3003(沙皮狗)  
//	 * @param productCode  商品编号(pc_productinfo表中的product_code字段)
//	 * @return  返回所有满足条件的商品基础数据
//	 */
//	public List<SolrData> addSolrData(String sellerCode,String productCode){
//		List<SolrData> list = new ArrayList<SolrData>();
//		PlusSupportProduct psp = new PlusSupportProduct();
//		List<Map<String, Object>> listPro = getProduct(sellerCode,productCode);/***拿到所有商品***/
//		if(listPro==null || listPro.size()<=0){
//			return null;
//		}
//		
//		Map<String,Double> mapPopularityNum = null;/***获取人气值的方法***/
//		
//		if(productCode==null){
//			mapPopularityNum = popularitySumNum(sellerCode);
//		}else{
//			mapPopularityNum = popularityNumOne(sellerCode,productCode);
//		}
//		
//	    Map<String,SolrDataFacet> facetValue= getFacet(sellerCode,productCode);/***获取商品分类的方法***/
//		Map<String,List<String>> mapgifts = getProductGifts(sellerCode, productCode);/**赠品**/
//		
//		Map<String,Integer> sortTop50Map = getTop50Sort(sellerCode, productCode);/**top50排序**/
//		
//		
//		
//		//取活动信息
//		//PlusModelEventSale eventSale = new PlusSupportEvent().upEventSalueByMangeCode(sellerCode);
//		
//		/**
//		 * 开始循环遍历封装List<Product>
//		 * 为创建索引提供数据
//		 */
////		int z = 0;
//		for(Map<String,Object> map : listPro){//缺少标签 
////			System.out.println(z);z++;
//			//Set<String> listActivityNew = new HashSet<String>();
//			SolrData sd = new SolrData();
//			
//			String productCodeValue = map.get("product_code").toString();
//			String productNameVlaue = map.get("product_name").toString();
//			String skuCodeString=(map.get("sku_code_string") == null || map.get("sku_code_string").equals(""))?"":map.get("sku_code_string").toString();
//			sd.setK1(productCodeValue);//商品编号
//			sd.setS9(productNameVlaue);//商品名称不做操作用于显示
//			sd.setS1(productNameVlaue.toLowerCase()+map.get("labels"));//商品名称转小写用于搜索
//			sd.setS3((map.get("brand_name")+"").toLowerCase());//品牌名称  转小写，用于搜索
//			sd.setS4(map.get("mainpic_url")+"");//图片地址
//			
//			String labels = (map.get("labels")==null || map.get("labels").equals(""))?null:map.get("labels").toString();
//			if(labels!=null){
//				String[] labelsS =labels.split(",");
//				List<String> listLabels = new ArrayList<String>();
//				for(int m=0;m<labelsS.length;m++){
//					listLabels.add(labelsS[m]);
//				}
//				sd.setL8(listLabels);  //关键词
//				
//			}
//			
//			sd.setS5(map.get("product_adv")+"");//广告语
//			sd.setS7(skuCodeString);//skuCode 编号
//			sd.setD1((map.get("market_price") == null || map.get("market_price").equals(""))?0.0:Double.parseDouble(map.get("market_price").toString())); //市场价
//			String[] skuCode = skuCodeString==null || skuCodeString.equals("") ? new String[0] : skuCodeString.split(",");
////			
//			if(skuCode.length>0){
//				List<Double> listPrice = new ArrayList<Double>();
//				for(int j=0;j<skuCode.length;j++){
//					PlusModelSkuInfo skuInfo = psp.upSkuInfoBySkuCode(skuCode[j],null);
//					if(skuInfo!=null){
//						listPrice.add(skuInfo.getSellPrice().doubleValue());
////						if(StringUtils.isNotBlank(skuInfo.getSellNote())){
////							listActivityNew.add(skuInfo.getSellNote());
////							//listActivity.add(skuInfo.getSellNote());
////						}
//					}
//			}
//				Collections.sort(listPrice);  
//				sd.setD2(listPrice.size()<0?0.0:listPrice.get(0)); //销售价
//			}else{
//				sd.setD2(0.0); //有问题的商品销售价
//			}
//			
//			sd.setD3(mapPopularityNum.get(productCodeValue)==null?0.0:mapPopularityNum.get(productCodeValue));//人气值
//			sd.setT1((map.get("update_time") == null || map.get("update_time").equals(""))?null:StringUtil.getYear(map.get("update_time").toString())); //更新时间
//			sd.setL2(facetValue.get(productCodeValue)==null?null : facetValue.get(productCodeValue).getOneName());//一级分类名称
//			sd.setL4(facetValue.get(productCodeValue)==null?null : facetValue.get(productCodeValue).getTwoName());//二级分类名称
//			sd.setI1(plusStock.upAllStockForProduct(productCodeValue)<=0?0:1); //商品是否有货
//			sd.setL5(mapgifts.get(productCodeValue));//赠品
//			String  keyWord  =(map.get("keyword") == null || map.get("keyword").equals(""))?"":map.get("keyword").toString();
//			if(keyWord!=null && !keyWord.equals("")){
//				List<String> listKeyWord = new ArrayList<String>();
//				String[] split = keyWord.split(",");
//				for(int i=0;i<split.length;i++){
//					listKeyWord.add(split[i]);
//				}
//			   sd.setL6(listKeyWord);//标签	
//			}
//			
//			
////			if(listActivityNew==null || listActivityNew.size()<=0){
////				if(!PlusHelperEvent.checkEventItem(productCodeValue)){
////					/**添加满减信息**/
////					List<PlusModelFullCutMessage> sale = new PlusServiceSale().getEventMessage(productCodeValue, eventSale,sellerCode);
////					if(sale!=null && sale.size()>0){
////		    			 listActivityNew.add("满减");
////		    		 }
////				}
////			}
////			
////			 List<String>  listActivity = new ArrayList<String>(listActivityNew);
////			
////			//添加  闪购 内购  特价 等标签
////			sd.setL7(listActivity);
//			
//			if("SI2009".equals(sellerCode)){
//				sd.setI2(0);//商品销量
//			}else if("SI2003".equals(sellerCode)){
//				/**20160218 变更销量走redis**/
//				PlusModelProductQuery plusModelProductQuery = new PlusModelProductQuery(productCodeValue);		
//				PlusModelProductSales productSalesValue = new LoadProductSales().upInfoByCode(plusModelProductQuery);
//				sd.setI2(productSalesValue.getFictitionSales30());//销量
//			}else if("SI3003".equals(sellerCode)){
//				/**20160218 变更销量走redis**/
//				PlusModelProductQuery plusModelProductQuery = new PlusModelProductQuery(productCodeValue);		
//				PlusModelProductSales productSalesValue = new LoadProductSales().upInfoByCode(plusModelProductQuery);
//				sd.setI2(productSalesValue.getFictitionSales30());//销量
//			}
////			sd.setI3((map.get("small_seller_code") == null || map.get("small_seller_code").equals(""))?0:map.get("small_seller_code").equals("SF03KJT")||map.get("small_seller_code").equals("SF03MLG")
////					||map.get("small_seller_code").equals("SF03100294")
////					||map.get("small_seller_code").equals("SF03100327")
////					||map.get("small_seller_code").equals("SF03100329")?1:0);//是否海外购 1是海外购 0不是海外购
//			
//			sd.setI3((map.get("small_seller_code") == null || map.get("small_seller_code").equals("")) ? 0 : 
//				(plusSeller.isKJSeller(map.get("small_seller_code").toString()) ? 1 : 0) );   //是否海外购 1是海外购 0不是海外购
//			
//			//top50排序使用
//			sd.setI4(null == sortTop50Map.get(productCodeValue) ? 0 : sortTop50Map.get(productCodeValue));
//			
//			//增加商品所属供应商编号,为按供应商搜索商品使用.转为小写因为
//			//KeySolrParamsQuery.getParams()会把所有查询转为小写.add by zht
//			if(map.get("small_seller_code") != null && !map.get("small_seller_code").equals("")) {
//				sd.setS16(map.get("small_seller_code").toString().toLowerCase());
//			}
//			
//			list.add(sd);
//		}
//		return list;
//		
//	}
//	
//	
//	
//	/**
//	 * 查询当前sellerCode 或 productCode 对应的上架商品
//	 * 
//	 * 当sellerCode不为空  productCode为空时   查询全部sellerCode对应的商品数据
//	 * 当sellerCode不为空  productCode为不空时   查询productCode对应的商品数据
//	 * 
//	 * @param sellerCode 对应系统编号（惠家有 SI2003  沙皮狗 SI3003  家有汇 SI2009）
//	 * @param productCode  对应的商品编号
//	 * @return 
//	 */
//	public  List<Map<String, Object>> getProduct(String sellerCode,String productCode){
//		
//		String excludeSeller = "'SF03100514','SF03100632'";
//		if(productCode!=null){
//			String query = "select p.product_code,p.product_name,p.product_adv,p.mainpic_url,p.market_price,p.min_sell_price,p.update_time,p.brand_code,p.labels,p.small_seller_code,t.keyword,"+
//					"(select group_concat(s.sku_code separator ',') from pc_skuinfo s where s.sale_yn='Y' and s.product_code=p.product_code group by s.product_code) as sku_code_string,"+
//					//"(select group_concat(s.sku_keyvalue separator '##') from pc_skuinfo s where s.product_code=p.product_code group by s.product_code) as sku_keyvalue_string,"+
//					"(select f.brand_name from pc_brandinfo f where p.brand_code=f.brand_code) as brand_name "+
//					"  from pc_productinfo p  left join pc_productdescription t  on t.product_code = p.product_code    where p.product_code=:productCode and p.seller_code=:seller_code and p.small_seller_code not in ("+excludeSeller+") and p.product_status='4497153900060002'";
//			return DbUp.upTable("pc_productinfo").dataSqlList(query, new MDataMap("seller_code",sellerCode,"productCode",productCode));
//			
//		}else{
//			// add by zht. 2016-11-23
//			String excludeProdcut = "" ; 
//			List<MDataMap> list = DbUp.upTable("pc_solr_exclude_product").queryAll(null, "", null, null); 
//			if(list != null && list.size() != 0){
//				for(MDataMap m : list) {
//					excludeProdcut += "'" + m.get("product_code") + "',";
//				}
//			}
//			if(StringUtils.isNotEmpty(excludeProdcut)) { 
//				excludeProdcut = " and p.product_code not in (" + excludeProdcut.substring(0, excludeProdcut.length() - 1).trim() + ")";
//				System.out.println(excludeProdcut);  
//			}
////			excludeProdcut = bConfig("clarge.excludeProduct");
////			if(StringUtils.isNotEmpty(excludeProdcut)) {
////				excludeProdcut = " and p.product_code not in (" + excludeProdcut.trim() + ")";
////			} else {
////				excludeProdcut = "";
////			}
//			 
//			/////////////
//			String query = "select p.product_code,p.product_name,p.product_adv,p.mainpic_url,p.market_price,p.min_sell_price,p.update_time,p.brand_code,p.labels,p.small_seller_code,t.keyword,"+
//					"(select group_concat(s.sku_code separator ',') from pc_skuinfo s where s.sale_yn='Y' and s.product_code=p.product_code group by s.product_code) as sku_code_string, "+
//					//"(select group_concat(s.sku_keyvalue separator '##') from pc_skuinfo s where s.product_code=p.product_code group by s.product_code) as sku_keyvalue_string,"+
//					"(select f.brand_name from pc_brandinfo f where p.brand_code=f.brand_code) as brand_name "+
//					"  from pc_productinfo p  left join pc_productdescription t  on t.product_code = p.product_code    where p.seller_code=:seller_code and p.small_seller_code not in ("+excludeSeller+") and p.product_status='4497153900060002' " 
//					+ excludeProdcut;
//					
//			return DbUp.upTable("pc_productinfo").dataSqlList(query, new MDataMap("seller_code",sellerCode));
//		}
//
//			
//	}
//	
//	public static void main(String[] args) {
//		SolrDataUtil sdu = new SolrDataUtil();
//		List<Map<String, Object>> list = sdu.getProduct("SI2003", null);
//		System.out.println(list.size());
//	}
//	
//	
//	
//	/***全量商品
//	 * 获取当前sellerCode所对应的商品分类信息（一级ID 一级 Name 二级 ID 二级Name）
//	 * @param sellCode 对应系统编号（惠家有 SI2003  沙皮狗 SI3003  家有汇 SI2009）
//	 * @return
//	 */
//	public  Map<String,SolrDataFacet> getFacet(String sellerCode,String productCode){
//		
//		String sql="";
//		List<Map<String, Object>> list =null;
//		if(productCode!=null){
//			
//		  sql ="select group_concat(category_code separator ',') as category_code,product_code from "+ 
//				" usercenter.uc_sellercategory_product_relation  "+
//                " where product_code=:productCode and seller_code=:sellerCode group by product_code";
//		  list = DbUp.upTable("uc_sellercategory_product_relation").dataSqlList(sql, new MDataMap("sellerCode",sellerCode,"productCode",productCode));
//			
//		}{
//		  sql ="select group_concat(ucs.category_code separator ',') as category_code,ucs.product_code from "+
//					" usercenter.uc_sellercategory_product_relation  ucs join "+
//					" (select product_code from productcenter.pc_productinfo where seller_code=:sellerCode and product_status='4497153900060002') pcp "+ 
//					" on  ucs.product_code = pcp.product_code "+
//					" where  ucs.seller_code=:sellerCode group by ucs.product_code";
//		  list = DbUp.upTable("uc_sellercategory_product_relation").dataSqlList(sql, new MDataMap("sellerCode",sellerCode));
//		 }
//		
//		String sqlCategory = "select category_code,category_name,parent_code from usercenter.uc_sellercategory where seller_code=:sellerCode";
//		List<Map<String, Object>> listCategory = DbUp.upTable("uc_sellercategory_product_relation").dataSqlList(sqlCategory, new MDataMap("sellerCode",sellerCode));
//		
//		/**
//		 * 先把结构树封装到map
//		 */
//		Map<String,String> categoryValue =new HashMap<String,String>();
//		Map<String,String> categoryFatherValue =new HashMap<String,String>();
//		for(Map<String, Object> map : listCategory){
//			categoryValue.put((String)map.get("category_code"), map.get("category_name")==null?"":map.get("category_name").toString());
//			categoryFatherValue.put((String)map.get("category_code"), map.get("parent_code")==null?"":map.get("parent_code").toString());
//		}
//		
//		/**
//		 * 封装每个商品对应的结构关系
//		 */
//		Map<String,SolrDataFacet> facet =new HashMap<String,SolrDataFacet>();
//		for(Map<String, Object> map : list){
//			
//			SolrDataFacet dataFacet = new SolrDataFacet();
//			List<String> twoId =  new ArrayList<String>();
//			List<String> twoName = new ArrayList<String>();
//			List<String> oneId = new ArrayList<String>();
//			List<String> oneName = new ArrayList<String>();
//			
//			if(map.get("category_code")!=null || !map.get("category_code").equals("")){
//				
//				String[] splitValue = map.get("category_code").toString().split(",");
//				if(null == splitValue || "".equals(splitValue) || splitValue.length<=0){
//				}else{
//					
//					for (int i = 0; i < splitValue.length; i++) {
//						
//						/****为了兼容商品挂在二级分类和三级分类   如果查询商品挂在12位的编号下，证明该商品没有三级分类****/
//						if(splitValue[i].trim().length()==12){
//							oneId.add(splitValue[i]);
//							oneName.add(categoryValue.get(splitValue[i]).toLowerCase());	//转小写，因为搜索的时候都是以小写搜索的
//						}
//						
//						/****为了兼容商品挂在二级分类和三级分类    如果查询商品挂在16位的编号下，证明该商品挂在三级分类 ****/
//						if(splitValue[i].trim().length()==16){
//							twoId.add(splitValue[i]);
//							String twoNameStr = categoryValue.get(splitValue[i]);
//							String oneNameStr = categoryValue.get(categoryFatherValue.get(splitValue[i]));
//							twoName.add(null == twoNameStr ? "" : twoNameStr.toLowerCase());//转小写，因为搜索的时候都是以小写搜索的
//							oneId.add(categoryFatherValue.get(splitValue[i]));
//							oneName.add(null == oneNameStr ? "" : oneNameStr.toLowerCase());//转小写，因为搜索的时候都是以小写搜索的
//						}
//						dataFacet.setOneId(oneId);
//						dataFacet.setOneName(oneName);
//						dataFacet.setTwoId(twoId);
//						dataFacet.setTwoName(twoName);
//					}
//					
//				}
//				
//			}
//			
//			facet.put((String)map.get("product_code"), dataFacet);
//		}
//		
//		return facet;
//		
//	}
//	
//	/** 人气方法
//	 * 人气公式 购物车*60% + 收藏%*40%
//	 * @param sellerCode  SI2003为惠家有  SI2009为家有汇
//	 * @return 返回数量
//	 */
//	public  Map<String,Double> popularitySumNum(String sellerCode){
//		String cartNum=null;
//		String OperateTypeNum=null;
//		
//		/***查询当前系统所对应的收藏数***/
//		String queryOperateTypeNum="select count(*) as operate_type,fhp.product_code from familyhas.fh_product_collection  fhp "+
//					" join  (select member_code from membercenter.mc_login_info where manage_code='"+sellerCode+"' and flag_enable=1) mem "+
//					" on fhp.member_code=mem.member_code "+
//					" where fhp.operate_type='4497472000020001' group by fhp.product_code";
//		List<Map<String, Object>> listOperateTypeNum =  DbUp.upTable("pc_skuinfo").dataSqlList(queryOperateTypeNum, new MDataMap());
//		
//		 /**
//         * 查询购物车下商品的数量
//         */
//		String queryCartNum = "select a.product_code product_code,sum(b.sumSkuNum) skunum"+
//				       " from (select s.sku_code ,s.product_code from productcenter.pc_skuinfo s inner join productcenter.pc_productinfo p on s.product_code= p.product_code where p.seller_code='"+sellerCode+"' and p.product_status='4497153900060002') a left join "+  
//		               " (select SUM(sku_num) sumSkuNum,sku_code from ordercenter.oc_shopCart  GROUP BY sku_code) b "+
//	                   " on a.sku_code = b.sku_code where b.sku_code is not null group by product_code" ;
//		List<Map<String, Object>> listCartNum = DbUp.upTable("pc_skuinfo").dataSqlList(queryCartNum, new MDataMap());
//		
//		/**
//		 * 查询全部的product_code
//		 */
//		String querySku="select product_code from  productcenter.pc_productinfo  where seller_code='"+sellerCode+"' and product_status='4497153900060002'";
//		List<Map<String, Object>> listProductCode = DbUp.upTable("pc_productinfo").dataSqlList(querySku, new MDataMap());
//		
//		Map<String,Double> mapCartNum = new HashMap<String, Double>();
//		Map<String,Double> mapOperateTypeNum = new HashMap<String, Double>();
//		Map<String,Double> mapPopularityNum = new HashMap<String, Double>();
//		for(Map<String,Object> map : listCartNum){
//			if(null==map.get("product_code")||"".equals(map.get("product_code"))){
//				continue;
//			}else{
//				
//				mapCartNum.put((String) map.get("product_code"),
//						(Double) (Double.parseDouble((String) (StringUtils.isEmpty(map.get("skunum")+"")?0:map.get("skunum").toString()))));
//			}
//			
//		}
//		
//		for(Map<String,Object> map : listOperateTypeNum){
//			if(null==map.get("product_code")||"".equals(map.get("product_code"))){
//				continue;
//			}else{
//				
//				mapOperateTypeNum.put((String) map.get("product_code"),
//						(Double) (Double.parseDouble((String) (StringUtils.isEmpty(map.get("operate_type")+"")?0:map.get("operate_type").toString()))));
//			}
//		}
//		
//		
//		for(Map<String, Object> map : listProductCode){
//			if(null==map.get("product_code")||"".equals(map.get("product_code"))){
//				continue;
//			}else{
//				
//				/**如果购物车商品数量为空默认为0**/
//				if(null!=mapCartNum.get(map.get("product_code").toString().trim())){
//					cartNum = mapCartNum.get(map.get("product_code").toString().trim()).toString();
//				}else{
//					cartNum="0";
//				}
//				
//				/**如果收藏商品数量为空默认为0**/
//				if(null!=mapOperateTypeNum.get(map.get("product_code").toString().trim())){
//					OperateTypeNum = mapOperateTypeNum.get(map.get("product_code").toString().trim()).toString();
//				}else{
//					OperateTypeNum="0";
//				}
//				DecimalFormat df = new DecimalFormat("0.000");
//				mapPopularityNum.put((String) map.get("product_code"),
//						Double.parseDouble(df.format((Double.parseDouble(cartNum)*0.6)+(Double.parseDouble(OperateTypeNum)*0.4))));
//			}
//		}
//		return mapPopularityNum;
//	}
//	
//	/***
//	 * 统计是否有货
//	 * @param sellerCode
//	 * @return
//	 */
//	/*public Map<String,Integer> getStockNumAll(String sellerCode,String productCode){
//		Map<String,Integer> map = new HashMap<String,Integer>();
//		String sql="";
//		List<Map<String, Object>> listProductCode = null;
//		if(productCode!=null){
//			sql="select product_code,group_concat(sku_code separator ',') as sku_code from pc_skuinfo  where product_code=:prductCode and seller_code=:sellerCode group by product_code";
//			listProductCode = DbUp.upTable("pc_productinfo").dataSqlList(sql, new MDataMap("sellerCode",sellerCode,"prductCode",productCode));
//		}else{
//			sql="select pcp.product_code,group_concat(pcs.sku_code separator ',') as sku_code from pc_skuinfo pcs  right join "+
//					" (select product_code from pc_productinfo where seller_code=:sellerCode and product_status='4497153900060002') pcp "+
//					" on pcs.product_code=pcp.product_code group by pcp.product_code";
//			listProductCode = DbUp.upTable("pc_productinfo").dataSqlList(sql, new MDataMap("sellerCode",sellerCode));
//			
//		}
//		
//		for(Map<String, Object> mapPro : listProductCode){
//			Integer integer = 0;
//			PlusSupportStock plus = new PlusSupportStock();
//			String[] skuCode = mapPro.get("sku_code").toString().split(",");
//			for(int i=0;i<skuCode.length;i++){
//				integer = integer + plus.upSalesStock(skuCode[i]);
//			}
//			
//			if(integer>0){
//				map.put(mapPro.get("product_code").toString(),1);
//			}else{
//				map.put(mapPro.get("product_code").toString(),0);
//			}
//		}
//		
//		return map;
//	}*/
//	/**
//	 * 获取商品最近的虚拟销售量(当productCodeList不为空时sellerCode无用)
//	 * @param sellerCode		商家编码 (可以为空)暂时只支持惠家有，请传入SI2003（2015-08-13开始可以支持沙皮狗项目传入SI3003）
//	 * @param productCodeList	商品编码List(为空时查询所有商品)
//	 * @param day				获取近day天的商品销量，必须为正整数
//	 * @return MDataMap  key:product_code(商品编码),value:sales(虚拟销量)
//	 */
//	/*public MDataMap getProductFictitiousSales(String sellerCode,List<String> productCodeList,int day){
//		MDataMap productCodesFictitiousSalesMap = new MDataMap();
//		if (day <= 0 || (StringUtils.isEmpty(sellerCode) && (null == productCodeList || productCodeList.isEmpty()))) {
//			return productCodesFictitiousSalesMap;
//		}
//		if (null == productCodeList || productCodeList.isEmpty()) {
//			productCodeList = new ArrayList<String>();
//			if (StringUtils.isNotEmpty(sellerCode)) {
//				List<MDataMap> productSalesMapList = DbUp.upTable("pc_productinfo").queryAll("product_code", "", "seller_code = '"+sellerCode+"'", null);
//				for (MDataMap mDataMap : productSalesMapList) {
//					productCodeList.add(mDataMap.get("product_code"));
//				}
//			}
//		}
//		
//		//获取day天前的日期
//    	Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE,   -day);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String beforeDay = sdf.format(cal.getTime());
//		String whereSql = " day >= '"+beforeDay+"' ";
//		
//		
//		if (null != productCodeList && productCodeList.size() > 0) {
//			whereSql += " and product_code in ('"+StringUtils.join(productCodeList,"','")+"')";
//		}
//		List<MDataMap> productSalesMapList = DbUp.upTable("pc_productsales_everyday").queryAll("product_code,day,sales", "", whereSql, null);
//		//获取到结果集里面所有的商品Code,放map里为了去重,key:product_code;value:fictitious_sales
//		for (MDataMap mDataMap : productSalesMapList) {
//			productCodesFictitiousSalesMap.put(mDataMap.get("product_code"),"0");
//		}
//		*//**
//		 * 2015-08-13增加虚拟销量
//		 *//*
//		List<MDataMap> productFictitiousSales = DbUp.upTable("pc_productinfo_ext").queryAll("product_code,fictitious_sales", "", "product_code in ('"+StringUtils.join(productCodeList,"','")+"')", null);
//		Map<String,Integer> fictitionMap = new HashMap<String, Integer>();
//		for (MDataMap mDataMap : productFictitiousSales) {
//			String fictition = StringUtils.isBlank(mDataMap.get("fictitious_sales")) ? "0" : mDataMap.get("fictitious_sales") ;
//			fictitionMap.put(mDataMap.get("product_code"), Integer.parseInt(fictition));
//		}
//		//循环productCode，获取到各个商品的销量mapList
//		for (String productCode : productCodeList) {
//			int fx = 0;			//总销量
//			if (productCodesFictitiousSalesMap.containsKey(productCode)) {
//				for (int i = day; i > 0; i--) {
//					//循环求出每日销量
//					Calendar everyDay = Calendar.getInstance();
//					everyDay.add(Calendar.DATE,   -i);
//					String nowDay = sdf.format(everyDay.getTime());
//					int sales = 0;				//商品在这天的真实销量
//					for (MDataMap productSalesMap : productSalesMapList) {
//						if (productCode.equals(productSalesMap.get("product_code")) && nowDay.equals(productSalesMap.get("day"))) {
//							sales = Integer.parseInt(productSalesMap.get("sales"));
//							break;
//						}
//					}
//					double fictitiousSales = fx + Math.abs(1/Math.sin(3*(day-i+1))) + 2*sales;
//					fx = Integer.parseInt(new java.text.DecimalFormat("0").format(fictitiousSales));
//				}
//			}
//			if (fictitionMap.containsKey(productCode)) {
//				fx = fx + fictitionMap.get(productCode);		//2015-08-13销量增加虚拟销量基数
//			}
//			productCodesFictitiousSalesMap.put(productCode, fx+"");
//		}
//		return productCodesFictitiousSalesMap;
//	}*/
//	
//	/** 暂时只支持惠家有    传递商品编号  不支持全部商品
//	 * 人气公式 购物车*60% + 收藏%*40%
//	 * @param sellerCode  SI2003为惠家有  SI2009为家有汇
//	 * @param productCode  可以为单个或多个，productCode中间用,号间隔
//	 * @return 返回数量
//	 */
//	public Map<String,Double> popularityNumOne(String sellerCode,String productCode){
//		Map<String,Double> mapPopularityNum = new HashMap<String, Double>();
//		
//		if (StringUtils.isEmpty(productCode)) {
//			return mapPopularityNum;
//		}
//		
//		String[] splitProductCode= productCode.split(",");
//		for (int i = 0; i < splitProductCode.length; i++) {
//			 /**
//	         * 查询购物车下商品的数量 和 收藏商品的数量
//	         */
//			String queryNum =" select (select count(operate_type) from familyhas.fh_product_collection b where b.operate_type='4497472000020001' and b.product_code='"+splitProductCode[i]+"' ) as operate_type, "+ 
//					         " (select SUM(sku_num) sumSkuNum from ordercenter.oc_shopCart where sku_code in (select sku_code  from productcenter.pc_skuinfo  where product_code='"+splitProductCode[i]+"' and seller_code='"+sellerCode+"' ) ) as sumSkuNum "+
//					         " from  productcenter.pc_productinfo where product_code='"+splitProductCode[i]+"'";
//			List<Map<String, Object>> listCartNum = DbUp.upTable("pc_productinfo").dataSqlList(queryNum, new MDataMap());
//			for(Map<String,Object> map : listCartNum){
//				
//				String sumSkuNum = null;
//				if(map.get("sumSkuNum")==null || map.get("sumSkuNum").equals("")){
//					sumSkuNum="0";
//				}else{
//					sumSkuNum=map.get("sumSkuNum").toString();
//				}
//				String operateType = null;
//				if(map.get("operate_type")==null || map.get("operate_type").equals("")){
//					operateType="0";
//				}else{
//					operateType=map.get("operate_type").toString();
//				}
//				mapPopularityNum.put(splitProductCode[i], 
//						(Double) (Double.parseDouble(sumSkuNum)*0.6)+
//						(Double) (Double.parseDouble(operateType)*0.4));
//				
//			}
//		}
//		
//		return mapPopularityNum;
//	}
//	
//	
//	/**
//	 * 根据productCode查询商品的赠品
//	 */
//	public Map<String,List<String>> getProductGifts(String sellerCode,String productCode){
//		//key为productCode,value为赠品
//		Map<String,List<String>> resultMap = new HashMap<String, List<String>>();
//		List<String> listValue = new ArrayList<String>();
//		listValue.add("赠品");
//		
//		if(sellerCode==null && productCode==null){
//			return resultMap;
//		}
//		
//		Map<String,String> innerGiftMap = new HashMap<String, String>();//内联赠品
//		Map<String,String> outerGiftMap = new HashMap<String, String>();//外联赠品
//		
//		//查询全部的内联赠品
//		String sqlinner ="";
//		List<Map<String, Object>>  listinner = null;
//		if(productCode==null){
//			sqlinner = "select pcp.product_code from pc_productproperty pcp  right join  "+
//					" (select product_code from pc_productinfo where seller_code=:sellerCode and product_status='4497153900060002') pro "+
//					" on pcp.product_code=pro.product_code where pcp.property_type='449736200004' and pcp.property_key='内联赠品'";
//			listinner = DbUp.upTable("pc_productproperty").dataSqlList(sqlinner, new MDataMap("sellerCode",sellerCode));
//		}else{
//			sqlinner="select product_code,property_value from pc_productproperty  product_code "+
//		             " where product_code =:productCode and property_type='449736200004' and property_key='内联赠品'";
//			listinner = DbUp.upTable("pc_productproperty").dataSqlList(sqlinner, new MDataMap("productCode",productCode));
//		}
//		
//		for(Map<String, Object> map : listinner){
//			innerGiftMap.put(map.get("product_code").toString(), "赠品");
//		}
//		
//		//查询全部的外联赠品
////		String sqlouter ="";
////		List<Map<String, Object>> listouter = null;
////		if(productCode==null){
////			sqlouter = "select product_code,gift_name from  pc_product_gifts_new where seller_code=:sellerCode";
////			listouter = DbUp.upTable("pc_product_gifts_new").dataSqlList(sqlouter, new MDataMap("sellerCode",sellerCode));
////		}else{
////			sqlouter="select product_code,gift_name from  pc_product_gifts_new where product_code=:productCode";
////			listouter = DbUp.upTable("pc_product_gifts_new").dataSqlList(sqlouter, new MDataMap("productCode",productCode));
////		}
////		
////		
////		for(Map<String, Object> map : listouter){
////			outerGiftMap.put(map.get("product_code").toString(), "赠品");
////		}
//		
//		
//		//查询全部商品
//		String listSql="";
//		List<Map<String, Object>> list = null;
//		if(productCode==null){
//			listSql = "select product_code from pc_productinfo where seller_code=:sellerCode and product_status='4497153900060002'";
//			list = DbUp.upTable("pc_productinfo").dataSqlList(listSql, new MDataMap("sellerCode",sellerCode));
//		}else{
//			listSql="select product_code from pc_productinfo where  product_status='4497153900060002' and product_code=:productCode";
//			list = DbUp.upTable("pc_productinfo").dataSqlList(listSql, new MDataMap("productCode",productCode));
//		}
//		
//		
//		for(Map<String, Object> map : list){
//			if((innerGiftMap.get(map.get("product_code"))!=null && !innerGiftMap.get(map.get("product_code")).equals(""))||
//			   (outerGiftMap.get(map.get("product_code"))!=null && !outerGiftMap.get(map.get("product_code")).equals("")) ){
//				resultMap.put(map.get("product_code").toString(), listValue);
//			}
//			
//		}
//		
//		return resultMap;
//	}
//	
//	/**
//	 * top50排序权重
//	 * @param sellerCode
//	 * @param productCode
//	 * @return
//	 */
//	public Map<String,Integer> getTop50Sort(String sellerCode,String productCode){
//		Map<String,Integer> resultMap = new HashMap<String, Integer>();
//		if(sellerCode==null && productCode==null){
//			return resultMap;
//		}
//		List<MDataMap> queryResultList = null;
//		if(productCode==null){
//			queryResultList = DbUp.upTable("pc_product_top50").queryAll("product_code,sort", "", "seller_code='"+sellerCode+"'", null);
//		}else{
//			queryResultList = DbUp.upTable("pc_product_top50").queryAll("product_code,sort", "", "product_code='"+productCode+"'", null);
//		}
//		if (null != queryResultList && !queryResultList.isEmpty()) {
//			for (MDataMap mDataMap : queryResultList) {
//				resultMap.put(mDataMap.get("product_code"), Integer.parseInt(StringUtils.isBlank(mDataMap.get("sort")) ? "0" : mDataMap.get("sort")));
//			}
//		}
//		
//		return resultMap;
//		
//	}
	
}
