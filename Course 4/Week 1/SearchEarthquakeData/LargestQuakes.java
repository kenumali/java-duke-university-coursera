
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        /*for(QuakeEntry quake : list) {
            System.out.println(quake);
        }*/
        //int largestQuakeIndex = indexOfLargest(list);
        //System.out.println(largestQuakeIndex);
        ArrayList<QuakeEntry> largeQuakes = getLargest(list, 50);
        for(QuakeEntry quake : largeQuakes) {
            System.out.println(quake);
        }
    }
    
    private int indexOfLargest(ArrayList<QuakeEntry> data) {
        int largest = 0;
        double mag = 0.0;
        int count = 0;
        for(QuakeEntry quake : data) {
            double magnitude = quake.getMagnitude();
            if(magnitude > mag) {
               mag = magnitude;
               largest = data.indexOf(quake);
            }
        }
        return largest;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        int magnitude = 0;
        for(int i = 0; i < howMany; i++) {
            magnitude = indexOfLargest(copy);
            answer.add(copy.get(magnitude));
            copy.remove(magnitude);
        }
        return answer;
    }
}
