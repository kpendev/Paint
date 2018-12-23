
package paint;

import java.util.ArrayDeque;
import javafx.scene.image.WritableImage;
import static paint.FileMenuActions.writableImage;
import static paint.Miscellaneous.loadScreenshot;
import static paint.Miscellaneous.screenshot;
import static paint.SaveHandling.setToNotSavedState;

/**
* This class contains all of the functions under the "Edit" menu.
*/
public class EditMenuActions { 
    //********Variables will be used for Undo/Redo**************
    static ArrayDeque<WritableImage> undoArrayDeque = new ArrayDeque<>(); //stack architecture storing the undos
    static ArrayDeque<WritableImage> redoArrayDeque = new ArrayDeque<>(); //stack architecture storing the redos

    
    /**
    * This function allows the user to undo actions
    */
    public static void Undo() {
        if(!undoArrayDeque.isEmpty()){
            System.out.println("Undo");       
            screenshot();     
            writableImage = undoArrayDeque.pop(); //takes the last operation off of the stack
            redoArrayDeque.push(writableImage);   //the last undo to redo stack so that user can redo
            loadScreenshot();                     //load the image off of the stack
            setToNotSavedState();
        }
    }
    
    /**
    * This function allows the user to redo actions
    */ 
    public static void Redo() {
        System.out.println("Redo");
        if(redoArrayDeque.size()>1){ //only if a undo has been made
            undoArrayDeque.push(redoArrayDeque.pop()); //add the last undo back to undoStack
            writableImage = redoArrayDeque.getFirst(); //take the first element and load that image in
            loadScreenshot();
        }
    }
 }
 