
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester {

    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        String st = "this is a test yes this is a test.";
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows + " " + follows.size());
        follows = markov.getFollows("e");
        System.out.println(follows + " " + follows.size());
        follows = markov.getFollows("es");
        System.out.println(follows + " " + follows.size());
        follows = markov.getFollows(".");
        System.out.println(follows + " " + follows.size());
        follows = markov.getFollows("t.");
        System.out.println(follows + " " + follows.size());
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String str = fr.asString();
        str = str.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(str);
        
        ArrayList<String> follows = markov.getFollows("he");
        System.out.println(follows.size());
    }
}
