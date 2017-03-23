
/**
 * Write a description of WordFrequencies here.
 * 
 * @author Kenneth Umali 
 * @version 16.2.2017
 */

import java.util.ArrayList;
import edu.duke.*;
public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()) {
            word = word.toLowerCase();
            /*
             * ignore punctuations
             * 
             * if(!Character.isLetter(word.charAt(0))) {
                    word = word.substring(1, word.length());
            }
            if(!Character.isLetter(word.charAt(word.length() - 1))) {
                word = word.substring(0, word.length() - 1);
            }*/
            int index = myWords.indexOf(word);
            if(index == -1) { //checks if the word is not yet in the ArrayList
                myWords.add(word);
                myFreqs.add(1);
            } else { //if the word is already in the ArrayList
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1); //increment the value of the element based on the index
            }
        }
    }
    
    public int findIndexOfMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int i = 0; i < myFreqs.size(); i++) {
            if(myFreqs.get(i) > max) {
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for(int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + 
                            myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
}
