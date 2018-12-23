/*
 * This Class Contains the menubar, its contents
 */
package paint;

import java.io.File;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import static paint.Paint.mainScreenLayout;
import static paint.SaveHandling.smartSave;
import static paint.ToolsMenuActions.cutMove;
import static paint.ToolsMenuActions.eraser;
import static paint.ToolsMenuActions.toolsPickColor;
import static paint.ToolsMenuActions.toolsText;

/**
* This class contains all of the code for the menu bar
*/
public class MenuBar  {
    
    /**
    * Creates the menu bar, adds all of the buttons, sets the actions, and 
    * keyboard shortcuts to them.
    */
    public static void MenuBar(){
    //********************************************************
    //***********Create the bar and position******************
    //********************************************************
    javafx.scene.control.MenuBar menuBar = new javafx.scene.control.MenuBar();
    mainScreenLayout.add(menuBar, 0, 0); 
    
    //*************************************************************************
    //***************File Section**********************************************
    //*************************************************************************
    // Create the buttons that will be under file and adds the key combination for each
    Menu fileMenu = new Menu("_File"); //alt-F will open menu
    MenuItem fileOpenMenuItem = new MenuItem("Open");
    fileOpenMenuItem.setMnemonicParsing(true);
    fileOpenMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
    
    MenuItem fileSaveAsMenuItem = new MenuItem("Save As");
    fileSaveAsMenuItem.setMnemonicParsing(true);
    fileSaveAsMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+S"));
    
    MenuItem fileSaveMenuItem = new MenuItem("Save");
    fileSaveMenuItem.setMnemonicParsing(true);
    fileSaveMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
    
    MenuItem fileExitMenuItem = new MenuItem("Exit");
    fileExitMenuItem.setMnemonicParsing(true);
    fileExitMenuItem.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
    
    
    //*****************Actions*********************
    //Exit
    fileExitMenuItem.setOnAction(actionEvent -> {
        System.out.println("File-Exit!");
        smartSave();
    });
        
    //Open
    fileOpenMenuItem.setOnAction(actionEven -> {
        System.out.println("File-Open!");
        FileMenuActions.fileOpenAction();
    });
        
    //Save as
    fileSaveAsMenuItem.setOnAction(actionEven -> {
        System.out.println("File-Save As!");
        FileMenuActions.fileSaveAsAction();
    });
    
    //Save 
    fileSaveMenuItem.setOnAction(actionEven -> {
        System.out.println("File-Save!");
        FileMenuActions.fileSaveAction();
    });
    
    //*************************************************************************
    //**************************Edit Section***********************************
    //*************************************************************************
    Menu editMenu = new Menu("_Edit"); //alt-E will open menu
    MenuItem EditUndoMenuItem = new MenuItem("Undo");
    EditUndoMenuItem.setMnemonicParsing(true);
    EditUndoMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
    MenuItem EditRedoMenuItem = new MenuItem("Redo");
    EditRedoMenuItem.setMnemonicParsing(true);
    EditRedoMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Y"));
    
    //*****************Actions*********************
    
    //Undo
    EditUndoMenuItem.setOnAction(actionEven -> {
        System.out.println("Edit-Undo!");
        EditMenuActions.Undo();
    });
    
    //Redo
    EditRedoMenuItem.setOnAction(actionEven -> {
        System.out.println("Edit-Redo!");
        EditMenuActions.Redo();
    });
    

    //*************************************************************************
    //***************Tools Section**********************************************
    //*************************************************************************
    // Create the buttons that will be under Tools and add key combination for each
    Menu toolMenu = new Menu("_Tools"); //alt-t will open menu
    
    MenuItem ToolsCutMoveMenuItem = new MenuItem("Cut and Move");
    ToolsCutMoveMenuItem.setMnemonicParsing(true);
    ToolsCutMoveMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
    
    MenuItem ToolsEraserMenuItem = new MenuItem("Eraser");
    ToolsEraserMenuItem.setMnemonicParsing(true);
    ToolsEraserMenuItem.setAccelerator(KeyCombination.keyCombination("Alt+E"));
    
    MenuItem ToolsTextItem = new MenuItem("Insert Text");
    ToolsTextItem.setMnemonicParsing(true);
    ToolsTextItem.setAccelerator(KeyCombination.keyCombination("Alt+T"));
    
    MenuItem ToolsPickColor = new MenuItem("Dropper Tool");
    ToolsPickColor.setMnemonicParsing(true);
    ToolsPickColor.setAccelerator(KeyCombination.keyCombination("Alt+P"));
    
        //*****************Actions*********************
    //Cut and Move
    ToolsCutMoveMenuItem.setOnAction(actionEven -> {
        System.out.println("Tools-Cut and Move!");
        cutMove();
    });
    
    //Cut and Move
    ToolsEraserMenuItem.setOnAction(actionEven -> {
        System.out.println("Tools-Eraser!");
        eraser(); 
    });
    
    //Insert Text
    ToolsTextItem.setOnAction(actionEven -> {
        System.out.println("Tools-Text!");
        toolsText();
    });
    
    //Pick Color
    ToolsPickColor.setOnAction(actionEven -> {
        System.out.println("Tools-Pick Color!");
        toolsPickColor();
    });
    

    //*************************************************************************
    //***************Draw Section**********************************************
    //*************************************************************************
    // Create the buttons that will be under Draw and add key combination for each
    Menu drawMenu = new Menu("_Draw"); //alt-D will open menu
    MenuItem DrawDrawLineMenuItem = new MenuItem("Draw Line");
    DrawDrawLineMenuItem.setMnemonicParsing(true);
    DrawDrawLineMenuItem.setAccelerator(KeyCombination.keyCombination("Alt+L"));
    
    MenuItem DrawFreeHandMenuItem = new MenuItem("Free Hand");
    DrawFreeHandMenuItem.setMnemonicParsing(true);
    DrawFreeHandMenuItem.setAccelerator(KeyCombination.keyCombination("Alt+H"));
    
    MenuItem DrawRectangleItem = new MenuItem("Rectangle");
    DrawRectangleItem.setMnemonicParsing(true);
    DrawRectangleItem.setAccelerator(KeyCombination.keyCombination("Alt+R"));
    
    MenuItem DrawCircleItem = new MenuItem("Cirlce");
    DrawCircleItem.setMnemonicParsing(true);
    DrawCircleItem.setAccelerator(KeyCombination.keyCombination("Alt+C"));
    
    MenuItem DrawSquareItem = new MenuItem("Square");
    DrawSquareItem.setMnemonicParsing(true);
    DrawSquareItem.setAccelerator(KeyCombination.keyCombination("Alt+S"));
    
    MenuItem DrawTriangleItem = new MenuItem("Triangle");
    DrawTriangleItem.setAccelerator(KeyCombination.keyCombination("Alt+T"));
    DrawTriangleItem.setMnemonicParsing(true);


    //*****************Actions*********************
    
    //Draw Line
    DrawDrawLineMenuItem.setOnAction(actionEven -> {
        System.out.println("Draw-Line!");
        DrawMenuActions.editTwoPointLine();
    });
    
    //Free Hand
    DrawFreeHandMenuItem.setOnAction(actionEven -> {
        System.out.println("Draw-Free Hand!");
        DrawMenuActions.editFreeHandAction();
    });
    
    //Draw Rectangle
    DrawRectangleItem.setOnAction(actionEven -> {
        System.out.println("Draw-Rectangle!");
        DrawMenuActions.drawRectangle();
    });
    
    //Draw Square
    DrawSquareItem.setOnAction(actionEven -> {
        System.out.println("Draw-Square!");
        DrawMenuActions.drawSquare();
    });
    
    //Draw Circle
    DrawCircleItem.setOnAction(actionEven -> {
        System.out.println("Draw-Circle!");
        DrawMenuActions.drawCircle();
    });
    
    //Draw Triangle
    DrawTriangleItem.setOnAction(actionEven -> {
        System.out.println("Draw-Circle!");
        DrawMenuActions.drawTriangle();
    });
    
    //*************************************************************************
    //****************Add all of the components to each Section****************
    //*************************************************************************
    fileMenu.getItems().addAll(fileOpenMenuItem, fileSaveMenuItem, fileSaveAsMenuItem, fileExitMenuItem); //Add Open, Save, Exit in File Section
    editMenu.getItems().addAll(EditUndoMenuItem, EditRedoMenuItem); // Undo, Redo
    toolMenu.getItems().addAll(ToolsCutMoveMenuItem, ToolsEraserMenuItem, ToolsTextItem, ToolsPickColor); // Cut/move, Eraser, Text, Colot picker
    drawMenu.getItems().addAll(DrawDrawLineMenuItem, DrawFreeHandMenuItem, DrawRectangleItem, DrawCircleItem, DrawSquareItem, DrawTriangleItem); //DrawLine, FreeHand....
    
    //*************************************************************************
    //***************Add the Section to Menu Bar*******************************
    //*************************************************************************
    menuBar.getMenus().addAll(fileMenu, editMenu, toolMenu, drawMenu); //Add the File, edit, tools, draw Section
    }
}