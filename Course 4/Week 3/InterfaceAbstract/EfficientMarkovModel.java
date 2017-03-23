
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    
    private int numOfChar;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int numChar) {
        myRandom = new Random();
        numOfChar = numChar;
        map = new HashMap<String, ArrayList<String>>();
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - numOfChar);
        String key = myText.substring(index, index + numOfChar);
        sb.append(key);
        for(int k=0; k < numChars - numOfChar; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public String toString() {
        return "EfficientMarkovModel of order " + numOfChar;
    }
    
    private void buildMap() {
        ArrayList<String> follows;
        for(int i = 0; i < myText.length() - 1; i++) {
            String nextChar = null;
            if((i + numOfChar) >= myText.length() ) {
                
                //key = myText.substring(i, myText.length());
                //System.out.println(i + " " + key);
                nextChar = "";
                //break;
            } else {
                String key = myText.substring(i, i + numOfChar);
                //int index = myText.indexOf(key);
                nextChar = Character.toString(myText.charAt(i + numOfChar));
                //nextChar = myText.substring(index + 1, i + numOfChar);
                //System.out.println(nextChar);
                if(!map.containsKey(key)) {
                    follows = new ArrayList<String>();
                    follows.add(nextChar);
                    map.put(key, follows);
                } else {
                    follows = map.get(key);
                    follows.add(nextChar);
                    map.put(key, follows);
                }
            }
        }
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        if(map.containsKey(key)) {
            follows = map.get(key);
        }
        return follows;
    }
    
    public void printHashMapInfo() {
        int maxSize = 0;
        for(String key : map.keySet()) {
            int size = map.get(key).size();
            if(size > maxSize) {
                maxSize = size;
            }
            //System.out.println(key + "\t size: " + size + " " + map.get(key));
        }
        
        System.out.println("The maximum number of keys following a key is " + maxSize);
        //System.out.print("Keys that have the largest ArrayList (of size 3 in this case) are: ");
        //for(String key : map.keySet()) {
            //if(map.get(key).size() == maxSize) {
                //System.out.println(key);
            //}
        //}
        System.out.println("It has " + map.size() + " keys in the HashMap");
    }
}
