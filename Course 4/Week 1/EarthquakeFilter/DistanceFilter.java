
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{

    private Location location;
    private double maxDistance;
    private String filterName;
    
    public DistanceFilter(Location loc, double distance, String name) {
        location = loc;
        maxDistance = distance;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDistance; 
    }
    
    public String getName() {
        return filterName;
    }
}
