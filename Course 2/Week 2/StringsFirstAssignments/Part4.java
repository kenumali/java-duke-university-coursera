
/**
 * Write a description of Part4 here.
 * 
 * @author Kenneth Umali 
 * @version 26.1.2017
 */

import edu.duke.*;
public class Part4 {
    
    public void readWebpage() {
        /*URLResource webpage = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String str : webpage.words()) {
            String lowercase = str.toLowerCase();
            if (lowercase.indexOf("youtube.com") != -1) {
                int leftDoubleQuote = str.indexOf("\"");
                int rightDoubleQuote = str.indexOf("\"", str.lastIndexOf("="));
                System.out.println(str.substring(leftDoubleQuote + 1, rightDoubleQuote));
            }
        }*/
        URLResource file = new  URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String item : file.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {
                int beg = item.lastIndexOf("\"",pos); //find the specified string backwards
                int end = item.indexOf("\"", pos+1); //finds the specified string forwards
                System.out.println(item.substring(beg+1,end));
            }
       	}
    }
}
