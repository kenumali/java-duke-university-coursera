
/**
 * Write a description of CaesarCipher here.
 * 
 * @author Kenneth Umali 
 * @version 15.2.2017
 */
public class CaesarCipher {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + 
                          alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            char tempCh = Character.toLowerCase(ch); //uppercase to match with alphabet
            int index = alphabet.indexOf(tempCh); //get indexOf uppercased character
            if(index != -1) {
                char newCh = shiftedAlphabet.charAt(index);
                if(Character.isUpperCase(ch)) {
                    encrypted.setCharAt(i, Character.toUpperCase(newCh));
                } else {
                    encrypted.setCharAt(i, newCh);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
