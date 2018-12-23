/*
 * Kostadin Pendev 11/8/2018
 *Pain(t)-6.0
 */
package paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static paint.MenuBar.MenuBar;
import static paint.SaveHandling.smartSave;
import static paint.WindowSetUp.buttonSeparator;
import static paint.WindowSetUp.canvas;
import static paint.WindowSetUp.createCanvas;
import static paint.WindowSetUp.createCircleButton;
import static paint.WindowSetUp.createCutMoveButton;
import static paint.WindowSetUp.createDropperButton;
import static paint.WindowSetUp.createEraseButton;
import static paint.WindowSetUp.createFillButton;
import static paint.WindowSetUp.createFreeHandButton;
import static paint.WindowSetUp.createHBox;
import static paint.WindowSetUp.createLineButton;
import static paint.WindowSetUp.createRectangleButton;
import static paint.WindowSetUp.createRedoButton;
import static paint.WindowSetUp.createSaveButton;
import static paint.WindowSetUp.createSlider;
import static paint.WindowSetUp.createSquareButton;
import static paint.WindowSetUp.createTextButton;
import static paint.WindowSetUp.createTriangleButton;
import static paint.WindowSetUp.createUndoButton;
import static paint.WindowSetUp.hbox;
import static paint.WindowSetUp.setUpColorPicker;


/**
 * Extends the class Application (where we inherit
 * all of the JavaFX functionality)
 */

public class Paint extends Application {
    //*******Variables for sizes of things*********
    static int widthOfMainScreen=800;
    static int heightOfMainScreen=800;
    static int heightOfCanvas=heightOfMainScreen-55;
    static int widthOfCanvas=widthOfMainScreen;
    //******Variables used for graphics*******
    static GridPane mainScreenLayout;
    
    /**
     *
     * Overriding JavaFX default start
     */
    @Override
    public void start(Stage primaryStage) { 
        
        //************MainScreen Set-up********************
        // Starting size will be 800x800
        mainScreenLayout = new GridPane();
        Scene mainScreen = new Scene(mainScreenLayout, widthOfMainScreen, heightOfMainScreen);
        
        
        //*********Add the interface******************
        MenuBar(); // creates thge menu bar
        
        
        //*********************************************************************
        //*********Set Up Canvas and Colorpicker*******************************
        //*********************************************************************
        createCanvas();  //create the canvas
        setUpColorPicker();
        
        
        //*********************************************************************
        //*************************SCREEN LAYOUT*******************************
        //*********************************************************************
        ///HBOX
        createHBox();           //creates the Hbox
        
        //Slider
        createSlider();         //creates the slider
        
        //Buttons
        buttonSeparator();      //creates the separators between the buttons
        createSaveButton();     //creates the save button
        createEraseButton();    //creates the erase button
        createFillButton();     //creates the fill button
        createUndoButton();     //creates the undo button
        createRedoButton();     //creates the redo button
        createDropperButton();  //creates the dropper button
        createTextButton();     //creates the text button
        createFreeHandButton(); //creates the free hand draw button
        createLineButton();     //creates the draw two point line button
        createRectangleButton();//creates the rectangle button
        createSquareButton();   //creates the square button
        createTriangleButton(); //creates the triange button
        createCircleButton();   //creates the circle button
        createCutMoveButton();  //creates the Cut/move button
        
        
        //addComponents
        mainScreenLayout.add(hbox, 0, 1); //add hbox to screen
        mainScreenLayout.add(canvas, 0, 3); //add canvas to screen
        
       
        //**************************************************************
        //*******************Display Window*****************************
        //**************************************************************
        primaryStage.setTitle("Pain(t)"); //sets the title
        primaryStage.setScene(mainScreen); //identifys which scene we are using
        primaryStage.show(); //shows the scene

        
        
        //**************************************************************
        //***************TESTS ON CLOSE IF MUST SAVE********************
        //**************************************************************
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            if(smartSave()==true){ //if Cancel/Close button pressed just close save prompt
               event.consume(); 
            }
        });
    }

    public static void main(String[] args) {
        launch(args); //This calls the start method
    }
    
    public static Stage getStage(Stage primaryStage){
        return primaryStage;
    }
}
