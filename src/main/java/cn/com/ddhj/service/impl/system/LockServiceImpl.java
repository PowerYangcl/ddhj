package cn.com.ddhj.service.impl.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.mapper.system.LockMapper;
import cn.com.ddhj.service.system.ILockService;

@Service
public class LockServiceImpl implements ILockService {

	@Autowired
	private LockMapper mapper;

	/**
	 * 
	 * 方法: addLock <br>
	 * 
	 * @param keycode
	 * @param timeoutSecond
	 * @param uuid
	 * @return
	 * @see cn.com.ddhj.service.system.ILockService#addLock(java.lang.String,
	 *      java.lang.Integer, java.lang.String)
	 */
	@Override
	public String addLock(String keycode, Integer timeoutSecond, String uuid) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("somekey", keycode);
		param.put("keysplit", ",");
		param.put("timeoutsecond", timeoutSecond.toString());
		param.put("lockflag", "1");
		param.put("uuid", uuid);
		return mapper.lock(param);
	}

	/**
	 * 
	 * 方法: unLock <br>
	 * 
	 * @param uuid
	 * @return
	 * @see cn.com.ddhj.service.system.ILockService#unLock(java.lang.String)
	 */
	@Override
	public String unLock(String uuid) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("somekey", "");
		param.put("keysplit", ",");
		param.put("timeoutsecond", "0");
		param.put("lockflag", "2");
		param.put("uuid", uuid);
		return mapper.lock(param);
	}

}
