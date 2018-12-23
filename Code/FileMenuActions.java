
package paint;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import static paint.Paint.heightOfCanvas;
import static paint.Paint.widthOfCanvas;
import static paint.SaveHandling.setToSavedState;
import static paint.WindowSetUp.canvas;
import static paint.WindowSetUp.canvasGraphicsContext;


/**
* This class contains all of the functions in the "File" menu
*/
public class FileMenuActions {
    
    //********FILE VARIABLES*******
    static WritableImage writableImage;
    static File file =null; //the file that is open/saved
    static Image image= null; //the image on the canvas
    static ImageView imgView; //the image on the canvas
    //********************Open Function************************************
    /**
    * Function that allows the user to open an image (JPG, PNG, JPEG), by opening
    * a file explorer.
    */
    static public void fileOpenAction(){
        FileChooser fileChooser = new FileChooser();
            
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterJPEG, extFilterPNG);
              
        //Show open file dialog and convert for the image
        file = fileChooser.showOpenDialog(null);
        image = new Image(file.toURI().toString()); //Convert to a string to Image constructor can accept it
        
        
        double myImageHeight=image.getHeight();
        double myImageWidth=image.getWidth();
        double scalingFactor=1;
        
        if(myImageWidth>widthOfCanvas-1 || myImageHeight>heightOfCanvas-1){ //if image is bigger than the canvas must scale
            if(myImageWidth>myImageHeight){   // if the width is too big, scale width
               scalingFactor=1/(myImageWidth/(widthOfCanvas-10));
               
            }else{  //if the height is too big, scale height
               scalingFactor=1/(myImageHeight/(heightOfCanvas-10)); 
            }
        }

        imgView = new ImageView(image);

        //Displaying Image-adjusted based on the canvas size
        canvasGraphicsContext.drawImage(image, 5, 5, myImageWidth*scalingFactor,  myImageHeight*scalingFactor);
    }
    
    
    //********************Save As Function************************************
    /**
    * Function allows user to determine where to save the canvas by opening
    * a file explorer and allows user to select image data type (JPG, PNG, JPEG).
    */
    static public void fileSaveAsAction(){
        System.out.println("In save as function");
        
        //Set up FileChooser and the extension filter
        FileChooser ImageSaver = new FileChooser();
        ImageSaver.setTitle("Save Image File");
        
        //image data types
        ImageSaver.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG"),
        new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG"),
        new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg"));
        
        // This try-catch saves the image 
        File savefile = ImageSaver.showSaveDialog(null);
        try {
            writableImage = new WritableImage(widthOfCanvas, heightOfCanvas);
            canvas.snapshot(null, writableImage); //make a snapshot and store it in writableImage
            
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null); //convert
            ImageIO.write(renderedImage, "png", savefile); //saves the file
            
            file = savefile; //Updates the file that we are using
            setToSavedState();
        } catch (IOException ex) {
            System.err.println("ERROR: Unable to Save");
        } 
    }
    //********************Save Function************************************
    /**
    * Function allows user to save canvas if a file is already created.
    * If no file exists, file explore opens for user to select location/name.
    */
    static public void fileSaveAction(){
        
        if (file != null) { //if a file is present
        // This try-catch saves the image   
            try {
                writableImage = new WritableImage(widthOfCanvas, heightOfCanvas);
                canvas.snapshot(null, writableImage); //make a snapshot and store it in writableImage
                
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null); //convert 
                ImageIO.write(renderedImage, "png", file); //saves the file
                
                System.out.println("saved");
                setToSavedState();
            } catch (IOException e) {
                System.err.println("ERROR: Unable to Save");
            }
        }else{
            fileSaveAsAction(); 
        }
    }
}  