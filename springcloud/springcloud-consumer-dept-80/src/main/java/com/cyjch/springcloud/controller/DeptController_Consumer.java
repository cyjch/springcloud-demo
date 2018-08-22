package com.cyjch.springcloud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.cyjch.springcloud.entity.Dept;

/**
 * 访问：http://localhost/consumer/dept/list
 */
@RestController
public class DeptController_Consumer
{

	//通过微服务名称地址访问
	//在没有启用服务发现时用此服务地址
	//private static final String REST_URL_PREFIX = "http://localhost:8001"; 
	
	//在开启服务发现后用此服务地址
	private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

	/**
	 * 使用 使用restTemplate访问restful接口 (url, requestMap,ResponseBean.class)
	 * 这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
	 * 用restTemplate访问远程服务
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 添加记录操作 
	 */
	@RequestMapping(value = "/consumer/dept/add")
	public boolean add(Dept dept) {
		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
	}

	/**
	 * 查询记录操作
	 */
	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
	}

	/**
	 * 查询列表操作
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list()
	{
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
	}

	/**
	 * 服务发现操作
	 * 测试@EnableDiscoveryClient,消费端可以调用服务发现
	 */
	@RequestMapping(value = "/consumer/dept/discovery")
	public Object discovery()
	{
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
	}

}
