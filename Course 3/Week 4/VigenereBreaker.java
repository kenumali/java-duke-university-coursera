import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+= totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++) {
            String str = sliceString(encrypted, i, klength);
            key[i] = cracker.getKey(str);
        }
        return key;
    }

    public void breakVigenere () {
        /*FileResource fr = new FileResource();
        String message = fr.asString();
        FileResource frDictionary = new FileResource("dictionaries/English");
        //int[] keys = tryKeyLength(message, 4, 'e');
        HashSet<String> dictionary = readDictionary(frDictionary);
        //VigenereCipher vc = new VigenereCipher(keys);
        //String decrypted = vc.decrypt(message);
        String decrypted = breakForLanguage(message, dictionary);
        System.out.println(decrypted);
        int count = countWords(decrypted, dictionary);
        System.out.println("Valid words: " + count);*/
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        //DirectoryResource dr = new DirectoryResource(); //select all dictionaries
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        //for(File f : dr.selectedFiles()) {
        for(int i = 0; i < languages.length; i++) {
            FileResource fr = new FileResource("dictionaries/"+languages[i]);
            if(!dictionaries.containsKey(languages[i])) {
                dictionaries.put(languages[i], readDictionary(fr));
                System.out.println("Done reading " + languages[i] + " dictionary");
            }
        }
        //}
        FileResource fr = new FileResource(); //select file to decrypt
        String message = fr.asString();
        breakForAllLangs(message, dictionaries);
        //int count = countWords(decrypted, dictionaries);
        //System.out.println("Valid words: " + count);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<String>();
        for(String word : fr.words()) {
            word = word.toLowerCase();
            words.add(word);
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for(String word : message.split("\\W+")) {
            word = word.toLowerCase();
            if(dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int[] keys = {};
        VigenereCipher vc;
        String decryptedMessage = "";
        int count = 0, max = 0, keyLength = 0;
        for(int i = 1; i <= 100; i++) {
            keys = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            vc = new VigenereCipher(keys);
            String decrypt = vc.decrypt(encrypted);
            count = countWords(decrypt, dictionary);
            if(count > max) {
                max = count;
                decryptedMessage = decrypt;
                keyLength = i;
            }
        }
        System.out.println("Key length with most real words: " + keyLength);
        return decryptedMessage;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
        char mostCommonChar = ' ';
        int count = 0;
        for(String word : dictionary) {
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(!letters.containsKey(ch)) {
                    letters.put(ch, 1);
                } else {
                    letters.put(ch, letters.get(ch) + 1);
                }
            }
        }
        for(Character c : letters.keySet()) {
            if(letters.get(c) > count) {
                count = letters.get(c);
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxWords = 0;
        String decryptedMessage = "";
        HashMap<String, Integer> langsMostWords = new HashMap<String, Integer>();
        for(String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            decryptedMessage = breakForLanguage(encrypted, dictionary);
            int count = countWords(decryptedMessage, dictionary);
            langsMostWords.put(language, count);
        }
        for(String lang : langsMostWords.keySet()) {
            if(langsMostWords.get(lang) > maxWords) {
                maxWords = langsMostWords.get(lang);
                System.out.println(decryptedMessage);
                System.out.println("Language: " + lang + " with " + maxWords + " # of words\n");
            }
        }
    }
}
