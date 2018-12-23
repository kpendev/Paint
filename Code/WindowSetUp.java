/*
 * Functions to Set up Window
 */
package paint;

import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static paint.DrawMenuActions.drawCircle;
import static paint.DrawMenuActions.drawRectangle;
import static paint.DrawMenuActions.drawSquare;
import static paint.DrawMenuActions.drawTriangle;
import static paint.DrawMenuActions.editFreeHandAction;
import static paint.DrawMenuActions.editTwoPointLine;
import static paint.EditMenuActions.Redo;
import static paint.EditMenuActions.Undo;
import static paint.FileMenuActions.file;
import static paint.FileMenuActions.fileSaveAction;
import static paint.FileMenuActions.fileSaveAsAction;
import static paint.ToolsMenuActions.cutMove;
import static paint.ToolsMenuActions.eraser;
import static paint.ToolsMenuActions.toolsPickColor;
import static paint.ToolsMenuActions.toolsText;
import static paint.WindowSetUp.eraseButton;

/**
* This class contains all of the functions required to set up the layout of the
* screen
*/
public class WindowSetUp {
    //*******Variables for sizes of things*********
    static int widthOfMainScreen=800;
    static int heightOfMainScreen=800;
    static int heightOfCanvas=heightOfMainScreen-55;
    static int widthOfCanvas=widthOfMainScreen;
    
    //******Variables used for graphics*******
    static Stage primaryStage;
    static GridPane mainScreenLayout;
    static ColorPicker colorPicker;
    static Canvas canvas;
    static GraphicsContext canvasGraphicsContext;
    static HBox hbox;
    
    //************HBox**************************
    static Slider lineWidthSlider = new Slider(1, 30, 2); //0 to 100, start at 50
    static Separator sep = new Separator();
    static Separator sep2 = new Separator();
    static Separator sep3 = new Separator();
    static Separator sep4 = new Separator();
    static ToggleButton fillButton = new ToggleButton();
    static Button saveButton = new Button("*");
    static Button eraseButton = new Button();
    static Button undoButton = new Button();
    static Button redoButton = new Button();
    static Button dropperButton = new Button();
    static Button textButton = new Button();
    static Button freeHandButton = new Button();
    static Button lineButton = new Button();
    static Button rectangleButton = new Button();
    static Button squareButton = new Button();
    static Button triangleButton = new Button();
    static Button circleButton = new Button();
    static Button cutMoveButton = new Button();
    
    
    
    //**************************************************************
    //********************Create the Canvas*************************
    //**************************************************************
    /**
    * Creates the Canvas
    */
    public static void createCanvas(){
        canvas = new Canvas(widthOfCanvas, heightOfCanvas);
    }
    
    
    //**************************************************************
    //*********Set Up Canvas and Colorpicker************************
    //**************************************************************
    /**
    * Sets up the graphicsContext with the color picker and the canvas
    */
    public static void setUpColorPicker(){
        canvasGraphicsContext = canvas.getGraphicsContext2D();
         
        colorPicker = new ColorPicker(); //Create the Colorpicker
            
        canvasGraphicsContext.setFill(Color.WHITE);//set the fill
        canvasGraphicsContext.setStroke(Color.BLACK);//set stroke color
        canvasGraphicsContext.setLineWidth(5); //set stroke of line
        canvasGraphicsContext.fill();
        canvasGraphicsContext.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                widthOfCanvas,    //width of the rectangle
                heightOfCanvas);  //height of the rectangle
 
