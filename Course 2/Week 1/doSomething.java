
/**
 * Write a description of doSomething here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class doSomething {
    public void something() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource img = new ImageResource(f);
            String fn = img.getFileName();
            String fn2 = "c-" + fn;
            String fn3 = "cc-" + fn2;
            img.setFileName(fn2);
            img.save();
            img.setFileName(fn3);
            img.save();
        }
    }
}
