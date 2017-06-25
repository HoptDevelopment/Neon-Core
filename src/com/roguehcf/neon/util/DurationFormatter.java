package com.roguehcf.neon.util;

import java.text.DecimalFormat;

import net.minecraft.util.org.apache.commons.lang3.time.DurationFormatUtils;

public class DurationFormatter {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final ThreadLocal<DecimalFormat> SECONDS_FORMAT = new ThreadLocal()
	{
		@Override
		protected DecimalFormat initialValue(){
			return new DecimalFormat("0.0");
		}
	};
	
	public static String getFormatted(long time, boolean milliseconds){
		if(time < TimeType.MINUTE.get()){
			return SECONDS_FORMAT.get().format(time * 0.001D) + 's';
		}
		return DurationFormatUtils.formatDuration(time, (time >= TimeType.HOUR.get() ? "HH:" : "") + "mm:ss");
	}
}
