
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author Kenneth Umali
 * @version 15.2.2017
 */

import edu.duke.*;
public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < message.length(); i += 2) {
            char ch = message.charAt(i);
            sb.append(ch);
        }
        return sb.toString();
    }
    
    private int[] countLetters(String encrypt) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < encrypt.length(); i++) {
            char ch = Character.toLowerCase(encrypt.charAt(i));
            int dex = alphabet.indexOf(ch);
            if(dex != - 1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] freqs) {
        int maxDex = 0;
        for(int i = 0; i < freqs.length; i++) {
            if(freqs[i] > freqs[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }
    
    void breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int[] freqs1 = countLetters(input);
        int key1 = maxIndex(freqs);
        int key2 = maxIndex(freqs1);
    }
    
    void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc2 = new CaesarCipherTwo(21, 8);
        String encrypted = cc2.encrypt(message);
        System.out.println("encrypted message: " + encrypted);
        
        //String firstHalf = halfOfString(message, 0);
        //String secondHalf = halfOfString(message, 1);
    }
}
