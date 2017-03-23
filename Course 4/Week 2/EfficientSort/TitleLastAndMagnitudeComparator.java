
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1_title = q1.getInfo();
        String q2_title = q2.getInfo();
        String q1_lastWord = q1_title.substring(q1_title.lastIndexOf(" ") + 1);
        String q2_lastWord = q2_title.substring(q2_title.lastIndexOf(" ") + 1);
        int val = q1_lastWord.compareTo(q2_lastWord);
        if(val == 0) {
            val = Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return val;
    }
}
