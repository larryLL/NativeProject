package com.shool.ctp.filter;

import org.apache.log4j.Level;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class DruidLogAcceptFilter extends Filter {

	private String keyword;
	private Level level;

	@Override
	public int decide(LoggingEvent event) {
		// TODO Auto-generated method stub
		// 得到正在记录日志的类的类名
		String msg = event.getLocationInformation().getClassName();
		if (msg.contains(keyword) && event.getLevel() == level) {
			return Filter.ACCEPT;
		} else {
			return Filter.DENY;
		}
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
}
