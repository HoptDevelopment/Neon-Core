package com.roguehcf.neon.util;

import java.util.concurrent.TimeUnit;

public enum TimeType {

	HOUR, MINUTE;
	
	public long get(){
		
		switch(this){
		
			case HOUR:{
				return TimeUnit.HOURS.toMillis(1L);
			}
			
			case MINUTE:{
				return TimeUnit.MINUTES.toMillis(1L);
			}
			
			default:{
				return 0;
			}
			
		}
	}
	
}
