package com.fengxing.ems.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroUtils {
	public static String encode(String hasAlgorithmName, Integer hasInterations
			,String password, Object salt) {
		return (new SimpleHash(hasAlgorithmName, password, salt, hasInterations)).toHex();
	}
}
