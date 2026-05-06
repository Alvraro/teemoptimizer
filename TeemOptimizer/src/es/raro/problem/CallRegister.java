package es.raro.problem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class CallRegister {
	private static final String DELIMITER = ";";
	
	private HashMap<String,Integer> calls;
	
	public CallRegister(){
		calls = new HashMap<String,Integer>();
	}
	
	public void register(Object[] objects) {
		String key = getKey(objects);
		
		Integer number = calls.get(key);
		if(number==null)
			number = 0;
		number++;
		
		calls.put(key, number);
	}

	private String getKey(Object[] objects) {
		String key = "";
		for(Object o : objects){
			if(o instanceof List){
				//Collections.sort((List) o);
			}
			key+=o.toString()+DELIMITER;
		}
		return key;
	}

	public void dumpToFile(String pathname) throws IOException{
		BufferedWriter f = new BufferedWriter(new FileWriter(pathname));
		for(Entry<String, Integer> e : calls.entrySet()){
			f.write(e.getKey()+DELIMITER+e.getValue());
			f.newLine();
		}
		f.close();
	}
}
