/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yacine
 */
public class UIController implements Initializable {
    
    @FXML
    private TextField text2qr;
    @FXML
    private ImageView image;
    @FXML
    private TextField qr2text;
    @FXML
    private JFXButton genrate;
    @FXML
    private JFXButton browse;
    @FXML
    private JFXButton read;
    @FXML
    private JFXComboBox QRtype;
    @FXML
    private JFXTextField path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double height = image.getFitHeight();
        double width = image.getFitWidth();
        QRtype.getItems().add("not available yet");
        read.addEventFilter(MouseEvent.MOUSE_CLICKED,(event)->
        {
            try {
                qr2text.setText(assets.readCodeQr(new File(path.getText())));
            } catch (Exception ex) {
                Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        browse.addEventFilter(MouseEvent.MOUSE_CLICKED,(event)->
        
        {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Code QR", "*.png"));
        
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        
        if( selectedFile != null )
        {
            path.setText(selectedFile.getPath());
        }
        });
        
        genrate.addEventFilter(MouseEvent.MOUSE_CLICKED,(event)->
        {
            try {
                assets.writeCodeQr(text2qr.getText(),250,"output.png");
                Image img =new Image (new FileInputStream(new File("output.png").getPath()));
               
                image.setImage(img);
                image.setFitHeight(height);
                image.setFitWidth(width);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
