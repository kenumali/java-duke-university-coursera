
/**
 * Write a description of CharacterNames here.
 * 
 * @author Kenneth Umali 
 * @version 16.2.2017
 */

import java.util.ArrayList;
import edu.duke.*;
public class CharactersInPlay {

    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterCount;
    
    public CharactersInPlay() {
        characterNames = new ArrayList<String>();
        characterCount = new ArrayList<Integer>();
    }
    
    void update(String person) {
        int index = characterNames.indexOf(person);
        if(!characterNames.contains(person)) {
            characterNames.add(person);
            characterCount.add(1);
        } else {
            int value = characterCount.get(index);
            characterCount.set(index, value + 1);
        }
    }
    
    void findAllCharacters() {
        characterNames.clear();
        characterCount.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()) {
            if(line.contains(".")){
                line = line.trim();
                int index = line.indexOf(".");
                if(index !=-1) {
                    String person = line.substring(0, index);
                    update(person);
                }
            }
        }
    }
    
    void charactersWithNumParts(int num1, int num2) {
        for(int i = 0; i < characterNames.size(); i++) {
            int numParts = characterCount.get(i);
            if(numParts >= num1 && numParts <= num2) {
                System.out.println(characterNames.get(i) + " " + numParts);
            }
        }
    }
    
    void tester() {
        findAllCharacters();
        for(int i = 0; i < characterNames.size(); i++) {
            if(characterCount.get(i) > 1) {
                System.out.println(characterNames.get(i) +
                                "'s number of speaking parts: " + characterCount.get(i));
            }
        }
        charactersWithNumParts(10, 15);
    }
}
