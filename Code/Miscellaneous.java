
package paint;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import static paint.FileMenuActions.writableImage;
import static paint.Paint.heightOfCanvas;
import static paint.Paint.widthOfCanvas;
import static paint.WindowSetUp.canvas;
import static paint.WindowSetUp.canvasGraphicsContext;

/**
 * This class contains the things that do not fit well in other classes
 */
public class Miscellaneous {
    
    /**
    * This function takes a screenshot of the current canvas.
    */
    public static void screenshot(){
        SnapshotParameters params = new SnapshotParameters();
        heightOfCanvas = (int)canvas.getHeight(); //get Height for screenshot
        widthOfCanvas = (int)canvas.getWidth(); //get width for screenshot
        
        writableImage = new WritableImage(widthOfCanvas, heightOfCanvas); //new writableImage
        params.setFill(Color.TRANSPARENT); //trasparent fill
        canvas.snapshot(params, writableImage); //takes the snapshot
    }
    
    /**
    * This function loads in the last screenshot taken of the current canvas.
    */
    public static void loadScreenshot(){
        canvasGraphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //clear the canvas
        canvasGraphicsContext.drawImage(writableImage, 0, 0, widthOfCanvas,  heightOfCanvas); //load image in
        canvasGraphicsContext = canvas.getGraphicsContext2D();
    }
}
