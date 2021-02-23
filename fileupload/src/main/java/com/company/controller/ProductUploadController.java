package com.company.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductUploadController {

	@GetMapping("/product")
	public void product() {
		log.info("product 폼 요청");
	}
	
	@PostMapping("/product")
	@ResponseBody 
	public String productPost(MultipartFile file, ProductVO vo) {
	    log.info("데이터 가져오기 : "+vo);
	    log.info("file : " +file.getOriginalFilename());
	    
	    return "success";
	}
	 
//	@PostMapping("/product")	
//	public ResponseEntity<String> product(ProductVO vo) {
//		log.info("데이터 가져오기 : "+vo);
//		return vo!=null?
//				new ResponseEntity<String>("success", HttpStatus.OK):
//				new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
//	}
	
//	@PostMapping("/product")	
//    public ResponseEntity<String> product(@RequestBody ProductVO vo) {
//		log.info("데이터 가져오기 : "+vo);
//		return vo!=null?
//				new ResponseEntity<String>("success", HttpStatus.OK):
//				new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
//	}
//	
	/*
	 @PostMapping(value="/product",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 @ResponseBody 
	 public ProductVO productPost(ProductVO vo) {
	  	log.info("데이터 가져오기 : "+vo); 
	  	return vo; 
	 }
	 */
	
//	 @PostMapping(value="/product",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	 @ResponseBody 
//	 public ResponseEntity<ProductVO> productPost(ProductVO vo) {
//	  	log.info("데이터 가져오기 : "+vo); 
//	  	return new ResponseEntity<ProductVO>(vo, HttpStatus.OK); 
//	 }
	
//	 @PostMapping(value="/product",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	 @ResponseBody 
//	 public List<ProductVO> productPost(ProductVO vo) {
	//	log.info("데이터 가져오기 : "+vo); 
	//  List<ProductVO> list = new ArrayList<ProductVO>();
	// 
	//	for(int i=0; i<3; i++) { 
	//        list.add(vo);
	//	}
	//	 return list; 
//	  }
	 
//	 @PostMapping(value="/product", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	 @ResponseBody 
//	 public ResponseEntity<List<ProductVO>> productPost(ProductVO vo) {
//		log.info("데이터 가져오기 : "+vo); 
//		List<ProductVO> list = new ArrayList<ProductVO>();
//	 
//		for(int i=0; i<3; i++) { 
//	        list.add(vo); 	  
//		}
//		 return new ResponseEntity<List<ProductVO>>(list, HttpStatus.OK); 
//	}
}
