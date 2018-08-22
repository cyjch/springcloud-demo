package com.cyjch.springcloud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cyjch.springcloud.entity.Dept;
import com.cyjch.springcloud.service.DeptClientService;

@RestController
public class DeptController_Consumer {
	
	/**
	 * 注入springcloud-api下面的service接口
	 */
	@Autowired
	private DeptClientService service;

	/**
	 * 查询记录操作
	 */
	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return this.service.get(id);
	}

	/**
	 * 查询列表操作
	 */
	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list() {
		return this.service.list();
	}

	/**
	 * 添加记录操作 
	 */
	@RequestMapping(value = "/consumer/dept/add")
	public Object add(Dept dept) {
		return this.service.add(dept);
	}
}
