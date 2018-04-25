package com.qa.util;

import java.util.Collection;

import com.google.gson.Gson;
import com.qa.domain.Account;

public class JSONUtil {

	private Gson gson;

	public JSONUtil() {
		this.gson = new Gson();
	}

	public String getJSONForObject(Object obj) {
		return gson.toJson(obj);
	}

	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		return gson.fromJson(jsonString, clazz);
	}

	public String getObjectForJSON(Collection<Account> values) {
		// TODO Auto-generated method stub
		return null;
	}

}