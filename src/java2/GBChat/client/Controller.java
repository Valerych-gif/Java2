package java2.GBChat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private boolean isDark = false;
    private String darkStyle = getClass().getResource("stylesDark.css").toExternalForm();
    private String lightStyle = getClass().getResource("stylesLight.css").toExternalForm();

    @FXML
    public VBox mainWindow;

    @FXML
    public MenuBar menuBar;

    @FXML
    TextArea mainChatFrame;

    @FXML
    TextField messageField, loginField;

    @FXML
    HBox authPanel;

    @FXML
    PasswordField passField;

    @FXML
    ListView<String> clientsList;

    private Network network;

    private boolean authenticated;
    private String nickname;

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        clientsList.setVisible(authenticated);
        clientsList.setManaged(authenticated);
        if (!authenticated) {
            nickname = "";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAuthenticated(false);
        clientsList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String nickname = clientsList.getSelectionModel().getSelectedItem();
                if (nickname!=null) {
                    messageField.setText("/w " + nickname + " ");
                    messageField.requestFocus();
                    messageField.selectEnd();
                }
            }
        });
        linkCallbacks();
    }

    public void sendAuth() {
        network.sendAuth(loginField.getText(), passField.getText());
        loginField.clear();
        passField.clear();
    }

    public void sendMsg() {
        if (network.sendMsg(messageField.getText())) {
            messageField.clear();
            messageField.requestFocus();
        }
    }

    public void showAlert(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
            alert.showAndWait();
        });
    }

    public void linkCallbacks() {
        network = new Network();
        network.setCallOnException(args -> showAlert(args[0].toString()));

        network.setCallOnCloseConnection(args -> setAuthenticated(false));

        network.setCallOnAuthenticated(args -> {
            setAuthenticated(true);
            nickname = args[0].toString();
        });

        network.setCallOnMsgReceived(args -> {
            String msg = args[0].toString();
            if (msg.startsWith("/")) {
                if (msg.startsWith("/clients ")) {
                    String[] tokens = msg.split("\\s");
                    Platform.runLater(() -> {
                        clientsList.getItems().clear();
                        for (int i = 1; i < tokens.length; i++) {
                            clientsList.getItems().add(tokens[i]);
                        }
                    });
                }
            } else {
                mainChatFrame.appendText(msg + "\n");
            }
        });
    }

    public void close(ActionEvent actionEvent) {
        network.sendMsg("/end");
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
