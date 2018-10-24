package com.sxyhton.system.controller;

import com.sxyht.common.utils.R;
import com.sxyhton.common.annotation.Log;
import com.sxyhton.common.controller.BaseController;
import com.sxyhton.common.domain.Tree;
import com.sxyhton.system.domain.MenuDO;
import com.sxyhton.system.service.MenuService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author cloudht 980899486@qq.com
 */
@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
	String prefix = "system/menu";
	@Autowired
	MenuService menuService;

	@RequiresPermissions("sys:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return prefix+"/menu";
	}

	@RequiresPermissions("sys:menu:menu")
	@RequestMapping("/list")
	@ResponseBody
	List<MenuDO> list(@RequestParam Map<String, Object> params) {
		return menuService.list(params);
	}

	@Log("添加菜单")
	@RequiresPermissions("sys:menu:add")
	@GetMapping("/add/{pId}")
	String add(Model model, @PathVariable("pId") Long pId) {
		model.addAttribute("pId", pId);
		if (pId == 0) 
			model.addAttribute("pName", "根目录");
		else
			model.addAttribute("pName", menuService.get(pId).getName());
		return prefix + "/add";
	}

	@Log("编辑菜单")
	@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		MenuDO mdo = menuService.get(id);
		Long pId = mdo.getParentId();
		model.addAttribute("pId", pId);
		if (pId == 0) 
			model.addAttribute("pName", "根目录");
		else
			model.addAttribute("pName", menuService.get(pId).getName());
		model.addAttribute("menu", mdo);
		return prefix+"/edit";
	}

	@Log("保存菜单")
	@RequiresPermissions("sys:menu:add")
	@PostMapping("/save")
	@ResponseBody
	R save(MenuDO menu) {
		if (menuService.save(menu) > 0)
			return R.ok();
		else
			return R.error(1, "保存失败");
	}

	@Log("更新菜单")
	@RequiresPermissions("sys:menu:edit")
	@PostMapping("/update")
	@ResponseBody
	R update(MenuDO menu) {
		if (menuService.update(menu) > 0)
			return R.ok();
		else
			return R.error(1, "更新失败");
	}

	@Log("删除菜单")
	@RequiresPermissions("sys:menu:remove")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (menuService.remove(id) > 0)
			return R.ok();
		else
			return R.error(1, "删除失败");
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<MenuDO> tree() {
		return menuService.getTree();
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<MenuDO> tree(@PathVariable("roleId") Long roleId) {
		return menuService.getTree(roleId);
	}
}
