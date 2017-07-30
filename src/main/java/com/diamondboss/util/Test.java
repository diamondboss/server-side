package com.diamondboss.util;

import java.io.File;

import com.diamondboss.util.tools.PropsUtil;

public class Test {

	public static void main(String[] args) {
		 File fDir = new File(PropsUtil.class.getResource("/").getPath());
	     String p = fDir.getAbsolutePath();
	     System.out.println(p);
	}

}
