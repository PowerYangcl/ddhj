package cn.com.ddhj.service.system;

import java.util.List;
import java.util.Map;

public interface ISysMenuService {

	List<Map<String, Object>> menu();
	
	List<Map<String, Object>> subMenu();
}
