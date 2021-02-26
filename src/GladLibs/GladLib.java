package GladLibs;

import edu.duke.*;
import java.util.*;

public class GladLib {
	
	private static ArrayList<String> adjectiveList;
	private static ArrayList<String> nounList;
	private static ArrayList<String> colorList;
	private static ArrayList<String> countryList;
	private static ArrayList<String> nameList;
	private static ArrayList<String> animalList;
	private static ArrayList<String> timeList;

	private static Random myRandom;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";

	public GladLib() {
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}

	public GladLib(String source) {
		initializeFromSource(source);
		myRandom = new Random();
	}

	private void initializeFromSource(String source) {
		adjectiveList = readIt(source + "/adjective.txt");
		nounList = readIt(source + "/noun.txt");
		colorList = readIt(source + "/color.txt");
		countryList = readIt(source + "/country.txt");
		nameList = readIt(source + "/name.txt");
		animalList = readIt(source + "/animal.txt");
		timeList = readIt(source + "/timeframe.txt");
	}

	public static String randomFrom(ArrayList<String> source) {
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}

	public static String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")) {
			return randomFrom(colorList);
		}
		if (label.equals("noun")) {
			return randomFrom(nounList);
		}
		if (label.equals("name")) {
			return randomFrom(nameList);
		}
		if (label.equals("adjective")) {
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")) {
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")) {
			return randomFrom(timeList);
		}
		if (label.equals("number")) {
			return "" + myRandom.nextInt(50) + 5;
		}
		return "**UNKNOWN**";
	}

	public static String processWord(String w) {
		int first = w.indexOf("<");
		int last = w.indexOf(">", first);
		if (first == -1 || last == -1) {
			return w;
		}
		String prefix = w.substring(0, first);
		String suffix = w.substring(last + 1);
		String sub = getSubstitute(w.substring(first + 1, last));
		return prefix + sub + suffix;
	}

	private static void printOut(String s, int lineWidth) {
		int charsWritten = 0;
		for (String w : s.split("\\s+")) {
			if (charsWritten + w.length() > lineWidth) {
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w + " ");
			charsWritten += w.length() + 1;
		}
	}

	public static String fromTemplate(String source) {
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for (String word : resource.words()) {
				story = story + processWord(word) + " ";
			}
		} else {
			FileResource resource = new FileResource(source);
			for (String word : resource.words()) {
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}

	private ArrayList<String> readIt(String source) {
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for (String line : resource.lines()) {
				list.add(line);
			}
		} else {
			FileResource resource = new FileResource(source);
			for (String line : resource.lines()) {
				list.add(line);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println("\n");
		String story = fromTemplate("data/madtemplate.txt");
		printOut(story, 60);
	}

}
