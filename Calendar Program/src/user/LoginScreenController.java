package user;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import client.ServerCodes;
import client.TCPClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import json.JsonArray;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;

public class LoginScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	Preferences prefs = Preferences.userNodeForPackage(getClass());
		
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private CheckBox rememberMeCheckBox;
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	@FXML
	private Button forgotButton;
	
	TCPClient client;
	
	@FXML
	private void handleLoginButtonAction (ActionEvent event) throws UnknownHostException, IOException {
		String username =  usernameField.getText();
		String password = passwordField.getText();
		
		client = new TCPClient();
		String serverReply = client.customQuery(ServerCodes.Login, "'" + username + "', '" +  password + "'");
		String[] answer= serverReply.split("#");
		
		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		int  brukerID = jsonArray.get(0).asObject().get( "brukerID" ).asInt();
		String brukernavn = jsonArray.get(0).asObject().get( "brukernavn" ).asString();
		String passord = jsonArray.get(0).asObject().get( "passord" ).asString();
		String fornavn = jsonArray.get(0).asObject().get( "fornavn" ).asString();
		String etternavn = jsonArray.get(0).asObject().get( "etternavn" ).asString();
		String epost = jsonArray.get(0).asObject().get( "epost" ).asString();
	
		if (username.contains(brukernavn) && password.contains(passord)) {
			if (rememberMeCheckBox.isSelected()) {
				prefs.put("username", usernameField.getText());
				//TODO husk aa fjerne linjen under naar vi skal levere inn
				prefs.put("password", passwordField.getText());
				prefs.putBoolean("checkBox", true);
			}
			else {
				prefs.put("username", "");
				//TODO husk aa fjerne linjen under naar vi skal levere inn
				prefs.put("password", "");
				prefs.putBoolean("checkBox", false);
			}
			System.out.println("Successful login!");
			
			mainController.user = new UserModel(brukerID, brukernavn, fornavn, etternavn, epost);
			
			mainController.setScreen(Main.mainPageID);
			
		}
		else {
			mainController.setScreen(Main.loginFailedID);
		}
	}
	
	@FXML
	private void handleRegisterButtonAction (ActionEvent event) {
		mainController.setScreen(Main.registerID);
	}
	
	@FXML
	private void handleForgotButtonAction (ActionEvent event) {
		mainController.setScreen(Main.forgotID);
	}

	@Override
	public void setScreenParent (ScreensController screenParent) {
		mainController = screenParent;
	}

	@Override
	public void initialize (URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				usernameField.requestFocus();
			}
		});
		if (prefs.getBoolean("checkBox", false) == true) {
			usernameField.setText(prefs.get("username", ""));
			//TODO husk aa fjerne linjen under naar vi skal levere inn
			passwordField.setText(prefs.get("password", ""));
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					//TODO husk aa endre til passwordField fra loginButton naar vi skal levere inn
					loginButton.requestFocus();
				}
			});
			rememberMeCheckBox.setSelected(true);
		}
		else {
			usernameField.requestFocus();
			rememberMeCheckBox.setSelected(false);
		}
	}	
}
