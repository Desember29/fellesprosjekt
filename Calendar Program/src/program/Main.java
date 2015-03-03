package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	//Husk aa adde alle grensesnitt (.fxml filer) under screens, og lag et navn for den under ID.
	public final static String loginID = "LoginScreen";
	private final static String loginScreen = "/user/LoginScreen.fxml";
	
	public final static String mainPageID = "MainPage";
	private final static String mainPageScreen = "/menu/MainPageScreen.fxml";
	
	public final static String loginFailedID = "LoginFailedScreen";
	private final static String loginFailedScreen = "/user/LoginFailedScreen.fxml";
	
	public final static String forgotID = "ForgotScreen";
	private final static String forgotScreen = "/user/ForgotScreen.fxml";
	
	public final static String appointmentID = "AppointmentScreen";
	private final static String appointmentScreen = "/appointment/AppointmentScreen.fxml";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ScreensController screensController = new ScreensController();
		
		//Husk aa laste inn grensesnitt med navn her.
		screensController.loadScreen(loginID, loginScreen);
		screensController.loadScreen(mainPageID, mainPageScreen);
		screensController.loadScreen(loginFailedID, loginFailedScreen);
		screensController.loadScreen(forgotID, forgotScreen);
		screensController.loadScreen(appointmentID, appointmentScreen);
		
		screensController.setScreen(loginID);
		
		Group root = new Group();
		root.getChildren().addAll(screensController);
		Scene scene = new Scene(root, 1024, 576);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
