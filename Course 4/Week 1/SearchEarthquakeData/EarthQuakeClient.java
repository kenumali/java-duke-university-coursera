import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData) {
            if(quake.getMagnitude() > magMin) {
                answer.add(quake);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData) {
            if(quake.getLocation().distanceTo(from) < distMax) {
                answer.add(quake);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData) {
            if(quake.getDepth() > minDepth && quake.getDepth() < maxDepth) {
                answer.add(quake);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData) {
            String title = quake.getInfo();
            if(where.equals("end") && title.endsWith(phrase)) {
                answer.add(quake);
            } else if(where.equals("start") && title.startsWith(phrase)) {
                answer.add(quake);
            } else if(where.equals("any") && title.indexOf(phrase) != -1) {
                answer.add(quake);
            }
        }
        return answer;
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigList = filterByMagnitude(list, 5.0);
        for(int i = 0; i < bigList.size(); i++) {
            System.out.println(bigList.get(i));
        }
        System.out.println("Found " + bigList.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> closeList = filterByDistanceFrom(list, 1000*1000, city);
        for(int i = 0; i < closeList.size(); i++) {
            double distanceInMeters = city.distanceTo(closeList.get(i).getLocation());
            System.out.println(distanceInMeters/1000 + " " + closeList.get(i).getInfo());
        }
        System.out.println("Found " + closeList.size() + " quakes that match the criteria");
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        //ArrayList<QuakeEntry> depthList = filterByDepth(list, -12000.0, -10000.0);
        ArrayList<QuakeEntry> depthList = filterByDepth(list, -4000.0, -2000.0);
        System.out.println("Find quakes with depth between -4000.0 and -2000.0");
        for(int i = 0; i < depthList.size(); i++) {
            System.out.println(depthList.get(i));
        }
        System.out.println("Found " + depthList.size() + " quakes that match the criteria");
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        //ArrayList<QuakeEntry> phraseList = filterByPhrase(list, "start", "Quarry Blast");
        //ArrayList<QuakeEntry> phraseList = filterByPhrase(list, "end", "Alaska");
        ArrayList<QuakeEntry> phraseList = filterByPhrase(list, "any", "Can");
        for(int i = 0; i < phraseList.size(); i++) {
            System.out.println(phraseList.get(i));
        }
        System.out.println("Found " + phraseList.size() + " quakes that match the criteria");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

}
