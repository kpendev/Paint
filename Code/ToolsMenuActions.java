/*
 * Contains all of the functions for the Tools Menu section
 */
package paint;

import java.util.Optional;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;
import static paint.DrawMenuActions.drawSettings;
import static paint.DrawMenuActions.editFreeHandAction;
import static paint.EditMenuActions.redoArrayDeque;
import static paint.EditMenuActions.undoArrayDeque;
import static paint.FileMenuActions.writableImage;
import static paint.Miscellaneous.loadScreenshot;
import static paint.Miscellaneous.screenshot;
import static paint.SaveHandling.setToNotSavedState;
import static paint.WindowSetUp.canvas;
import static paint.WindowSetUp.canvasGraphicsContext;
import static paint.WindowSetUp.colorPicker;
import static paint.WindowSetUp.fillButton;
import static paint.WindowSetUp.lineWidthSlider;

/**
* This class contains all of the functions for the "Tools" menu
*/
public class ToolsMenuActions {
    //*************************************************************************
    //***********************Button Management*********************************
    //****Thus, the action statements perform only the option selected once****
    static private boolean DrawText=false; //has DrawText been selected?
    static private boolean drawPickColor=false; //has drawPickColor been selected?

    //Stores the coordinates of the mouse clicks
    private static Pair<Double, Double> initialTouch;
    
    //Cut Move Variables
    private static Pair<Double, Double> finalTouch; //stores the last click coordinates
    private static Pair<Double, Double> moveSelection;
    static private boolean cutMove=false; // has cutMove been selected?
    static private boolean imageCut=false; //has user made the cut selection
    static private double selectionWidth; //the width of the selection
    static private double selectionHeight; //the height of the selection
    static private WritableImage imgCut; //the writable image of the selection
    ImageView cutSelection;
    
    
   //***************************************************************************
   //******************************Pick Color***********************************
   //***************************************************************************
    /**
    * This function allows the user to load the color of something into the
    * color chooser by clicking on the canvas.
    */
    public static void toolsPickColor(){
   System.out.println("Pick Color");
   drawPickColor=true; //draw circle selected
        canvas.setOnMousePressed(event->{
            if(drawPickColor==true){
                // Obtain PixelReader
                screenshot();
                PixelReader pixelReader = writableImage.getPixelReader(); 

                // Get coordinates at MouseClick
                int xVal = (int) event.getX();
                int yVal = (int) event.getY();
                
                //Determine pixel color and set it
                Color colorPicked = pixelReader.getColor(xVal,yVal);
                colorPicker.setValue(colorPicked);
                drawPickColor=false;
            }
       });         
   }

   //***************************************************************************
   //******************************Insert Text**********************************
   //***************************************************************************
    /**
    * This function allows the user to insert text through a text box. 
    * After user enters the text, user can modify color, size, location, fill 
    * of the text before placing it permanently on the canvas.
    * button.
    */
   public static void toolsText(){
   System.out.println("Text");
        DrawText=true; //Insert text true
        
        //Dialog Box for text input
        TextInputDialog dialog = new TextInputDialog("Please enter text here"); //sample text
        dialog.setTitle("Text Input Dialog"); //Title
        dialog.setHeaderText("Text"); //header
        dialog.setContentText("Text:"); //input box label

        // Opens the dialog box an waits for user input
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){ //if user enters text
            screenshot();
            undoArrayDeque.push(writableImage); //add screenshot to stack
        }else{ //otherwise end 
            DrawText=false;
        }
        
        drawSettings(); //load the draw settings
        canvasGraphicsContext.setLineWidth(1); //set initial width
        
        canvas.setOnMouseMoved(event->{
            //clears the rectangle and loads screenshot back in so that the circle updates as you draw it
            if(DrawText==true){
                initialTouch = new Pair<>(event.getX(), event.getY()); // pairs the mouse coordinates
                
                drawSettings();
                canvasGraphicsContext.setLineWidth(1); //initial line width
                loadScreenshot();
                canvasGraphicsContext.setFont(Font.font(lineWidthSlider.getValue()*5)); //Multiplied by 5 because slider was originally optimized for lines
                
                if(fillButton.isSelected()){ //fill if fill selected
                    canvasGraphicsContext.fillText(result.get(), event.getX(), event.getY());
                }else{
                    canvasGraphicsContext.strokeText(result.get(), event.getX(), event.getY());
                }
                
            }
       });
        
