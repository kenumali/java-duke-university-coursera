
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
public class Tester {

    public void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(3);
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        String message = fr.asString();
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    public void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource("VigenereTestData/titus-small_key5.txt");
        String message = fr.asString();
        int key = cc.getKey(message);
        String decrypted = cc.decrypt(message);
        System.out.println("Key used to decrypt: " + key + "\n" +
            "Decrypted message: " + decrypted);
        
        cc = new CaesarCracker('a');
        fr = new FileResource("VigenereTestData/oslusiadas_key17.txt");
        message = fr.asString();
        key = cc.getKey(message);
        decrypted = cc.decrypt(message);
        System.out.println("Key used to decrypt: " + key + "\n" +
            "Decrypted message: " + decrypted);
    }
    
    public void testVigenereCipher() {
        int[] key = {17, 14, 12, 4}; //"rome"
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        String message = fr.asString();
        String encrypted = vc.encrypt(message);
        System.out.println(encrypted);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
        
    }
    
    public void testVigenereBreaker() {
        VigenereBreaker vb = new VigenereBreaker();
        /*String str = vb.sliceString("abcdefghijklm", 0, 3); //adgjm
        System.out.println(str);
        str = vb.sliceString("abcdefghijklm", 1, 3); //behk
        System.out.println(str);
        str = vb.sliceString("abcdefghijklm", 2, 3); //cfil
        System.out.println(str);
        
        str = vb.sliceString("abcdefghijklm", 0, 4); //aeim
        System.out.println(str);
        str = vb.sliceString("abcdefghijklm", 1, 4); //bfj
        System.out.println(str);
        str = vb.sliceString("abcdefghijklm", 2, 4); //cgk
        System.out.println(str);
        str = vb.sliceString("abcdefghijklm", 3, 4); //dhl
        System.out.println(str);
        
        str = vb.sliceString("abcdefghijklm", 0, 5); //afk
        System.out.println(str);
        str = vb.sliceString("abcdefghijklm", 1, 5); //bgl
        System.out.println(str);
        str = vb.sliceString("abcdefghijklm", 2, 5); //chm
        System.out.println(str);
        str = vb.sliceString("abcdefghijklm", 3, 5); //di
        System.out.println(str);
        str = vb.sliceString("abcdefghijklm", 4, 5); //ej
        System.out.println(str);
        
        FileResource fr = new FileResource("VigenereTestData/athens_keyflute.txt");
        String msg = fr.asString();
        String eKey = "flute";
        int[] key = vb.tryKeyLength(msg, eKey.length(), 'e'); // {5, 11, 20, 19, 4}
        
        System.out.println(Arrays.toString(key));*/
        //vb.breakVigenere();
        //FileResource fr = new FileResource("messages/secretmessage1.txt");
        //String msg = fr.asString();
        //int[] key = vb.tryKeyLength(msg, 4, 'e'); // {5, 11, 20, 19, 4}
        
        //System.out.println(Arrays.toString(key));
        vb.breakVigenere();
    }
}
