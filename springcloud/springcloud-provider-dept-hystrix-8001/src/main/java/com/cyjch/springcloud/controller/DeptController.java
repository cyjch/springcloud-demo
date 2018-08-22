package com.cyjch.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cyjch.springcloud.entity.Dept;
import com.cyjch.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController
{
	@Autowired
	private DeptService service = null;

	/**
	 * 带有容断器的获取部门信息
	 * 一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
	 */
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id)
	{
		Dept dept = this.service.get(id);
		
		//故意人为抛出异常，模拟服务故障，如果异常时进行熔断服务调用，前端工程师可以解析含有某些约定的字段处理，显示友好信息
		if (null == dept) {
			throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
		}
		
		return dept;
	}

	/**
	 * 熔断处理方法，接上面get()方法的异常
	 */
	public Dept processHystrix_Get(@PathVariable("id") Long id)
	{
		//return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand").setDb_source("no this database in MySQL");
		Dept dept = new Dept();
		dept.setDeptno(id);
		dept.setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand");
		dept.setDb_source("no this database in MySQL");
		return dept;
	}
}