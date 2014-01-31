package core;

import java.util.HashMap;
import java.util.Map;

public class BotState {

	private Map<String,Object> memory = new HashMap<String,Object>();

	public Map<String,Object> getMemory() {
		return memory;
	}

	public void setMemory(Map<String,Object> memory) {
		this.memory = memory;
	}
	
	public boolean contains(String key){
		return this.memory.containsKey(key);
	}
	
	public Object get(String key){
		return this.memory.get(key);
	}
	
}
