
/**
 * Write a description of AssignmentOne here.
 * 
 * @author Kenneth Umali 
 * @version 21.2.2017
 */

import edu.duke.*;
import java.util.HashMap;
public class CodonCount {

    private HashMap<String, Integer> dnaMap;
    
    public CodonCount() {
        dnaMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        dna = dna.toUpperCase();
        for(int i = start; i < dna.length(); i+=3) {
            if(!(i + 3 >= dna.length())) {
                String codon = dna.substring(i, i+3);
                if(!dnaMap.containsKey(codon)) {
                    dnaMap.put(codon, 1);
                } else {
                    dnaMap.put(codon, dnaMap.get(codon) + 1);
                }
            }
        }
    }
    
    public String getMostCommonCodon() {
        int largest = 0;
        String largestCodon = "";
        for(String codon : dnaMap.keySet()) {
            int currentCount = dnaMap.get(codon);
            if(currentCount > largest) {
                largest = currentCount;
                largestCodon = codon;
            }
        }
        return largestCodon;
    }
    
    void printCodonCounts(int start, int end) {
        for(String codon : dnaMap.keySet()) {
            int count = dnaMap.get(codon);
            if(count >= start && count <= end) {
                System.out.println(codon + "\t" + count);
            }
        }
        System.out.println("Unique Codons: " + dnaMap.size());
    }
    
    void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //dna = dna.toUpperCase();
        //buildCodonMap(1, dna);
        //buildCodonMap(0, dna);
        dnaMap.clear();
        //buildCodonMap(2, dna); //39
        buildCodonMap(2, dna); //34
        //buildCodonMap(0, dna); //36
        printCodonCounts(1,50); 
        System.out.println("Most Common Codon : " + getMostCommonCodon());
    }
}
