package cn.com.ddhj.job;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.service.user.ITUserStepService;

/**
 * 每日凌晨统计根据用户当日步行数赠送碳币
 * 1.5w <= 当日步数 <= 2w步  用户碳币帐户*2,翻倍
 * 2w < 当日步数  用户碳币帐户*3,翻三倍
 * @ClassName: DailyCarbonStepPresent 
 * @Description: TODO
 * @author zht 
 * @date 2017年9月11日 下午7:10:00 
 *
 */
public class DailyCarbonStepPresent extends BaseClass {
	@Inject
	private ITUserStepService stepService;
	
	public void dailyCountStepPresent() {
		String yesterday = DateUtil.formatDate(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
		stepService.dailyCountStepPresent(yesterday);
	}
}
