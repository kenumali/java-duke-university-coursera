import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    //private HashMap<String, ArrayList<String>> usedCategories;
    private ArrayList<String> usedCategories;
    private ArrayList<String> wordsUsed;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        wordsUsed = new ArrayList<String>();
        myMap = new HashMap<String, ArrayList<String>>();
        usedCategories = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        wordsUsed = new ArrayList<String>();
        myMap = new HashMap<String, ArrayList<String>>();
        usedCategories = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        
        String[] categories = {"adjective", "noun", "color", "country", "name",
                            "animal", "timeframe", "verb", "fruit"};
        for(String category : categories) {
            ArrayList<String> list = readIt(source + "/" + category + ".txt");
            myMap.put(category, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        } else {
            if(!usedCategories.contains(label)) {
                usedCategories.add(label);
            }
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(true) {
            if(!wordsUsed.contains(sub)) {
                wordsUsed.add(sub);
                break;
            } else {
                sub = getSubstitute(w.substring(first+1,last));
            }
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        System.out.println("\nTotal words replaced: " + wordsUsed.size());
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    int totalWordsInMap() {
        int totalNumber = 0;
        for(String key : myMap.keySet()) {
            //ArrayList<String> words  = myMap.get(key);
            //totalNumber += words.size();
           totalNumber += myMap.get(key).size();
        }
        return totalNumber;
    }
    
    int totalWordsConsidered() {
        int consideredWords = 0;
        for(int i = 0; i < usedCategories.size(); i++) {
            consideredWords += myMap.get(usedCategories.get(i)).size();
        }
       return consideredWords;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("Total Words: " + totalWordsInMap());
        System.out.println("Total Words Considered: " + totalWordsConsidered());
        wordsUsed.clear();
        usedCategories.clear();
    }
}
