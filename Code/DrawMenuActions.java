
package paint;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import static paint.DrawMenuActions.drawSettings;
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
* It contains all of the action functions under the "Draw" menu
*/
public class DrawMenuActions {
    //*************************************************************************
    //***********************Button Management*********************************
    //****Thus, the action statements perform only the option selected once****
    //*************************************************************************
    static private boolean DrawTwoPointLine=false; //has DrawTwoPointLine been selected?
    static private boolean DrawFreeHandAction=false; //has DrawFreeHandAction been selected?
    static private boolean DrawRectangle=false; //has DrawRectangle been selected?
    static private boolean DrawSquare=false; //has DrawSquare been selected?
    static private boolean DrawCircle=false; //has DrawCircle been selected?
    static private boolean drawTriangle=false; //has drawPickColor been selected?
    
    
    //Stores the coordinates of the mouse clicks
    private static Pair<Double, Double> initialTouch;
    
    

    /**
    * Retrieves information from slider, fill button, size slider
    * and updates the canvasGraphicsContext with those properties
    */
    public static void drawSettings(){
        canvasGraphicsContext.setStroke(colorPicker.getValue()); //get the stroke color
        canvasGraphicsContext.setLineWidth(lineWidthSlider.getValue()); //set the line width
        
        //If Fill is selected, fill shape otherwise set fill to transparent
        if(fillButton.isSelected()){
            canvasGraphicsContext.setFill(colorPicker.getValue());
        }else{
            canvasGraphicsContext.setFill(Color.TRANSPARENT);
        }
        
    }
    
   //***************************************************************************
   //**********************FREE HAND FUNCTION***********************************
   //***************************************************************************
   /**
   * This function allows the user to draw freely on the canvas.
   */
   public static void editFreeHandAction(){
       System.out.println("FreeHand Function!");
       DrawFreeHandAction=true; //freehand is selected
        canvas.setOnMousePressed(e->{
            if(DrawFreeHandAction==true){
            screenshot(); //take screenshot before adding a line
            undoArrayDeque.push(writableImage); //add screenshot to stack
            drawSettings(); //load draw settings
            canvasGraphicsContext.beginPath(); //starts drawing
            canvasGraphicsContext.moveTo(e.getX(), e.getY()); //updates the path
            canvasGraphicsContext.stroke(); //sets the stroke
            }
       });

        canvas.setOnMouseDragged(e->{
            if(DrawFreeHandAction==true){
                canvasGraphicsContext.lineTo(e.getX(), e.getY()); //adds to the path
                canvasGraphicsContext.stroke();
            }
       });
        canvas.setOnMouseReleased(event->{
            if(DrawFreeHandAction==true){
                screenshot();
                loadScreenshot();
                redoArrayDeque.clear();    //clear redo 
                redoArrayDeque.push(writableImage); //add last image to redo
                DrawFreeHandAction=false; //draw completed, need to click again to draw
                setToNotSavedState();
            }
       });
        
   }
   //***************************************************************************
   //******************Draw Line using Two Points*******************************
   //***************************************************************************
   /**
    * This function allows the user to draw a line using two points.
    * Press mouse button and keep pressing, drag to the second point, release
    * button.
    */
   public static void editTwoPointLine(){
   System.out.println("FunctionCalled");
        DrawTwoPointLine=true; //two point line selected
        canvas.setOnMousePressed(event->{
        drawSettings(); //load draw settings
            if(DrawTwoPointLine==true){
                screenshot(); //take screenshot before adding a line
                undoArrayDeque.push(writableImage);
                System.out.println("Start Drawing Line");
                initialTouch = new Pair<>(event.getX(), event.getY()); //pairs the x and y cordinates of the mouse click
            }
       });

        canvas.setOnMouseDragged(event->{
            if(DrawTwoPointLine==true){
                //clears the rectagle and loads the past screenshot so that the line updates as mouse is dragged
                loadScreenshot();
                canvasGraphicsContext.strokeLine(initialTouch.getKey(), initialTouch.getValue(), event.getX(), event.getY()); //gets coordinate of second point
            }
       });
       canvas.setOnMouseReleased(event->{
            //must take screenshot and load it in again.
            if(DrawTwoPointLine==true){
                screenshot();
                loadScreenshot();
                redoArrayDeque.clear(); //clear redo 
                redoArrayDeque.push(writableImage); //push last image to redo
                DrawTwoPointLine=false; //draw action completed, must click again
                setToNotSavedState(); //file need to be saved
            }
       });       
   }
   //***************************************************************************
   //******************************Draw Rectangle*******************************
   //***************************************************************************
   /**
    * This function allows the user to draw a rectangle using two points.
    * Press mouse button and keep pressing, drag to the second point, release
    * button.
    */
   public static void drawRectangle(){
   System.out.println("Rectangle");
        DrawRectangle=true; // draw rectangle is selected
        canvas.setOnMousePressed(event->{
        drawSettings(); //load draw settings
            if(DrawRectangle==true){
                screenshot(); //take screenshot before adding a line
                undoArrayDeque.push(writableImage); //add screenshot to stack
                screenshot();
                System.out.println("Start Drawing Rectangle");
                initialTouch = new Pair<>(event.getX(), event.getY()); //pair the x and y coordinates for mouse
            }
       });

        canvas.setOnMouseDragged(event->{
            //must clear rectangle and load screenshot back in so that the rectangle is updated as mouse is dragged
            if(DrawRectangle==true){
                loadScreenshot();
                if(fillButton.isSelected()){ //fill if fill is selected
                    canvasGraphicsContext.fillRect(initialTouch.getKey(), initialTouch.getValue(), event.getX()-initialTouch.getKey(), event.getY()-initialTouch.getValue());
                }else{
                    canvasGraphicsContext.strokeRect(initialTouch.getKey(), initialTouch.getValue(), event.getX()-initialTouch.getKey(), event.getY()-initialTouch.getValue());
                }
            }
       });
       canvas.setOnMouseReleased(event->{
            if(DrawRectangle==true){
                screenshot();
                loadScreenshot();
                redoArrayDeque.clear(); //clear redo
                redoArrayDeque.push(writableImage); //push last image to redo
                DrawRectangle=false; //draw complete, must click again
                setToNotSavedState(); //file need to be saved
            }
       });       
   }
   
