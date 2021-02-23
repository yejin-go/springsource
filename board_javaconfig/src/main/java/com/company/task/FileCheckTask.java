package com.company.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.company.domain.FileAttach;
import com.company.mapper.AttachMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileCheckTask {
	//어제 날짜의 폴더를 알아낸 후 매일 새벽 두시에 자동으로 도는 프로그램	
	//데이터베이스에서 파일 리스트를 가져와서 현재 서버의 업로드 폴더와 비교 후
	//데이터베이스와 일치하지 않은 파일 제거.
	
	@Autowired
	private AttachMapper mapper;
	
	private String getFolderYesterday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		
		c.add(Calendar.DATE, -1);
		String str = sdf.format(c.getTime());
		
		return str.replace("-", File.separator);
	}
	
	@Scheduled(cron="* * 2 * * *")
	public void checkFiles() {
		log.warn("File Check Task run...");
		
		//어제 날짜의 파일 정보 데이터베이스에서 가져오기
		List<FileAttach> fileList = mapper.getOldFiles();
		
		//List 구조를 stream 형태로 변환(필터링, 합계, 평균.., 매핑과 같은 중간처리 가능해 짐) 
		Stream<FileAttach> stream = fileList.stream();
		
		//map() : 스트림의 요소에 저장된 값 중에서 원하는 필드만 추출하거나 특정 형태로 변환시 사용
		Stream<Path> filePath = stream.map(vo -> 
						Paths.get("c:\\upload", vo.getUploadPath(),vo.getUuid()+"_"+vo.getFileName()));
		
		List<Path> fileListPath = filePath.collect(Collectors.toList());
		
		fileListPath.forEach(p -> log.warn(""+p));
		
		File targetDir = Paths.get("c:\\upload", getFolderYesterday()).toFile();
		File[] removeFiles = targetDir.listFiles(file -> fileListPath.contains(file.toPath())==false);
		
		for(File file:removeFiles) {
			log.warn(file.getAbsolutePath());
			file.delete();
		}
	}
}
