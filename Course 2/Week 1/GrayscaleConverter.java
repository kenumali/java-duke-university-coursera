
/**
 * Write a description of GrayscaleConverter here.
 * 
 * @author Kenneth Umali
 * @version 25.1.2017
 */

import edu.duke.*;
import java.io.File;

public class GrayscaleConverter {
    
    public void selectFile() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource img = new ImageResource(f);
            ImageResource gray = makeItGray(img);
            saveImg(gray, f);
            gray.draw();
        }
    }
    
    public ImageResource makeItGray(ImageResource img) {
        //creates a new ImageResource object, does not inherit img properties
        ImageResource grayImage = new ImageResource(img.getWidth(), img.getHeight());
        for (Pixel px : grayImage.pixels()) {
            Pixel pix = img.getPixel(px.getX(), px.getY());
            int average = (pix.getRed() + pix.getGreen() + pix.getBlue()) / 3;
            px.setRed(average);
            px.setGreen(average);
            px.setBlue(average);
        }
        return grayImage;
    }
    
    public void saveImg(ImageResource img, File f) {
        String filename = f.getName();
        if(filename.substring(0, 5).equals("gray-")) {
            System.out.println("File already has grayscale copy");
        } else {
            String grayscaleCopy = "gray-" + filename;
            // used getParentFile() to save grayscale image in the same folder as the original image
            img.setFileName(f.getParentFile() + "\\" + grayscaleCopy); 
            img.save();
        }
    }
}
