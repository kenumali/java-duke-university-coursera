
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{

    private String typeOfRequest;
    private String phrase;
    private String filterName;
    
    public PhraseFilter(String req, String phr, String name) {
        typeOfRequest = req;
        phrase = phr;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return typeOfRequest.equals("start") && qe.getInfo().startsWith(phrase) ? true
            : typeOfRequest.equals("end") && qe.getInfo().endsWith(phrase) ? true
            : typeOfRequest.equals("any") && qe.getInfo().indexOf(phrase) != -1 ? true
            : false;
    }
    
    public String getName() {
        return filterName;
    }
}
