package com.company.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper1 {
	@Insert("insert into tbl_sample1(col1) values(#{value})")
	public int insertCol(String value);
}