   //***************************************************************************
   //******************************Draw Square**********************************
   //***************************************************************************
   /**
    * This function allows the user to draw a square using two points.
    * Press mouse button and keep pressing, drag to the second point, release
    * button.
    */
   public static void drawSquare(){
   System.out.println("Square");
        DrawSquare=true; //draw square selected
        canvas.setOnMousePressed(event->{
        drawSettings(); //load ddraw settings
            if(DrawSquare==true){
                screenshot(); //take screenshot before adding a line
                undoArrayDeque.push(writableImage); //add screenshot to stack
                screenshot(); //take screenshot
                System.out.println("Start Drawing Square");
                initialTouch = new Pair<>(event.getX(), event.getY()); // pairs the mouse coordinates
            }
       });

        canvas.setOnMouseDragged(event->{
            if(DrawSquare==true){ //Clear rectangle and load screenshot back in so that square updates as you draw
                loadScreenshot();
                if(fillButton.isSelected()){ //if fill selected, fill shape
                    canvasGraphicsContext.fillRect(initialTouch.getKey(), initialTouch.getValue(), event.getX()-initialTouch.getKey(), event.getX()-initialTouch.getKey());
                }else{
                    canvasGraphicsContext.strokeRect(initialTouch.getKey(), initialTouch.getValue(), event.getX()-initialTouch.getKey(), event.getX()-initialTouch.getKey());
                }
            }
       });
       canvas.setOnMouseReleased(event->{
            if(DrawSquare==true){
                screenshot(); 
                loadScreenshot();
                redoArrayDeque.clear(); //clear the redo
                redoArrayDeque.push(writableImage); //push the last image to redo
                DrawSquare=false; //the square is drawn, must click again
                setToNotSavedState(); //file need to be saved
            }
       });       
   }
   
