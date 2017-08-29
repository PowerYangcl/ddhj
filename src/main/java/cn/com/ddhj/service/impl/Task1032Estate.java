package cn.com.ddhj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.result.estateInfo.EData;
import cn.com.ddhj.service.IEstateInfoService;
import cn.com.ddhj.util.CommonUtil;

public class Task1032Estate implements Callable<List<EnvInfo>> {
	
	private IEstateInfoService estateService;  // 地产信息相关接口 
	
	private String position;
	
	private String radius;
	
	public List<EnvInfo> call() throws Exception {
		List<EnvInfo> list = new ArrayList<EnvInfo>();
		
		String[] arr = this.getPosition().split(",");
		String lat = arr[0];
		String lng = arr[1]; 
		JSONObject obj = estateService.estateInfoList(lng, lat, "1" , "10" , this.getRadius()); 
		EnvInfo rj = new EnvInfo();
		rj.setName("容积率");
		EnvInfo lh = new EnvInfo();
		lh.setName("绿化率");
		if(obj.getString("code").equals("1")){
			TreeMap<Integer , EData> map = new TreeMap<Integer , EData>();
			List<EData> list_ = JSONArray.parseArray(obj.getString("list") , EData.class);
			for(EData e : list_){
				Integer distance = CommonUtil.getDistance(Double.valueOf(lat) , Double.valueOf(lng) , Double.valueOf(e.getLat()) , Double.valueOf(e.getLng()));
				map.put(distance , e);
			}
			if(map.size() == 0){
				rj.setLevel("住宅");
				rj.setMemo("1.2"); 
				lh.setMemo("一般");  
				lh.setLevel("28%");
				list.add(rj);
				list.add(lh);
				return list; 
			}
			
			EData e = map.get(map.firstKey());
			if(e.getVolumeRate() != null){
				Double v = null;
				String vol = e.getVolumeRate();
				if(vol.length() > 2){
					vol = vol.substring(0, 2);
					try {
						v = Double.valueOf(vol);
					}catch (Exception e2) {
						vol = "1.2";
						v = 1.2;
					}
				}else{
					vol = "1.2";
					v = 1.2;
				}
				rj.setMemo(this.getRjLevel(e.getPropertyType()).split("@")[1]);  
				rj.setLevel(this.getRjLevel(e.getPropertyType()).split("@")[0]); 
				
			}else{
				rj.setLevel("住宅");
			}
			
			if(e.getGreeningRate() != null){
				String g = e.getGreeningRate().split("（")[0];
				if(g.length() < 2){
					lh.setLevel("25%");
				}else{
					lh.setLevel(g);
				}
				String l = e.getGreeningRate().split("绿化率")[1];
				l = l.substring(0, l.length() -1);
			}else{
				lh.setLevel("28%");
			}
		}else{
			rj.setLevel("住宅");
			lh.setLevel("28%");
		}
		
		list.add(rj);
		lh.setMemo(lh.getLevel()); 
		lh.setLevel(this.getLhMemo(lh.getLevel()));
		list.add(lh);
		return list; 
	}

	// 绿化率大于30%，色块显示“好”，25%-30%，显示“一般”，小于25%，显示“差”
	private String getLhMemo(String str){
		Integer num = 0;
		try {
			num = Integer.valueOf(str.split("%")[0]) ;
		} catch (Exception e) {
			return "一般";
		}
		
		if(num > 30){
			return "好";
		}else if(num >= 25 && num <= 30){
			return "一般";
		}else{
			return "差";
		} 
	}
	
	// 1.2那里要么显示普通or公寓or别墅  楼盘信息那里就是显示一个数字 - 程艳
	private String getRjLevel(String str){
		String re = "";
		if(str.equals("普通住宅")){
			re = "住宅@2";
		}else if(str.equals("公寓、普通住宅")){
			re = "公寓@1.6";
		}else if(str.equals("公寓")){
			re = "公寓@0.9";
		}else if(str.equals("别墅")){
			re = "别墅@0.3";
		}else if(str.equals("别墅、普通住宅")){
			re = "别墅@0.5";
		}else if(str.equals("公寓、其它")){
			re = "公寓@1.4";
		}else{
			re = "住宅@2";
		}
		return re;
	}
	
	public IEstateInfoService getEstateService() {
		return estateService;
	}
	public void setEstateService(IEstateInfoService estateService) {
		this.estateService = estateService;
	}

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	
}





























