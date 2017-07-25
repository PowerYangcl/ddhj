package cn.com.ddhj.service.impl.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.system.SysMenuDto;
import cn.com.ddhj.mapper.system.SysMenuMapper;
import cn.com.ddhj.model.system.SysMenu;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.system.ISysMenuService;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenuMapper, SysMenuDto> implements ISysMenuService {

	@Autowired
	private SysMenuMapper mapper;

	@Override
	public List<SysMenu> menu(String groupCode) {
		return getMenu(groupCode, "0");
	}

	private List<SysMenu> getMenu(String groupCode, String parentCode) {
		List<SysMenu> list = new ArrayList<SysMenu>();
		try {
			SysMenuDto dto = new SysMenuDto();
			dto.setGroupCode(groupCode);
			dto.setParentCode(parentCode);
			list = mapper.findEntityAll(dto);
			if (list != null && list.size() > 0) {
				for (SysMenu menu : list) {
					SysMenuDto subDto = new SysMenuDto();
					subDto.setGroupCode(menu.getGroupCode());
					subDto.setParentCode(menu.getCode());
					List<SysMenu> subMenus = mapper.findEntityAll(subDto);
					if (subMenus != null && subMenus.size() > 0) {
						menu.setSubMenus(subMenus);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
