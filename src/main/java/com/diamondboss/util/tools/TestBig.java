package com.diamondboss.util.tools;

import java.math.BigDecimal;

public class TestBig {

	public static void main(String[] args) {
		BigDecimal num = new BigDecimal("0.01");
		
		System.out.println(num);
		
		BigDecimal num1 = num.multiply(new BigDecimal("100")).setScale(0);
		
		System.out.println(num1);
	}

}
