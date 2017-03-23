
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author Kenneth Umali 
 * @version 15.2.2017
 */
public class CaesarCipherTwo {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) +
                           alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + 
                           alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        String f = "";
        char ch;
        for(int i = 0; i < encrypted.length(); i++) {
            int idx = alphabet.indexOf(encrypted.charAt(i));
            char tempChar = Character.toLowerCase(encrypted.charAt(i));
            if(idx != -1) {
                if(i % 2 == 0) {
                    ch = shiftedAlphabet1.charAt(idx);
                    if(Character.isUpperCase(encrypted.charAt(i))) {
                        encrypted.setCharAt(i, Character.toUpperCase(ch));
                    } else {
                        encrypted.setCharAt(i, ch);
                    }
                } else {
                    ch = shiftedAlphabet2.charAt(idx);
                    if(Character.isUpperCase(encrypted.charAt(i))) {
                        encrypted.setCharAt(i, Character.toUpperCase(ch));
                    } else {
                        encrypted.setCharAt(i, ch);
                    }
                }
            }
            
        }
        return encrypted.toString();
    }
    
    /*public String decrypt(String input) {
        CaesarCipherTwo cc2 = new CaesarCipherTwo();
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
    }*/
    
}
