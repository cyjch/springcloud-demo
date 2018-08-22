package com.cyjch.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;

/**
 * Spring的配置文件：applicationContext.xml
 * SpringBoot的配置：@Configuration(类修饰)、@Bean(方法修饰) 
 */
@Configuration
public class ConfigBean{ 
	/**
	Spring使用BEAN的方式
	applicationContext.xml
	--------------------------------
	<bean id="userServcie" class="com.atguigu.tmall.UserServiceImpl">

	@Bean
	public UserServcie getUserServcie(){
		return new UserServcieImpl();
	}
	--------------------------------
	 */
	
	
	/**
	 * Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端       负载均衡的工具。
	 * jdbc : JdbcTemplate(jdbc的模板)
	 * redis: RedisTemplate(redis的模板)
	 * rest : RestTemplate (rest风格的模板)
	 * controller使用 restTemplate访问restful接口方式 (url, requestMap,ResponseBean.class)
	 * 这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型
	 */
	@Bean
	@LoadBalanced //Ribbon是基于Netflix Ribbon实现的一套客户端，负载均衡的工具
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	/**
	 * 负载均衡算法切换
	 */
	@Bean
	public IRule myRule(){
		//return new RoundRobinRule();//轮询（默认）
		//return new RandomRule();//随机
		return new RetryRule();//推荐：先轮询，如果出问题了，会重试其它的节点服务，如果超过几次后就不会再访问坏的服务了
	} 
}

