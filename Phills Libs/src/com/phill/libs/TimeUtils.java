package com.phill.libs;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

	public static String getFormattedTime(long seconds) {
		
        if(seconds < 0) {
            return "0s";
        }
        
        long hours = TimeUnit.SECONDS.toHours(seconds);
        seconds   -= TimeUnit.HOURS.toSeconds(hours);
        
        long minutes = TimeUnit.SECONDS.toMinutes(seconds);
        seconds -= TimeUnit.MINUTES.toSeconds(minutes);

        StringBuilder sb = new StringBuilder(64);
        
        if (hours > 0) {
        	sb.append(hours);
        	sb.append(" h ");
        }
        if (minutes > 0) {
        	sb.append(minutes);
        	sb.append(" min ");
        }
        sb.append(seconds);
        sb.append(" s");

        return(sb.toString());
    }
	
}
