package com.jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.StreamingHttpOutputMessage;

import javax.lang.model.SourceVersion;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		// 롬복 됐는지 확인
//		Hello hello = new Hello();
//		hello.setData("hello");
//		String data = hello.getData();
//		System.out.println("data = " + data);

		SpringApplication.run(JpashopApplication.class, args);
	}
}
