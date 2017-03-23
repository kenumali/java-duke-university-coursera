
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{

    private double min;
    private double max;
    private String filterName;
    
    public MagnitudeFilter(double minMag, double maxMag, String name) {
        min = minMag;
        max = maxMag;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= min && qe.getMagnitude() <= max;
    }
    
    public String getName() {
        return filterName;
    }
}
