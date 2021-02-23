package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.SampleVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestControllerTest {
	
	@GetMapping(value="/hello",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String sayHello() {
		log.info("hello 요청");
		return "Hello World";
	}
	
	@GetMapping("/sendVO")
	public SampleVO sendVO() {
		SampleVO sample=new SampleVO();
		sample.setMno("12345");
		sample.setFirstName("hong");
		sample.setLastName("dong");
		return sample;
	}

	@GetMapping(value="/sendlist", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<SampleVO> sendList() {
		
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			SampleVO sample=new SampleVO();
			sample.setMno("12345");
			sample.setFirstName("hong");
			sample.setLastName("dong");
			list.add(sample);
		}
		return list;
	}
	
	@GetMapping("/check")   // ~/check?height=180&weight=80
	public ResponseEntity<SampleVO> check(double height, double weight) {
		SampleVO vo = new SampleVO();
		vo.setMno("4567");
		vo.setFirstName(height+"");
		vo.setLastName(weight+"");
		
		ResponseEntity<SampleVO> entity=null;
		
		if(height < 150) {
			entity=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
		}else {
			entity=ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return entity;
	}
	
	@GetMapping("/product/{cat}/{pid}") // http://localhost:8080/product/bags/1234
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid) {
		return new String[] {"category : " +cat, "productId : " +pid};
	}
}
