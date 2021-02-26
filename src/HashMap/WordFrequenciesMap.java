package HashMap;

import edu.duke.*;
import java.util.*;

public class WordFrequenciesMap {

	public static void main(String[] args) {
		
		countWord();
	}
	
	public static void countWord() {
		
		FileResource fr = new FileResource();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int total = 0;
		
		for(String w : fr.words()) {
			w = w.toLowerCase();
			//total += 1;
			if(map.keySet().contains(w)) {
				map.put(w, map.get(w) + 1);
			}
			else {
				map.put(w, 1);
			}
		}
		
		for(String w : map.keySet()) {
			int occur = map.get(w);
			if(occur > 500) {
				System.out.println("occurences : " + occur + "\t" + w);
			}
		}
		
		//System.out.println("total:" + total);
		
	}

}
