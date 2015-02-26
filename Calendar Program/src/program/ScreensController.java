package program;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScreensController extends StackPane {
	
	
	private HashMap<String, Node> screens = new HashMap<String, Node>();
	
	
	public ScreensController() {
		super();
	}
	
	
	private void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}
	
	
	private Node getScreen(String name) {
		return screens.get(name);
	}
	
	
	//Laster inn FXML vinduet og har det klart i screens HashMappet
	public void loadScreen(String name, String resource) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
			Parent screen = (Parent) loader.load();
			ControllerInterface controller = ((ControllerInterface) loader.getController());
			controller.setScreenParent(this);
			addScreen(name, screen);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ScreensController.loadScreen failed!");
		}
		
	}
	
	public void unloadScreen(String name) {
		if (getScreen(name) != null) {
			screens.remove(name);			
		}
		else {
			System.out.println("ScreensController.unloadScreen failed!");
		}
	}
	
	
	//Setter viewet til vinduet name, som eksisterer i screens HashMappet
	public void setScreen(String name) {
		if (getScreen(name) != null) {
			if (!getChildren().isEmpty()) {
				getChildren().remove(0);
				getChildren().add(0, getScreen(name));
			}
			else {
				getChildren().add(getScreen(name));
			}
		}
		else {
			System.out.println("ScreensController.setScreen failed!");
		}
	}
}
