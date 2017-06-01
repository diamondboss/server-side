package com.diamondboss.util.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * 分拆小区地址
 * @author xzf
 *
 */
public class SpinOffAddress {
	public static Map<String, String> getCountryMap(String country) {
        Map<String, String> countryMap = new HashMap<String, String>();
        String provinces = "";
        String city = "";
        String area = "";
        String street = "";
        String county = "";
        String remainingString = country;
        if (null != country && !"".equals(country)) {
            int provincesIndex = remainingString.indexOf("省");
            if (provincesIndex > 0) {
                provinces = country.substring(0, provincesIndex + 1);
                countryMap.put("provinces", provinces);
                remainingString = country.substring(provincesIndex + 1);
            }
            int cityIndex = remainingString.indexOf("市");
            if (cityIndex > 0) {
                city = remainingString.substring(0, cityIndex + 1);
                countryMap.put("city", city);
                remainingString = remainingString.substring(cityIndex + 1);
			}
			int countyIndex = remainingString.indexOf("县");
			if (countyIndex > 0) {
				county = remainingString.substring(0, countyIndex + 1);
				countryMap.put("area", county);
				remainingString = remainingString.substring(countyIndex + 1);
			} else {
				int areaIndex = remainingString.indexOf("区");
				if (areaIndex > 0) {
					area = remainingString.substring(0, areaIndex + 1);
					countryMap.put("area", area);
					remainingString = remainingString.substring(areaIndex + 1);
				}
			}
			int streetIndex = remainingString.indexOf("路");
            if (streetIndex > 0) {
            	street = remainingString.substring(0, streetIndex + 1);
                countryMap.put("street", street);
                remainingString = remainingString.substring(streetIndex + 1);
            }
            if (remainingString.length() > 0) {
                countryMap.put("country", remainingString);
            } else {
                countryMap.put("country", country);
            }
        }
        return countryMap;
    }
}
