
/**
 * Write a description of Part3 here.
 * 
 * @author Kenneth Umali
 * @version 26.1.2017
 */
public class Part3 {
    
    public boolean twoOccurrences(String stringa, String stringb) {
        int count = 0;
        int lastIndex = 0;
        while(lastIndex != -1) {
            lastIndex = stringb.indexOf(stringa, lastIndex);
            System.out.println(lastIndex);
            if(lastIndex != -1) {
                count++;
                lastIndex += stringa.length();
            }
        }
        if(count > 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgatgta"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
    
    public String lastPart(String stringa, String stringb) {
        int firstOccurence = stringb.indexOf(stringa);
        if(firstOccurence == -1) {
            return stringb;
        } else {
            return stringb.substring(firstOccurence + stringa.length());
        }
    }
}
