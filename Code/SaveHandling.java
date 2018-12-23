/*
 * This file contains all of the functions that handle what save command,
 * if a save is needed, and the savebutton modes.
 */
package paint;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import static paint.FileMenuActions.file;
import static paint.FileMenuActions.fileSaveAction;
import static paint.FileMenuActions.fileSaveAsAction;
import static paint.WindowSetUp.saveButton;

/**
 * This class contains the functions determine if a save is needed and handle
 * the saved/ not saved states.
 */
public class SaveHandling {
    
    static Boolean isSaved=true;
    
    /**
    * Function that determines if user must save file when user tries to close
    * the program. Dialogue box opens and user can save file, not save, or cancel.
    * @return If user clicks the red close button, return true.
    */
    public static boolean smartSave(){
        if (isSaved==false){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //create the Alert box
            alert.setHeaderText("Save?");
            alert.setContentText("Would you like to save your work?");
                
            //Buttons
            ButtonType dontButton = new ButtonType("Don't Save"); 
            ButtonType saveButton = new ButtonType("Save"); 
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE); 

            alert.getButtonTypes().setAll(dontButton, saveButton, cancelButton); //add buttons
            
            Optional<ButtonType> result = alert.showAndWait(); //show the alertbox and wait for user action
                
            if(result.get() == dontButton){ //if dont button pressed=exit everything
                 System.exit(0);
            }
            else if((result.get() == saveButton)){ //if save button pressed
                if (file == null){ //if there is not a file assosiated with the project-save as
                    fileSaveAsAction();
                    System.exit(0);
                }else{ //if there is a file assosiated with the project-save
                    fileSaveAction();
                    System.exit(0);
                }
            }
            else if(result.get() == cancelButton){
                return true; //if Cancel/Close button pressed just close save prompt
            }
        }else{
            System.exit(0); //Exit
        }
     return false; //Otherwise return False
    }
    
    /**
    * Function that sets the state of the project as saved
    */
    public static void setToSavedState(){
        isSaved=true; //file has been saved
        saveButton.setStyle("-fx-background-color: #80b380; "); //Sets the save button to green for saved
        saveButton.setText(" "); //set the save button to just contain the icon because saved
    }
    
    /**
    * Function that sets the state of the project as not saved
    */
    public static void setToNotSavedState(){
        isSaved=false; //needs to be saved again
        saveButton.setStyle("-fx-background-color: #ff6666; "); //Sets the save button to red for not saved
        saveButton.setText("*"); //set the save button to just say * because Not saved
    }
}
