
/**
 * Write a description of WordPlay here.
 * 
 * @author Kenneth 
 * @version 10.2.2017
 */
public class WordPlay {
    
    public boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        boolean vowel = false;
        for(int i = 0; i < vowels.length(); i++) {
            if(vowels.charAt(i) == ch) {
                return true;
            }
        }
        return vowel;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for(int i = 0; i < newPhrase.length(); i++) {
            if(isVowel(newPhrase.charAt(i))) {
                newPhrase.setCharAt(i, ch);
            }
        }
        return newPhrase.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        int pointer = 0;
        int index = phrase.indexOf(ch, pointer);
        int counter = 0;
        for(int i = 0; i < newPhrase.length(); i++) {
            int remainder = (index + 1) % 2;
            char currChar = newPhrase.charAt(i);
            if(remainder == 0 && Character.toLowerCase(currChar) == Character.toLowerCase(ch)) {
                newPhrase.setCharAt(i, '+');
            } else if(remainder == 1 && Character.toLowerCase(currChar) == Character.toLowerCase(ch)){
                newPhrase.setCharAt(i, '*');
            }
            pointer++;
            index = phrase.indexOf(ch, pointer);
        }
        return newPhrase.toString();
    }
    
    public void testIsVowel() {
        System.out.println(isVowel('a'));
        System.out.println(isVowel('U'));
        System.out.println(isVowel('F'));
    }
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }
    
    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a')); //dn* ctg+*+ctg+
        System.out.println(emphasize("Mary Bella Abracadabra", 'a')); //M+ry Bell+ +br*c*d*br+
        System.out.println(emphasize("AbraCAdaBRaSisBumbA", 'A')); //*br+C+d+BR*SisBumb*
    }
}
