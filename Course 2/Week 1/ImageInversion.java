
/**
 * Write a description of ImageInversion here.
 * 
 * @author Kenneth Umali 
 * @version 25.1.2017
 */

import edu.duke.*;
import java.io.File;

public class ImageInversion {
    
    public void selectFile() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource img = new ImageResource(f);
            ImageResource inverted = invertImg(img);
            saveImg(inverted, f);
            inverted.draw();
        }
    }
    
    public ImageResource invertImg(ImageResource img) {
        //creates a new ImageResource object, does not inherit img properties
        ImageResource invertedImg = new ImageResource(img.getWidth(), img.getHeight());
        for (Pixel px : invertedImg.pixels()) {
            Pixel pix = img.getPixel(px.getX(), px.getY());
            int red = 255 - pix.getRed();
            int green = 255 - pix.getGreen();
            int blue = 255 - pix.getBlue();
            px.setRed(red);
            px.setGreen(green);
            px.setBlue(blue);
        }
        return invertedImg;
    }
    
    public void saveImg(ImageResource img, File f) {
        String filename = f.getName();
        if(filename.substring(0, 9).equals("inverted-")) {
            System.out.println("File already has inverted copy");
        } else {
            String invertedCopy = "inverted-" + filename;
            // used getParentFile() to save grayscale image in the same folder as the original image
            img.setFileName(f.getParentFile() + "\\" + invertedCopy); 
            img.save();
        }
    }
}
