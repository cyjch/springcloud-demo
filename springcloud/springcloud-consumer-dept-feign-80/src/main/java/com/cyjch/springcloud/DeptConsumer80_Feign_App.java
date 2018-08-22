package com.cyjch.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient

//启用Feign客户端，扫描com.cyjch.springcloud包
@EnableFeignClients(basePackages= {"com.cyjch.springcloud"})
//组件扫描
@ComponentScan("com.cyjch.springcloud")
public class DeptConsumer80_Feign_App{
	public static void main(String[] args){
		SpringApplication.run(DeptConsumer80_Feign_App.class, args);
	}
}
