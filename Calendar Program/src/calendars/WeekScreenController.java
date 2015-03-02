package calendars;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class WeekScreenController {
	//Uke
	
	////Ukenummer
	@FXML
	private Text weekNumber;
	
		//weekNumber m� settes etter hva som er riktig ukenummer.
	
	////Bytte uke
	@FXML
	private ImageView previousWeekButton, nextWeekButton;
	
	@FXML
	private void changeToPreviousWeek(ActionEvent event){
		//Hvordan kan vi f� byttet til forrige ukesoversikt?
	}
	
	@FXML
	private void changeToNextWeek(ActionEvent event){
		//Hvordan bytte til neste ukesoversikt?
	}
	
	
	//Mandag
	
	////Datofelt
	@FXML
	private Text mondayDate, mondayMonth;
	
		/*
		 * Dette gjelder for alle dagene:
		 * 
		 * Text mondayDate og Text mondayMonth m� settes ut etter hva som er riktig dato og m�ned for den gitte dagen. 
		 */
	
	////Avtalefelt
	
	@FXML
	private Label ingenAvtalerMonday;
	
		/* 
		 * Dette gjelder for alle dagene:
		 * 
		 * a) Dersom det ikke er satt opp noen m�ter p� en dag vil bare Label "Ingen avtaler" v�re visible.
		 * b) Dersom det er satt opp noen avtaler vil Label "Ingen avtaler" v�re satt til visible=False.
		 * 		1) Dersom det er satt opp 1 avtale vil avtaleformatet bestemme hva som skjer:
		 * 			x) Dersom avtalen er tildelt et rom m� GridPane (3 x 1) (0,0) settes til visible=True
		 * 			   og Label for tid, avtalenavn og rom i GridPane m� settes.
		 * 			y) Dersom avtalen ikke er tildelt et rom m� GridPane (2 x 1) (0,0) settes til visible=True
		 * 			   og Label for tid og avtalenavn i GridPane m� settes.
		 * 		2) Dersom det er satt opp 2 avtaler gj�res tilsvarende for den tidligste avtalen som beskrevet i 1).
		 * 		   Avtale nr. 2 vil ogs� gj�res p� tilsvarende m�te basert p� om det er lagt inn rom eller ikke,
		 * 		   men vi m� sette visible=True for GridPane (3 x 1) (0,1) eller GridPane (2 x 1) (0,1).
		 * 		3) Dersom det er satt opp 3 avtaler gj�res det samme som beskrevet i 1) og vi settes visible=True
		 * 		   p� enten GridPane (3 x 1) (0,2) eller GridPane (2 x 1) (0,2).
		 * 		4) Dersom det er satt opp mer enn 3 avtaler vil vi gj�re som beskrevet i 1) og 2) for de 2 f�rste avtalene,
		 * 		   mens vi i rad 3 vil sette visible=True for Label "+ ? andre avtaler" der vi vil sette ?=(antallAvtaler-2)
		 *    
		 */
	
	
	
	
	
	//Tirsdag
	
	////Datofelt
	@FXML
	private Text tuesdayDate, tuesdayMonth;
	
		//*
	
	////Avtalefelt
		
		//*
	
	
	
	
	
	
	//Onsdag
	
	////Datofelt
	@FXML
	private Text wednesdayDate, wednesdayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
	
	
	
	
	//Torsdag
	
	////Datofelt
	@FXML
	private Text thursdayDate, thursdayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
	
	
	
	
	//Fredag
	
	////Datofelt
	@FXML
	private Text fridayDate, fridayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
	
	
	
	//L�rdag
	
	////Datofelt
	@FXML
	private Text saturdayDate, saturdayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
	
	
	//S�ndag
	
	////Datofelt
	@FXML
	private Text sundayDate, sundayMonth;
	
		//*
	
	////Avtalefelt
	
		//*
	
	
}
