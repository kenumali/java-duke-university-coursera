
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 953;
        
        //MarkovZero mz = new MarkovZero();
        //runModel(mz, st, size, seed);
    
        //MarkovOne mOne = new MarkovOne();
        //runModel(mOne, st, size, seed);
        
        MarkovModel mSeven = new MarkovModel(7);
        runModel(mSeven, st, size, seed);
        
        //MarkovFour mFour = new MarkovFour();
        //runModel(mFour, st, size, seed);

    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 50;
        int seed = 531;
        
        //st = "this is a test yes a test";
        EfficientMarkovModel mTwo = new EfficientMarkovModel(5);
        runModel(mTwo, st, size, seed);
        
        //MarkovModel markov = new MarkovModel(2);
        //runModel(markov, st, size, seed);
        
        //mTwo.printHashMapInfo();
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        long startTime = System.nanoTime();
        EfficientMarkovModel mTwo = new EfficientMarkovModel(2);
        runModel(mTwo, st, size, seed);
        long endTime = System.nanoTime();
        System.out.println("Time for EfficientMarkovModel: " + ((endTime - startTime) / 1000000));
        
        startTime = System.nanoTime();
        MarkovModel markov = new MarkovModel(2);
        runModel(markov, st, size, seed);
        endTime = System.nanoTime();
        System.out.println("Time for MarkovModel: " + ((endTime - startTime) / 1000000));
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}
