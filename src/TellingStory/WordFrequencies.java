package TellingStory;

import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {

	public static ArrayList<String> myWords;
	public static ArrayList<Integer> myFreqs;
	

	public static void main(String[] args) {
		findUnique();
        System.out.println("# unique words: " + myWords.size());
        int index = findMax();
        System.out.println("max word/freq: " + myWords.get(index)+" " + myFreqs.get(index));
	}

	public WordFrequencies() {
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}

	public static void findUnique() {

		FileResource resource = new FileResource();
		for (String s : resource.words()) {
			s = s.toLowerCase();
			int index = myWords.indexOf(s);
			if (index == -1) {
				myWords.add(s);
				myFreqs.add(1);
			} else {
				int freq = myFreqs.get(index);
				myFreqs.set(index, freq + 1);
			}
		}
	}

	public static int findMax() {
		int max = myFreqs.get(0);
		int maxIndex = 0;
		for (int k = 0; k < myFreqs.size(); k++) {
			if (myFreqs.get(k) > max) {
				max = myFreqs.get(k);
				maxIndex = k;
			}
		}
		return maxIndex;
	}


}
