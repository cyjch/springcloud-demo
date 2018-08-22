package com.cyjch.springcloud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cyjch.springcloud.entity.Dept;
import com.cyjch.springcloud.service.DeptService;

/**
 * http://localhost:8001/dept/list
 */
@RestController
public class DeptController {
	@Autowired
	private DeptService service;
	
	@PostMapping("/dept/add")
	//@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}
	
	@GetMapping("/dept/get/{id}")
	//@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return service.get(id);
	}

	@GetMapping("/dept/list")
	//@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return service.list();
	}

	///////////////////////////////////////
	
	/**
	 * 服务发现,可以遍历所有的微服务
	 */
	@Autowired
	private DiscoveryClient client;
	
	@GetMapping("/dept/discovery")
	//@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery() {
		//获取所有的服务提供方
		List<String> list = client.getServices();
		System.out.println("**********" + list);

		//获取其中一个微服务，打印服务相关信息
		List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
		}
		return this.client;
	}

}
