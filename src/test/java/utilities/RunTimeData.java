package utilities;

import java.util.HashMap;
import java.util.Map;

public class RunTimeData {

	private static final Map<String, Object> dataMap = new HashMap<>();

	public static void setData(String key, Object value) {
		dataMap.put(key, value);
	}

	public static Object getData(String key) {
		return dataMap.get(key);
	}
}
