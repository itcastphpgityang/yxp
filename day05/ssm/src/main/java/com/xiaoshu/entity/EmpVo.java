package com.xiaoshu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EmpVo extends Emp {
	
	private String dDname;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date start;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date end;

	public String getdDname() {
		return dDname;
	}

	public void setdDname(String dDname) {
		this.dDname = dDname;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	
}
