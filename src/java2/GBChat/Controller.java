package java2.GBChat;

import com.sun.javafx.css.Selector;
import com.sun.javafx.css.Style;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Date;
import java.util.Set;

public class Controller {
    public TextArea mainChatFrame;
    public TextField messageField;
    public MenuBar menu;
    public MenuItem menuFile;
    public VBox mainWindow;
    public MenuBar menuBar;

    private boolean isDark = false;
    private String darkStyle = getClass().getResource("stylesDark.css").toExternalForm();
    private String lightStyle = getClass().getResource("stylesLight.css").toExternalForm();

    public void send(ActionEvent actionEvent) {
        String message = messageField.getText();
        Date date = new Date();

        if (!message.equals("")){
            mainChatFrame.appendText(String.format("You [%s]: %s\n", date, message));
            messageField.clear();
        }

    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void setTheme(ActionEvent actionEvent) {
        if (!isDark){
            setDarkTheme();
            isDark=true;
        } else {
            setLightTheme();
            isDark=false;
        }
    }

    private void setDarkTheme (){
        mainWindow.getStylesheets().add(darkStyle);
        mainWindow.getStylesheets().remove(lightStyle);
        System.out.println("DarkTheme: ");
    }

    private void setLightTheme (){
        mainWindow.getStylesheets().add(lightStyle);
        mainWindow.getStylesheets().remove(darkStyle);
        System.out.println("LightTheme");
    }

    public void showAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About GBChat");
        alert.setHeaderText("GBChat - Simple chat by GeekBrains student");
        alert.setContentText("Author Bulekov Valery\nVersion 0.0.1");
        alert.show();
    }
}
