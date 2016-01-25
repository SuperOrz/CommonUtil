package com.hs.servlet;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EnhancedRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	public EnhancedRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	@Override
	public String getParameter(String name) {
		String result = super.getParameter(name);
		if(result == null) return null;
		try {
			result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
			return result;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String,String[]> results =  super.getParameterMap();
		if(results==null) return results;
		for (String key : results.keySet()) {
			String values[] = results.get(key);
			for(int i=0; i<values.length; i++){
				String value = values[i];
				try {
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
			results.put(key, values);
		}
		return results;
	}
	
}
