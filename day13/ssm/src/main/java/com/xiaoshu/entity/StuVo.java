package com.xiaoshu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StuVo extends Stu{
	private String mName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date start;
	

	public Date getStart() {
		return start;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end;
	
	private Integer num;
}
