package slot;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLController {
private Stage meineStage;
	
	@FXML private Label slot1;
	@FXML private Label slot2;
	@FXML private Label slot3;
	@FXML private Label slotx1;
	@FXML private Label slotx2;
	@FXML private Label slotx3;
	@FXML private Label faktor;

	@FXML private TextField gut;
	@FXML private TextField ein;
	@FXML private TextField gew;
	@FXML private TextField gesgew;
	@FXML private Button go;
	@FXML private Button plus;
	@FXML private Button minus;
	@FXML private ImageView icon1;
	@FXML private ImageView iconx1;
	@FXML private ImageView icon2;
	@FXML private ImageView iconx2;
	@FXML private ImageView icon3;
	@FXML private ImageView iconx3;
	@FXML private ImageView bannerU = new ImageView(new Image("icons/u.png"));;
	@FXML private ImageView bannerO = new ImageView(new Image("icons/o.png"));;
	@FXML private FadeTransition fade = new FadeTransition();
	@FXML private FadeTransition fade2 = new FadeTransition();
	@FXML private TranslateTransition translate1, translate2, translate3;
	private File audio = new File("src/sound/coin.wav");
	private File audio2 = new File("src/sound/mariocoin.mp3");
	private Media media,media2;
	private MediaPlayer player,player2;
	@FXML private MediaView mediaView = new MediaView();
	@FXML private MediaView mediaView2 = new MediaView();
	
	private double einsatz=5.0;
	private double gesgewi=0.0;
	private double gewinnFaktor=1.0;
	private double guthaben=250;
	private static Image[] icons = {new Image("icons/1.png"),new Image("icons/2.png"), new Image("icons/3.png"), new Image("icons/4.png"),new Image("icons/5.png"),new Image("icons/6.png"), new Image("icons/7.png"), new Image("icons/8.png"), new Image("icons/11.png"),new Image("icons/12.png")};

	//Übergibt die Stage 
	public void setMeineStage(Stage meineStage) {
			this.meineStage = meineStage;
	}	
	
	@FXML public void start() {
		bannerAnimation();
		audioAnimation();
	}
	
	@FXML public void audioAnimation() {
		
		try {	
			media = new Media(audio.toURI().toString());
			player = new MediaPlayer(media);
			mediaView.setMediaPlayer(player);
			player.setVolume(75);
			
			media2 = new Media(audio2.toURI().toString());
			player2 = new MediaPlayer(media2);
			mediaView2.setMediaPlayer(player2);
			player2.setVolume(75);
			player2.play();
		}
		catch(Exception ex) {
			//den Dialog erzeugen und anzeigen
			Alert meinDialog = new Alert(AlertType.INFORMATION, "Beim Laden hat es ein Problem gegeben.\n" + ex.getMessage());
			//den Text setzen
			meinDialog.setHeaderText("Bitte beachten");
			meinDialog.initOwner(meineStage);
			//den Dialog anzeigen
			meinDialog.showAndWait();
		}
	}
	
	@FXML public void bannerAnimation() {
	
		fade.setNode(bannerO);
		fade.setDuration(Duration.millis(500));
		fade.setCycleCount(4);
		fade.setInterpolator(Interpolator.LINEAR);
		fade.setFromValue(1);
		fade.setToValue(0.4);
		fade.setAutoReverse(true);
		
		
		fade2.setNode(bannerU);
		fade2.setDuration(Duration.millis(500));
		fade2.setCycleCount(3);
		fade2.setInterpolator(Interpolator.LINEAR);
		fade2.setFromValue(1);
		fade2.setToValue(0);
		fade2.setAutoReverse(true);
	
		fade.play();
		fade2.play();
	}
	
	//Startet den Banditen
	@FXML private void play(ActionEvent event) {
		player.stop();
		if(guthaben>=einsatz) {
		bannerO.setOpacity(0.1);
		this.gut.setText(Double.toString(this.guthaben=guthaben-einsatz)+ "€");
		go.setDisable(false);
		player.play();
		random();
		random2();
	}
		else
			ein.setText(gut.getText());
		
		}
	
	//Erhöht den Einsatz wie auch den Gewinnfaktor
	@FXML private void plus(ActionEvent event) {
		if(einsatz<50) {
			this.ein.setText(Double.toString(this.einsatz=einsatz+5)+ "€");
			gewinnFaktor=gewinnFaktor+0.25;
			faktor.setText("Faktor="+gewinnFaktor);
		}
		else 
			return;
	}
	//Reduziert den Einsatz wie auch den Gewinnfaktor
	@FXML private void minus(ActionEvent event) {
		if(einsatz>5) {
			this.ein.setText(Double.toString(this.einsatz=einsatz-5)+ "€");
			gewinnFaktor=gewinnFaktor-0.25;
			faktor.setText("Faktor="+gewinnFaktor);
		}
		else 
			return;
	}
	
	
		//Sorgt für zufällige Marvel Helden
		@FXML private void random() {
			TranslateTransition translate1 = new TranslateTransition();
			TranslateTransition translate2 = new TranslateTransition();
			TranslateTransition translate3 = new TranslateTransition();


			translate1.setNode(iconx1 = new ImageView(icons[(int) (Math.random() * icons.length)]));
			translate2.setNode(iconx2 = new ImageView(icons[(int) (Math.random() * icons.length)]));
			translate3.setNode(iconx3 = new ImageView(icons[(int) (Math.random() * icons.length)]));
			
			translate1.setByY(460);//Imagesize Y = 460
			translate1.setDuration(Duration.millis(80));
			//translate1.setCycleCount(TranslateTransition.INDEFINITE);
			translate1.setCycleCount(3);
			
			translate2.setByY(460);//Imagesize Y = 460
			translate2.setDuration(Duration.millis(90));
			//translate2.setCycleCount(TranslateTransition.INDEFINITE);
			translate2.setCycleCount(3);

			translate3.setByY(460);//Imagesize Y = 460
			translate3.setDuration(Duration.millis(100));
			//translate3.setCycleCount(TranslateTransition.INDEFINITE);
			translate3.setCycleCount(3);

			translate1.play();
			translate2.play();
			translate3.play();
		     
			slotx1.setGraphic(iconx1);
			slotx2.setGraphic(iconx2);
			slotx3.setGraphic(iconx3);
			
	}
		
		//Sorgt für zufällige Marvel Helden
		private void random2() {
			translate1 = new TranslateTransition();
			translate2 = new TranslateTransition();
			translate3 = new TranslateTransition();


			translate1.setNode(icon1 = new ImageView(icons[(int) (Math.random() * icons.length)]));
			translate2.setNode(icon2 = new ImageView(icons[(int) (Math.random() * icons.length)]));
			translate3.setNode(icon3 = new ImageView(icons[(int) (Math.random() * icons.length)]));
			
			translate1.setFromY(-460); 
			translate1.setToY(0); //Imagesize Y = 460
			translate1.setDuration(Duration.millis(80));
			//translate1.setCycleCount(TranslateTransition.INDEFINITE);
			translate1.setCycleCount(3);
			
			translate2.setFromY(-460); 
			translate2.setToY(0); //Imagesize Y = 460
			translate2.setDuration(Duration.millis(90));
			//translate2.setCycleCount(TranslateTransition.INDEFINITE);
			translate2.setCycleCount(3);

			translate3.setFromY(-460); 
			translate3.setToY(0); //Imagesize Y = 460
			translate3.setDuration(Duration.millis(100));
			//translate3.setCycleCount(TranslateTransition.INDEFINITE);
			translate3.setCycleCount(3);

			player.play();
			
			translate1.play();
			translate2.play();
			translate3.play();
			
			slot1.setGraphic(icon1);
		    slot2.setGraphic(icon2);
			slot3.setGraphic(icon3);
			     
			compare();
			
	}
		
		//Vergleicht die einzelnen Marvel Helden für Gewinnkalkulation
		private void compare() {
			String ic1 = icon1.getImage().toString();
			String ic2 = icon2.getImage().toString();
			String ic3 = icon3.getImage().toString();
			
		if (ic1.compareTo(ic2) == 0 && ic2.compareTo(ic3) == 0) {
			drilling();
			gut.setText(Double.toString(this.guthaben=this.guthaben+50*gewinnFaktor*2)+ "€");
			gew.setText(50*gewinnFaktor+"€");
			gesgewi=gesgewi+50*gewinnFaktor;
			gesgew.setText(gesgewi+"€");
			return;
		}
		else if (ic1.compareTo(ic2) == 0 || ic2.compareTo(ic3) == 0 || ic1.compareTo(ic3) == 0) {
			if(ic1.compareTo(ic2) == 0 )
				paarEinsZwei();
			if(ic2.compareTo(ic3) == 0)
				paarZweiDrei();
			if(ic1.compareTo(ic3) == 0)
				paarEinsDrei();
			gut.setText(Double.toString(this.guthaben=this.guthaben+20*gewinnFaktor)+ "€");
			gew.setText(20*gewinnFaktor+"€");
			gesgewi=gesgewi+20*gewinnFaktor;
			gesgew.setText(gesgewi+"€");
			return;
		}
		else {
			gew.setText("0€");
		}
		liquide();
	}
		
		private void paarEinsZwei() {
			fade.setNode(icon1);
			fade.setDuration(Duration.millis(500));
			fade.setCycleCount(4);
			fade.setInterpolator(Interpolator.LINEAR);
			fade.setFromValue(1);
			fade.setToValue(0.4);
			fade.setAutoReverse(true);
			
			
			fade2.setNode(icon2);
			fade2.setDuration(Duration.millis(500));
			fade2.setCycleCount(4);
			fade2.setInterpolator(Interpolator.LINEAR);
			fade2.setFromValue(1);
			fade2.setToValue(0.4);
			fade2.setAutoReverse(true);
		
			fade.play();
			fade2.play();
		}
		
		private void paarZweiDrei() {
			fade.setNode(icon2);
			fade.setDuration(Duration.millis(500));
			fade.setCycleCount(4);
			fade.setInterpolator(Interpolator.LINEAR);
			fade.setFromValue(1);
			fade.setToValue(0.4);
			fade.setAutoReverse(true);
			
			
			fade2.setNode(icon3);
			fade2.setDuration(Duration.millis(500));
			fade2.setCycleCount(4);
			fade2.setInterpolator(Interpolator.LINEAR);
			fade2.setFromValue(1);
			fade2.setToValue(0.4);
			fade2.setAutoReverse(true);
		
			fade.play();
			fade2.play();
		}
		
		private void paarEinsDrei() {
			fade.setNode(icon1);
			fade.setDuration(Duration.millis(500));
			fade.setCycleCount(4);
			fade.setInterpolator(Interpolator.LINEAR);
			fade.setFromValue(1);
			fade.setToValue(0.4);
			fade.setAutoReverse(true);
			
			
			fade2.setNode(icon3);
			fade2.setDuration(Duration.millis(500));
			fade2.setCycleCount(4);
			fade2.setInterpolator(Interpolator.LINEAR);
			fade2.setFromValue(1);
			fade2.setToValue(0.4);
			fade2.setAutoReverse(true);
		
			fade.play();
			fade2.play();
		}
	
		private void drilling() {
			FadeTransition fade = new FadeTransition();
			FadeTransition fade2 = new FadeTransition();
			FadeTransition fade3 = new FadeTransition();
			
			fade.setNode(icon1);
			fade.setDuration(Duration.millis(500));
			fade.setCycleCount(4);
			fade.setInterpolator(Interpolator.LINEAR);
			fade.setFromValue(1);
			fade.setToValue(0.4);
			fade.setAutoReverse(true);
			
			
			fade2.setNode(icon2);
			fade2.setDuration(Duration.millis(500));
			fade2.setCycleCount(4);
			fade2.setInterpolator(Interpolator.LINEAR);
			fade2.setFromValue(1);
			fade2.setToValue(0.4);
			fade2.setAutoReverse(true);
			
			
			fade3.setNode(icon3);
			fade3.setDuration(Duration.millis(500));
			fade3.setCycleCount(4);
			fade3.setInterpolator(Interpolator.LINEAR);
			fade3.setFromValue(1);
			fade3.setToValue(0.4);
			fade3.setAutoReverse(true);
		
			fade.play();
			fade2.play();
			fade3.play();
		}
		
	//Überprüft auf vorhandenes Guthaben
	//und beendet es wenn nicht liquide 
	private void liquide() {
		if(guthaben ==  0.0) {
			System.exit(0);
		}
		
	//Setzt die Bühne
	setMeineStage(meineStage);
	}

}
