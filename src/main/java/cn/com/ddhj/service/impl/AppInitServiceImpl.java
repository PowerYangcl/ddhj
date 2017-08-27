package cn.com.ddhj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.TAppBootPicMapper;
import cn.com.ddhj.mapper.TAppConfigMapper;
import cn.com.ddhj.mapper.TAppStartMapper;
import cn.com.ddhj.mapper.TAppVersionMapper;
import cn.com.ddhj.model.TAppBootPic;
import cn.com.ddhj.model.TAppConfig;
import cn.com.ddhj.model.TAppStart;
import cn.com.ddhj.model.TAppVersion;
import cn.com.ddhj.service.IAppInitService;
import cn.com.ddhj.util.DCacheEnum;

/**'
 * @descriptions app应用相关信息接口 2001 and 2002接口相关内容
 *
 * @author Yangcl 
 * @date 2017年8月27日 下午6:37:43
 * @version 1.0.1
 */
@Service
public class AppInitServiceImpl implements IAppInitService {

	private static Logger logger = Logger.getLogger(AppInitServiceImpl.class); 
	
	private static String serialNumber = "LR201610160928070002";  
	
	@Autowired
	private TAppConfigMapper acMapper;
	
	@Autowired
	private TAppStartMapper asMapper;
	
	@Autowired
	private TAppVersionMapper avMapper;
	
	@Autowired
	private TAppBootPicMapper abpMapper;

	/**
	 * @descriptions 2001 接口 app启动接口 | 以当天日期和Key作为唯一性标识，创建缓存对象。
	 *
	 * @param isFirstLaunch 无论是否为第一次启动，我都会返回给客户端引导图|故在此版本中视为无效参数
	 * @return
	 * @date 2017年8月27日 下午8:21:46
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject appInitialization(JSONObject obj , ServletContext application) {
		JSONObject result = new JSONObject();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(new Date());
		JSONObject appInitialization = null;
		Object o = application.getAttribute(DCacheEnum.app_init.toString() + "-" + today);  //   app_init-2017-08-27
		if(o != null){
			appInitialization = JSONObject.parseObject(String.valueOf( o ));
			return appInitialization;  
		}
		
		synchronized(Object.class){ 
			// 取锁后再次判断
			Object o_ = application.getAttribute(DCacheEnum.app_init.toString() + "-" + today);  //   app_init-2017-08-27
			if(o_ != null){
				appInitialization = JSONObject.parseObject(String.valueOf( o_ )); 
				return appInitialization;  
			}
			
			// 删除前一天的缓存 
			application.removeAttribute(DCacheEnum.app_init.toString() + "-" + this.getYestady(new Date())); 
			
			TAppConfig ac = new TAppConfig();  // 报文中的 keys 内容
			List<TAppConfig> acList = acMapper.findEntityList();
			if(acList !=  null && acList.size() != 0){
				ac = acList.get(0);  // 取得倒序中的第一个记录，视为最新配置
			}else{
				result.put("resultCode", -1);
				result.put("resultMessage", "后台app启动配置项异常！t_app_config未配置相关信息，请在管理后台核实");
				return result;
			}
			
			TAppVersion av = new TAppVersion();
			List<TAppVersion> avList = avMapper.findEntityList(); // 取得倒序中的第一个记录，视为最新版本 
			if(avList !=  null && avList.size() != 0){
				av = avList.get(0);  // 取得倒序中的第一个记录，视为最新配置
			}else{
				result.put("resultCode", -1);
				result.put("resultMessage", "后台app启动配置项异常！t_app_version未配置相关信息，请在管理后台核实");
				return result;
			}
			
			// 获取初始化信息  pageJump
			TAppStart as = asMapper.getEntityByVersion(av.getVersionApp());
			if(as == null){
				result.put("resultCode", -1);
				result.put("resultMessage", "后台app启动配置项异常！t_app_start未配置相关信息，请在管理后台核实");
				return result;
			}
			
			// 获取配置的引导图
			List<String> bootView = new ArrayList<>();
			List<TAppBootPic> bpList = abpMapper.getListByVersion(av.getVersionApp());
			if(bpList !=  null && bpList.size() != 0){
				for(TAppBootPic e : bpList){
					bootView.add(e.getPicUrl());
				}
			}
			
			JSONObject keys = new JSONObject();
			keys.put("wechatAppId" ,  ac.getWechatAppId());
			keys.put("wechatAppSecret" ,  ac.getWechatAppSecret());
			keys.put("umKeyAndroid" ,  ac.getUmKeyAndroid());
			keys.put("umKeyIos" ,  ac.getUmKeyIos());
			keys.put("qqAppKey" ,  ac.getQqAppKey());
			keys.put("qqAppId" ,  ac.getQqAppId()); 
			
			JSONObject pageJump = new JSONObject();
			pageJump.put("isShowAd" ,   as.getIsShowAdd());
			pageJump.put("picUrl" ,  as.getPicUrl() );
			pageJump.put("ynCountdown" ,  as.getYuCountdown() );
			pageJump.put("buttonBackground" ,   as.getButtonBackground());
			pageJump.put("buttonPic" ,   as.getButtonPic());
			pageJump.put("ynJumpButton" ,   as.getYuJumpButton());
			pageJump.put("showmoreLinkvalue" ,   as.getShowMoreLinkvalue());
			pageJump.put("buttonType" ,   as.getButtonType());
			pageJump.put("residenceTime" ,   as.getResidenceTime());
			pageJump.put("buttonText" ,   as.getButtonText());
			pageJump.put("buttonColor" ,   as.getButtonColor());
			
			result.put("keys", keys); 
			result.put("pageJump", pageJump);  
			result.put("firstUrl", as.getFirstUrl());
			result.put("serialNumber", serialNumber);
			result.put("bootView", bootView); 
			result.put("resultCode", 1);
			result.put("resultMessage", "操作成功");
			
			application.setAttribute(DCacheEnum.app_init.toString() + "-" + today  , result.toJSONString());  
			
			return result;
		}
		
		
	}

	
	
	/**
	 * @descriptions 2002	app版本接口
	 *
	 * @param obj
	 * @return
	 * @date 2017年8月27日 下午8:23:09
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject appVersionInfo(JSONObject obj , ServletContext application) {
		JSONObject result = new JSONObject();

		
		return result;
	}
	
	
	
	private String getYestady(Date date){
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE , -1);   // 把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime();      // 这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 
		 return formatter.format(date);
	}
}












