   //***************************************************************************
   //******************************Draw Circle**********************************
   //***************************************************************************
   /**
    * This function allows the user to draw a circle using two points.
    * Press mouse button and keep pressing, drag to the second point, release
    * button.
    */
   public static void drawCircle(){
   System.out.println("Cirlce");
        DrawCircle=true; //draw circle selected
        canvas.setOnMousePressed(event->{
            drawSettings(); //load draw settings in
            if(DrawCircle==true){
                screenshot(); //take screenshot before adding a line
                undoArrayDeque.push(writableImage); //add screenshot to stack
                screenshot();
                System.out.println("Start Drawing Circle");
                initialTouch = new Pair<>(event.getX(), event.getY()); // pairs the mouse coordinates
            }
       });

        canvas.setOnMouseDragged(event->{
            //clears the rectangle and loads screenshot back in so that the circle updates as you draw it
            if(DrawCircle==true){
                loadScreenshot();
                if(fillButton.isSelected()){ //fill if fill selected
                    canvasGraphicsContext.fillOval(initialTouch.getKey(), initialTouch.getValue(), event.getX()-initialTouch.getKey(), event.getX()-initialTouch.getKey());
                }else{
                    canvasGraphicsContext.strokeOval(initialTouch.getKey(), initialTouch.getValue(), event.getX()-initialTouch.getKey(), event.getX()-initialTouch.getKey());
                }
            }
       });
       canvas.setOnMouseReleased(event->{
            if(DrawCircle==true){
                screenshot();
                loadScreenshot();
                redoArrayDeque.clear(); //clear the redo stack
                redoArrayDeque.push(writableImage); //add last image to redo stack
                DrawCircle=false; //draw action completed, must click again
                setToNotSavedState(); //file need to be saved
            }
       });          
   }
  
   //***************************************************************************
   //******************************Draw Triangle********************************
   //***************************************************************************
   /**
    * This function allows the user to draw a Triangle using two points.
    * Press mouse button and keep pressing, drag to the second point, release
    * button.
    */
   public static void drawTriangle(){
   System.out.println("drawTriangle");
    drawTriangle=true;
        canvas.setOnMousePressed(event->{
            drawSettings(); //load draw settings
            if(drawTriangle==true){
                screenshot(); //take screenshot before adding a line
                undoArrayDeque.push(writableImage); //add screenshot to stack
                screenshot();
                System.out.println("Start Drawing Triangle");
                initialTouch = new Pair<>(event.getX(), event.getY()); //pair the x and y coordinates for mouse
            }
       });

        canvas.setOnMouseDragged(event->{
            //must clear rectangle and load screenshot back in so that the rectangle is updated as mouse is dragged
            if(drawTriangle==true){
                loadScreenshot();
                double firstX = initialTouch.getKey(); //the first x point click
                double firstY = initialTouch.getValue(); //the first y point click
                
                double secondX = event.getX(); //the dragged x point
                double secondY = event.getY(); //the dragged y point
                
                double[] xPoints = {firstX, secondX,firstX+((secondX-firstX)/2)}; //array of the x points and calculate the third point
                double[] yPoints = {firstY, firstY,secondY}; //array of y points
                
                if(fillButton.isSelected()){ //fill if fill is selected
                    canvasGraphicsContext.fillPolygon(xPoints, yPoints, 3);
                }else{
                    canvasGraphicsContext.strokePolygon(xPoints, yPoints, 3);
                }
            }
       });
        
       canvas.setOnMouseReleased(event->{
            if(drawTriangle==true){
                screenshot();
                loadScreenshot();
                redoArrayDeque.clear(); //clear the redo stack
                redoArrayDeque.push(writableImage); //push last image to stack
                drawTriangle=false; //draw complete, must click again
                setToNotSavedState(); //file needs to be saved
            }
       });       
   }
}