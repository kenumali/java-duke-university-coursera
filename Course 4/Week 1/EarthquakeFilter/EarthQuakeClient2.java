import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0);
        /*Filter f = new MagnitudeFilter(4.0, 5.0);
        ArrayList<QuakeEntry> m7  = filter(list, f);
        f = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> shallowQuake = filter(m7, f);*/
        /*Location japan = new Location(35.42, 139.43);
        Filter f = new DistanceFilter(japan, 10000000, "Distance");
        ArrayList<QuakeEntry> quakeList = filter(list, f);
        f = new PhraseFilter("end", "Japan", "Phrase");
        ArrayList<QuakeEntry> quakes = filter(quakeList, f);*/
        
        /*Filter f = new MagnitudeFilter(4.0, 5.0, "Magnitude");
        ArrayList<QuakeEntry> quake = filter(list, f);
        f = new DepthFilter(-35000, -12000, "Depth");
        ArrayList<QuakeEntry> quakes = filter(quake, f);*/
        
        Location denver = new Location(39.7392, -104.9903);
        /*Filter f = new DistanceFilter(denver, 1000000, "Distance");
        ArrayList<QuakeEntry> quake = filter(list, f);
        f = new PhraseFilter("end", "a","Phrase");
        ArrayList<QuakeEntry> quakes = filter(quake, f);*/
        
        Filter f = new MagnitudeFilter(3.5, 4.5, "Magnitude");
        ArrayList<QuakeEntry> quake = filter(list, f);
        f = new DepthFilter(-55000, -20000,"DepthFilter");
        ArrayList<QuakeEntry> quakes = filter(quake, f);
        
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        }
        System.out.println("Found " + quakes.size() + " quakes that match the criteria");
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*for(QuakeEntry quake : list) {
            System.out.println(quake);
        }*/
        
        MatchAllFilter maf = new MatchAllFilter();
        /*maf.addFilter(new MagnitudeFilter(0.0, 2.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0, "Depth"));
        maf.addFilter(new PhraseFilter("any", "a", "Phrase"));*/
        
        maf.addFilter(new MagnitudeFilter(1.0, 4.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-180000, -30000.0, "Depth"));
        maf.addFilter(new PhraseFilter("any", "o", "Phrase"));
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        
        for(QuakeEntry quake : quakes) {
            System.out.println(quake);
        }
        System.out.println("Found " + quakes.size() + " quakes that match the criteria");
        System.out.println("Filters used are: " + maf.getName());
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*for(QuakeEntry quake : list) {
            System.out.println(quake);
        }*/
        
        MatchAllFilter maf = new MatchAllFilter();
        /*maf.addFilter(new MagnitudeFilter(0.0, 3.0, "Magnitude"));
        Location tulsa = new Location(36.1314, -95.9372);
        maf.addFilter(new DistanceFilter(tulsa, 10000000, "Distance"));
        maf.addFilter(new PhraseFilter("any", "Ca", "Phrase"));*/
        
        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "Magnitude"));
        Location billund = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(billund, 3000000, "Distance"));
        maf.addFilter(new PhraseFilter("any", "e", "Phrase"));
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        
        for(QuakeEntry quake : quakes) {
            System.out.println(quake);
        }
        System.out.println("Found " + quakes.size() + " quakes that match the criteria");
        System.out.println("Filters used are: " + maf.getName());
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
