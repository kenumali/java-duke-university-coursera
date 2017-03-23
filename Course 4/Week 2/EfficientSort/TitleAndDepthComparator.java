
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int val = q1.getInfo().compareTo(q2.getInfo());
        if(val == 0) {
            val = Double.compare(q1.getDepth(), q2.getDepth());
        }
        return val;
    }
}