        canvasGraphicsContext.setFill(colorPicker.getValue()); //fill color from color picker
        canvasGraphicsContext.setStroke(colorPicker.getValue()); //stroke color from color picker
        canvasGraphicsContext.setLineWidth(1); //with of any lines drawn
    }
    
    
    //*********************************************************************
    //*************************SCREEN LAYOUT*******************************
    //*********************************************************************
    //Hbox
    /**
    * Creates the HBox
    */
    public static void createHBox(){
        hbox = new HBox();
        hbox.setSpacing(3); //Spacing between the items
        hbox.getChildren().addAll(saveButton, undoButton, redoButton,sep, textButton, dropperButton, fillButton, cutMoveButton, eraseButton, sep2, colorPicker, sep3, freeHandButton, lineButton, rectangleButton, squareButton, triangleButton, circleButton,sep4, lineWidthSlider); //add nodes to hbox
    }
    
    //slider
    /**
    * Creates the slider that will be used to resize things (stroke, font size)
    */
    public static void createSlider(){
        lineWidthSlider.setMaxWidth(100); //sets the width of the slider
        lineWidthSlider.setShowTickMarks(true); //show tick marks
        lineWidthSlider.setMajorTickUnit(10); // tick mark spacing
    }
    
    //*********************************************************************
    //*************************Buttons*************************************
    //*********************************************************************
    /**
    * It converts an image into a ImageView and sizes the image so that 
    * it can fit on the button.
    * 
    * @param mypath the path to the image 
    * 
    * @return ImageViewButtonImage the ImageView of the image specified
    */
    public static ImageView createSaveButton(String mypath){
        Image buttonImage = new Image(mypath); //specifiy image
        ImageView ImageViewButtonImage = new ImageView(buttonImage); //create an imageview
        ImageViewButtonImage.setFitWidth(17); //size it to fit button
	ImageViewButtonImage.setFitHeight(17); //size it to fit button
        return ImageViewButtonImage;
    }
    
    /**
    * Creates the separators between buttons
    */
    public static void buttonSeparator(){
        //first
        sep.setOrientation(Orientation.VERTICAL); //create it
        sep.setMaxHeight(25);                     //length
        //second
        sep2.setOrientation(Orientation.VERTICAL); //create it
        sep2.setMaxHeight(25);                     //lenth
        //third
        sep3.setOrientation(Orientation.VERTICAL); //create it
        sep3.setMaxHeight(25);                     //lenth
        //fourth
        sep4.setOrientation(Orientation.VERTICAL); //create it
        sep4.setMaxHeight(25);                     //lenth
        
    }
    
    //save button
    /**
    * Creates the save button with the icon and sets its action to the function
    */
    static void createSaveButton(){
        saveButton.setStyle("-fx-background-color: #ff6666; ");
        saveButton.setGraphic(createSaveButton("Images/save.png")); //get image
        saveButton.setOnAction(e ->  {                              //set action
           if (file != null) {
              fileSaveAction();
           }else{
              fileSaveAsAction();    
           }
        });
    }
    
    //eraser button
    /**
    * Creates the erase button with the icon and sets its action to the function
    */
    public static void createEraseButton(){
        eraseButton.setGraphic(createSaveButton("Images/eraser.png")); //set image
        eraseButton.setOnAction(e ->  {                                //set action
           eraser();
        });
    }
    
    //fill button
    /**
    * Creates the fill button with the icon and sets its action to the function
    */
    public static void createFillButton(){
        fillButton.setGraphic(createSaveButton("Images/Fill.png"));  //set image
    }
    
    //undo button
    /**
    * Creates the undo button with the icon and sets its action to the function
    */
    public static void createUndoButton(){                               //set image
        undoButton.setGraphic(createSaveButton("Images/undo.png")); //set action
        undoButton.setOnAction(e ->  {
           Undo();
        });
    }
    
    //redo button
    /**
    * Creates the redo button with the icon and sets its action to the function
    */
    public static void createRedoButton(){
        redoButton.setGraphic(createSaveButton("Images/redo.png")); //set image
        redoButton.setOnAction(e ->  {                              //set action
           Redo();
        });
    }
    
    //dropper button
    /**
    * Creates the dropper button with the icon and sets its action to the function
    */
    public static void createDropperButton(){
        dropperButton.setGraphic(createSaveButton("Images/dropper.png"));
        dropperButton.setOnAction(e ->  {
           toolsPickColor();
        });
    }
    //text button
    /**
    * Creates the text button with the icon and sets its action to the function
    */
    public static void createTextButton(){
        textButton.setGraphic(createSaveButton("Images/Text.png")); //set image
        textButton.setOnAction(e ->  {                              //set action
           toolsText();
        });
    }
    
    //freehand button
    /**
    * Creates the free hand draw button with the icon and sets its action to the function
    */
    public static void createFreeHandButton(){
        freeHandButton.setGraphic(createSaveButton("Images/FreeHand.png")); //set image
        freeHandButton.setOnAction(e ->  {                                 //set action
           editFreeHandAction();
        });
    }
    
    //line button
    /**
    * Creates the line button with the icon and sets its action to the function
    */
    public static void createLineButton(){
        lineButton.setGraphic(createSaveButton("Images/line.png")); //set image
        lineButton.setOnAction(e ->  {                                 //set action
           editTwoPointLine();
        });
    }
    
    //rectangle button
    /**
    * Creates the rectangle button with the icon and sets its action to the function
    */
    public static void createRectangleButton(){
        rectangleButton.setGraphic(createSaveButton("Images/rectangle.png")); //set image
        rectangleButton.setOnAction(e ->  {                                 //set action
           drawRectangle();
        });
    }
    
    //square button
    /**
    * Creates the square button with the icon and sets its action to the function
    */
    public static void createSquareButton(){
        squareButton.setGraphic(createSaveButton("Images/square.png")); //set image
        squareButton.setOnAction(e ->  {                                 //set action
           drawSquare();
        });
    }
    
    //triangle button
    /**
    * Creates the triangle button with the icon and sets its action to the function
    */
    public static void createTriangleButton(){
        triangleButton.setGraphic(createSaveButton("Images/triangle.png")); //set image
        triangleButton.setOnAction(e ->  {                                 //set action
           drawTriangle();
        });
    }
    
    //circle button
    /**
    * Creates the circle button with the icon and sets its action to the function
    */
    public static void createCircleButton(){
        circleButton.setGraphic(createSaveButton("Images/circle.png")); //set image
        circleButton.setOnAction(e ->  {                                 //set action
           drawCircle();
        });
    }
    
    //Cut and Move button
    /**
    * Creates the cut and move button with the icon and sets its action to the function
    */
    public static void createCutMoveButton(){
        cutMoveButton.setGraphic(createSaveButton("Images/cut.png")); //set image
        cutMoveButton.setOnAction(e ->  {                             //set action
           cutMove();
        });
    }
}
