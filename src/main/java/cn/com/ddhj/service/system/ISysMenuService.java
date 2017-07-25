package cn.com.ddhj.service.system;

import java.util.List;

import cn.com.ddhj.dto.system.SysMenuDto;
import cn.com.ddhj.model.system.SysMenu;
import cn.com.ddhj.service.IBaseService;

public interface ISysMenuService extends IBaseService<SysMenu, SysMenuDto> {

	/**
	 * 根据菜单分组编码查询菜单列表
	 * 
	 * @param groupCode
	 * @return
	 */
	List<SysMenu> menu(String groupCode);
}
