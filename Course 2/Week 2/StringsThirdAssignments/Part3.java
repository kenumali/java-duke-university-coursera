
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part3 {
    
    public int findStopCodon(String dna, int startCodon, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startCodon + 3);
        while(currIndex != -1) {
            int diff = currIndex - startCodon;
            if(diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where) {
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        
        while(true) {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()) {
                break;
            }
            //System.out.println(currentGene);
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex + 1) + currentGene.length();
        }
        return geneList;
    }
    
    public double cgRatio(String dna) {
        int countC = 0;
        int countG = 0;
        int cIndex = dna.indexOf("C");
        int gIndex = dna.indexOf("G");
        while(true) {
            if(cIndex == -1 && gIndex == -1) {
                break;
            } else {
                if(cIndex != -1) {
                    countC += 1;
                    cIndex = dna.indexOf("C", cIndex + 1);
                }
                if(gIndex != -1) {
                    countG +=1;
                    gIndex = dna.indexOf("G", gIndex + 1);
                }
            }
        }
        return ((double) countC + countG) / dna.length();
    }
    
    public int countCTG(String dna) {
        int startIndex = dna.indexOf("CTG");
        int count = 0;
        while(startIndex != -1) {
            count++;
            startIndex = dna.indexOf("CTG", startIndex + 1);
        }
        return count;
    }
    
    public void processGenes(StorageResource sr) {
        String longest = "";
        double ratio = 0.35d;
        int countcg = 1;
        int count = 1;
        for(String genes : sr.data()) {
            if(genes.length() > 60) {
                System.out.println("Gene is: " + genes);
                System.out.println("Number of strings: " + count);
                count++;
            }
            if(cgRatio(genes) > ratio) {
                System.out.println("Gene with ratio > 0.35: " + genes);
                System.out.println("Ratio is: " + cgRatio(genes) + " - count: "+ countcg);
                countcg++;
            }
            if(genes.length() > longest.length()) {
                longest = genes;
            }
        }
        
        System.out.println("Longest Gene is: " + longest);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        processGenes(getAllGenes(dna));
    }
}
