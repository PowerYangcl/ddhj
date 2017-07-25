package cn.com.ddhj.service.impl.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.ddhj.service.system.ISysMenuService;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

	@Override
	public List<Map<String, Object>> menu() {
		Map<String, Object> map = new HashMap<>();
		map.put("code", "10001");
		map.put("name", "点点环境");
		map.put("href", "subindex.htm?code=10001");
		Map<String, Object> map1 = new HashMap<>();
		map1.put("code", "10002");
		map1.put("name", "微信商城");
		map1.put("href", "subindex.htm?code=10002");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		list.add(map1);
		return list;
	}

	@Override
	public List<Map<String, Object>> subMenu() {
		Map<String, Object> map = new HashMap<>();
		map.put("code", "10001");
		map.put("name", "点点环境");
		map.put("href", "subindex.htm?code=10001");
		Map<String, Object> map1 = new HashMap<>();
		map1.put("code", "10002");
		map1.put("name", "微信商城");
		map1.put("href", "subindex.htm?code=10002");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		list.add(map1);
		return list;
	}

}
