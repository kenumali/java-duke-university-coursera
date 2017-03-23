
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author Kenneth Umali 
 * @version 14.2.2017
 */
import edu.duke.*;
import java.util.Arrays;
public class CaesarBreaker {

    public String decrypt(String encrypt) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypt);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        System.out.println(dkey + " " + (26 - dkey));
        return cc.encrypt(encrypt, 26 - dkey);
    }
    
    public int[] countLetters(String encrypt) {
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
    
    public int maxIndex(int[] freqs) {
        int maxDex = 0;
        for(int i = 0; i < freqs.length; i++) {
            if(freqs[i] > freqs[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }
    
    String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        //String answer = "";
        for(int i = start; i < message.length(); i += 2) {
            char ch = message.charAt(i);
            sb.append(ch);
        }
        //for (int k = start; k< message.length() ; k+= 2) {
            //answer = answer + message.charAt(k);
        //}
        return sb.toString();
        //return answer;
    }
    
    int getKey(String s) {
        int[] freqs = countLetters(s);
        int key = maxIndex(freqs);
        return key;
    }
    
    void decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        int dkey1 = key1 - 4;
        int dkey2 = key2 - 4;
        if(key1 < 4) {
            dkey1 = 26 - (4 - key1);
        }
        if(key2 < 4) {
            dkey2 = 26 - (4 - key2);
        }
        System.out.println("key1: " + dkey1 + " key2: " + dkey2);
        System.out.println(cc.encryptTwoKeys(encrypted, 26 - dkey1, 26 - dkey2));
    }
    
    public void testDecrypt() {
        FileResource fr = new FileResource();
        System.out.println(decrypt(fr.asString()));
    }
    
    public void testHalfOfString() {
        System.out.println(halfOfString("Qbkm Zgis", 0)); //Qk gs
        System.out.println(halfOfString("Qbkm Zgis", 1)); //bmZi
        System.out.println(halfOfString("Hello World", 0)); //HloWrd
        System.out.println(halfOfString("Hello World", 1)); //el ol
        System.out.println(halfOfString("Hello World", 2)); //loWrd
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        decryptTwoKeys(message);
        //decryptTwoKeysGivenKeys(message);
    }
}
