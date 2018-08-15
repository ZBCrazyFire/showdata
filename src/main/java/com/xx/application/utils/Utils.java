package com.xx.application.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utils {
	public static String nowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(new Date());
		System.out.println(nowTime);
		return nowTime;
	}
	public static String generalId() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}
}
