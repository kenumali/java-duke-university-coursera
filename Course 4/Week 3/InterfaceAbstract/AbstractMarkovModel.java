
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        for(int i = 0; i < myText.length(); i++) {
            int index = myText.indexOf(key, pos);
            if(index+key.length() == myText.length() || index == -1) {
                break;
            }
            follows.add(Character.toString(myText.charAt(index + key.length())));
            pos = index + key.length(); // index+1

        }
        return follows;
    }
    
    abstract public String toString();
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
}
