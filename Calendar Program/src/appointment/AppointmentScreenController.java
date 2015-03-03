package appointment;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import user.TCPClient;
import appointment.Appointment;

import com.sun.org.apache.xml.internal.security.Init;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import json.JsonArray;
import json.JsonValue;

public class AppointmentScreenController implements Initializable{
	
	private Appointment model;
	
	@FXML
	private TextField txtPurpose;
	@FXML
	private TextField txtPlace;
	@FXML
	private DatePicker dpStart;
	@FXML
	private TextField timeStart;
	@FXML
	private TextField timeEnd;
	@FXML
	private ComboBox roomField;
	@FXML
	private ListView<String> invitedField;
	
	private String startTime;
	private String endTime;
	private LocalDate date;
	
	
	private boolean valid=true;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new Appointment();
		model.setStart("00:00");
		model.setEnd("23:59");
		
		try {
			addUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		timeStart.setText(model.getStart());
		timeEnd.setText(model.getEnd());
		createListeners();
	}
	
	private void createListeners() {
		timeStart.textProperty().addListener((observable, oldValue, newValue) -> {
			if (validate(timeStart.getText(), "(\\d){2}(:)(\\d){2}", timeStart, null, null) && isCorrectTimeSpan() 
					&& validTime() && validate(timeEnd.getText(), "(\\d){2}(:)(\\d){2}", timeEnd, null, null)) {
				startTime=timeStart.getText();
				valid = true;
			} else {
				valid = false;
			}
		});
		timeEnd.textProperty().addListener((observable, oldValue, newValue) -> {
			if (validate(timeEnd.getText(), "(\\d){2}(:)(\\d){2}", timeEnd, null, null) && isCorrectTimeSpan() 
					&& validTime() && validate(timeStart.getText(), "(\\d){2}(:)(\\d){2}", timeStart, null, null)) {
				endTime=timeEnd.getText();
				valid = true;
			} else {
				valid = false;
			}
		});
		dpStart.valueProperty().addListener(new ChangeListener <LocalDate>(){
			public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate t, LocalDate t1){
				LocalDate today = LocalDate.now();
				if (today.compareTo(t1) > 0){
					dpStart.setStyle("-fx-control-inner-background: #FBB;");
					valid = false;
				}
				else {
					dpStart.setStyle("-fx-control-inner-background: white;");
					date = dpStart.getValue();
					valid = true;
				}
			};
		});
	}
	
	private boolean validate(String value, String regex, TextField textField, DatePicker datePicker, LocalDate date) {
    	if(textField != null) {
    		if (!value.isEmpty()) {
    			Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        		Matcher m = p.matcher(value);
        		boolean doesMatch = m.matches();
        		String style = doesMatch ? "-fx-border-width: 0; -fx-background-color: WHITE" : "-fx-border-color: red; -fx-border-width: 2; "
        				+ "-fx-background-color: #ffbbbb";
        		textField.setStyle(style);
        		textField.setPromptText("Ugyldig input!");
        		return doesMatch;
    		} else {
    			textField.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
    		}
    	}
    	if (datePicker != null) {
    		if (date != null) {
    			LocalDate today = LocalDate.now();
    			String style = !date.isBefore(today) ? "-fx-border-width: 0; -fx-background-color: WHITE" : "-fx-border-color: red; -fx-border-width: 2; "
        				+ "-fx-background-color: #ffbbbb";
        		datePicker.setStyle(style);
        		return true;
    		} else {
        		datePicker.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb");
    		}
		}
    	return false;
    }
	
	@FXML
	private void doConfirm() {
		Appointment appointment = new Appointment();
		if (valid) {
			appointment.setPlace(txtPlace.getText());
			appointment.setPurpose(txtPurpose.getText());
			System.out.println("Riktig");
			System.out.println(model.getStart());
			System.out.println(model.getEnd());
		} else {
			System.out.println("feil");
		}
	}
	
	private boolean isCorrectTimeSpan() {
		if (Integer.parseInt(timeEnd.getText().replace(":", "")) > Integer.parseInt(timeStart.getText().replace(":", ""))) {
			return true;
		}
		timeStart.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
		timeEnd.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
		return false;
	}
	
	private boolean validTime() {
		if (!timeStart.getText().matches("[0-2][0-3]:[0-5][0-9]") && !timeEnd.getText().matches("[0-1][0-9]:[0-5][0-9]")) {
			timeStart.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
			timeEnd.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
			return false;
		} else {
			return true; 
		}
	}
	
	private void addUsers() throws IOException {
		TCPClient client = new TCPClient();
		String serverReply = client.customQuery("u4sl29fjanz680slla0p", "'None'");
		
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		/*String fornavn = "";
		String etternavn = "";
		String data = "";*/
		
		String[] data=null;
		
		for( JsonValue value : jsonArray ) {
			String fornavn = value.asObject().get( "fornavn" ).asString();
			String etternavn = value.asObject().get( "etternavn" ).asString();
			String temp = fornavn + " " + etternavn;
			data[0]+=temp;
		}
		
		ObservableList<String> items =FXCollections.observableArrayList (data);
		invitedField.setItems(items);
	}
	

}
