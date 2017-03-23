
/**
 * Write a description of WordLengths here.
 * 
 * @author Kenneth Umali 
 * @version 14.2.2017
 */
import edu.duke.*;
import java.util.Arrays;
public class WordLengths {

    void countWordLengths(FileResource resource, int[] counts) {
        //If word has a non-letter as the first or last character,
        //it should not be counted as part of the word length
        for(String word : resource.words()) {
            //checks if the first character is non-letter
            if(!Character.isLetter(word.charAt(0))) {
                word = word.substring(1, word.length());
            }
            //checks if the last character is non-letter
            if(!Character.isLetter(word.charAt(word.length() - 1))) {
                word = word.substring(0, word.length() - 1);
                //length--;
            }
            int length = word.length();
            //System.out.println(length + " " + word);
            counts[length] += 1;
        }
        System.out.println(Arrays.toString(counts));
        System.out.println("index of largest element: " + indexOfMax(counts));
    }
    
    int indexOfMax(int[] values) {
        int index = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > values[index]) {
                index = i;
            }
        }
        return index;
    }
    
    int countWords(FileResource fr) {
        int longest = 0;
        for(String word : fr.words()) {
            if(word.length() > longest) {
                longest = word.length();
            }
        }
        return longest + 1;
    }
    
    void testCountWordLengths() {
        //String[] sample = {"romeo.txt"};
        String[] sample = {"errors.txt"};
        for(int i = 0; i < sample.length; i++) {
            FileResource fr = new FileResource("data/" + sample[i]);
            String words = fr.asString();
            int[] counts = new int[countWords(fr)];
            System.out.println("done with " + sample[i]);
            //System.out.println(counts.length);
            countWordLengths(fr, counts);
        }
        
    }
}
