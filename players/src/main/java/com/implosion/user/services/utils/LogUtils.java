package com.implosion.user.services.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtils {

	public static String toJsonString(Object... input) {
		try {
			Map<String, Object> map = new HashMap<>();
			for (int i = 0; i < input.length-1; i+=2) {
				map.put(input[i].toString(), input[i+1]);
			}
			return "{ values: "+ new ObjectMapper().writeValueAsString(input) +" }";
		} catch (Exception e) {
			log.error("Unable to conver {} to json", input);
			return "[toJsonStringConversionError]";
		}
	}
}