       canvas.setOnMouseReleased(event->{
            if(DrawText==true){
                screenshot();
                loadScreenshot();
                redoArrayDeque.clear(); //clear the redo stack
                redoArrayDeque.push(writableImage); //push last image to redo
                DrawText=false; //draw acction completed, must click again
                setToNotSavedState(); //file needs to be saved
            }
       });          
   }
   //**************************************************************************
   //**************************Eraser******************************************
   //**************************************************************************
   /**
    * This function allows the user to erase thing on the canvas.
    */
    public static void eraser(){
        colorPicker.setValue(Color.WHITE); //make the color of the picker white to "erase"
        editFreeHandAction(); //call the free hand tool
    }
    
    
    //**************************************************************************
    //**************************Cut and Move************************************
    //**************************************************************************
    /**
    * This function allows the user to select and area of the canvas by clicking
    * and dragging. After the selection is made, user can move that selection
    * anywhere.
    */
    public static void cutMove(){
        cutMove=true; //cut move selected
        canvasGraphicsContext.setStroke(Color.BLACK); //get the stroke size
        canvasGraphicsContext.setLineWidth(1); //set the line width
        
        canvas.setOnMousePressed(event->{ //if click on canvas and going to select section
            if(cutMove==true && imageCut==false){
                screenshot();
                undoArrayDeque.push(writableImage); //add screenshot to undo stack so cut can be undone
                System.out.println("Start Cut Selection");
                initialTouch = new Pair<>(event.getX(), event.getY()); //pair the x and y coordinates for mouse
            }
            
            if(cutMove==true && imageCut==true){ //if move completed
            screenshot();
            loadScreenshot();
            
            redoArrayDeque.clear(); //clear the redo stackk
            redoArrayDeque.push(writableImage); //push last image to the stack
            
            imageCut=false; //reset condition
            cutMove=false; //reset condition
            } 
        });
        
        canvas.setOnMouseDragged(event->{
            if(cutMove==true && imageCut==false){ //if user is selecting
                System.out.println("SELECTING");
                loadScreenshot();
                finalTouch = new Pair<>(event.getX(), event.getY()); //pair the x and y coordinates for mouse
                canvasGraphicsContext.strokeRect(initialTouch.getKey(), initialTouch.getValue(), finalTouch.getKey()-initialTouch.getKey(), finalTouch.getValue()-initialTouch.getValue()); //starts drawing selection box
                
                selectionWidth=finalTouch.getKey()-initialTouch.getKey(); //width of selection
                selectionHeight=finalTouch.getValue()-initialTouch.getValue(); //height of selection
            } 
        });
        
        canvas.setOnMouseReleased(event->{ //selection is made
            if(cutMove==true && imageCut==false){
                System.out.println("Selection Finished");
                loadScreenshot(); //make the square disapear
                
                //START CREATING THE IMAGE
                //sets up settings for the screenshot selection (the rectangle that shows what you are selecting)
                SnapshotParameters params = new SnapshotParameters();
                imgCut = new WritableImage( (int) selectionWidth, (int) selectionHeight);
                params.setFill(Color.TRANSPARENT); 
                
                //makes the screenshot 
                Rectangle2D viewport = new Rectangle2D(initialTouch.getKey(), initialTouch.getValue()+50, selectionWidth, selectionHeight); //50 accounts for the toolbar.
                params.setViewport(viewport);
                canvas.snapshot(params, imgCut);
                
                canvasGraphicsContext.clearRect(initialTouch.getKey(), initialTouch.getValue(), selectionWidth,  selectionHeight); //clears the seletion area behind the selection
                screenshot();
              
              imageCut=true; //slection hsa been made
            } 
        });        
               
        canvas.setOnMouseMoved(event->{ //start moving the slection around
            if(cutMove==true && imageCut==true){ 
             System.out.println("Selection Moving");
             
             moveSelection = new Pair<>(event.getX(), event.getY()); //pair the x and y coordinates for mouse
             
             loadScreenshot(); //everytime one mouse is moved, load in the screenshot
             canvasGraphicsContext.drawImage(imgCut, moveSelection.getKey(), moveSelection.getValue(), selectionWidth,  selectionHeight);
            } 
        });
    }
}
