package slot;

import java.util.Optional;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLController{
	
	
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
	@FXML private ImageView go;
	@FXML private ImageView plus;
	@FXML private ImageView minus;
	@FXML private ImageView icon1;
	@FXML private ImageView iconx1;
	@FXML private ImageView icon2;
	@FXML private ImageView iconx2;
	@FXML private ImageView icon3;
	@FXML private ImageView iconx3;
	@FXML private ImageView bannerU ;
	@FXML private ImageView bannerU2;
	@FXML private ImageView bannerO;
	@FXML private MediaView mediaView = new MediaView();
	@FXML private MediaView mediaView2 = new MediaView();
	@FXML private MediaView mediaView3 = new MediaView();
	@FXML private MediaView mediaView4 = new MediaView();
	@FXML private MediaView mediaView5 = new MediaView();
	
	private FadeTransition fade = new FadeTransition();
	private FadeTransition fade2 = new FadeTransition();
	private FadeTransition fade3 = new FadeTransition();
	private FadeTransition fade4 = new FadeTransition();
	private FadeTransition fade5 = new FadeTransition();
	private FadeTransition fade6 = new FadeTransition();
	private TranslateTransition translate1, translate2, translate3;
	private RotateTransition rotate = new RotateTransition();
	
	private Media media,media2,media3,media4,media5;
	private MediaPlayer player,player2,mplayer,wplayer,wplayer2;
	public Stage meineStage;
	
	private double einsatz=5.0;
	private double gesgewi=0.0;
	private double gewinnFaktor=1.0;
	private double guthaben=250;
	private int flagRandom;
	private ActionEvent event;
	private Timeline timer, timer2; 
	public Image[] icons = {new Image(getClass().getResource("/icons/1.png").toExternalForm()),new Image(getClass().getResource("/icons/2.png").toExternalForm()),new Image(getClass().getResource("/icons/3.png").toExternalForm()),new Image(getClass().getResource("/icons/4.png").toExternalForm()),new Image(getClass().getResource("/icons/5.png").toExternalForm()),new Image(getClass().getResource("/icons/6.png").toExternalForm()),new Image(getClass().getResource("/icons/7.png").toExternalForm()),new Image(getClass().getResource("/icons/8.png").toExternalForm()),new Image(getClass().getResource("/icons/11.png").toExternalForm()),new Image(getClass().getResource("/icons/12.png").toExternalForm())};
	
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
			media = new Media(getClass().getResource("/sound/coin.wav").toExternalForm());
			player = new MediaPlayer(media);
			mediaView.setMediaPlayer(player);
			player.setVolume(75);
			
			media2 = new Media(getClass().getResource("/sound/mariocoin.mp3").toExternalForm());
			player2 = new MediaPlayer(media2);
			mediaView2.setMediaPlayer(player2);
			player2.setVolume(75);
			player2.play();
			
			media3 = new Media(getClass().getResource("/sound/wheel.mp3").toExternalForm());
			mplayer = new MediaPlayer(media3);
			mediaView3.setMediaPlayer(mplayer);
			mplayer.setVolume(75);
			
			media4 = new Media(getClass().getResource("/sound/win.wav").toExternalForm());
			wplayer = new MediaPlayer(media4);
			mediaView4.setMediaPlayer(wplayer);
			wplayer.setVolume(75);
			
			media5 = new Media(getClass().getResource("/sound/bwin.wav").toExternalForm());
			wplayer2 = new MediaPlayer(media5);
			mediaView5.setMediaPlayer(wplayer2);
			wplayer2.setVolume(75);
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	@FXML public void bannerAnimation() {
	
		rotateButton();
		
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
	
		fade3.setNode(bannerU2);
		fade3.setByValue(0.1);
		
		
		fade4.setNode(go);
		fade4.setDuration(Duration.millis(1));
		fade4.setCycleCount(1);
		fade4.setInterpolator(Interpolator.LINEAR);
		fade4.setFromValue(1);
		fade4.setToValue(0.75);
		fade4.play();
		
		fade.play();
		fade2.play();
		fade3.play();
		fade4.play();
	}
	
	//Startet den Banditen
	@FXML private void play(ActionEvent event) {
	
		player.stop();
		liquide();
		 if(guthaben<einsatz && guthaben >0) {
				einsatz=guthaben;
				ein.setText(Double.toString(einsatz));	
		 }
		
		 if(einsatz<=guthaben) {
			flagRandom=0;
			bannerO.setOpacity(0.1);
			this.gut.setText(Double.toString(this.guthaben=guthaben-einsatz)+ "€");
			player.play();
			wplayer.stop();
			wplayer2.stop();
			random();
			random2();
			rotateButtonGo();
			go.setDisable(true);
			mplayer.play();
		}
	}
	
	@FXML private void rotateButton() {
		rotate.setNode(go);
		rotate.setDuration(Duration.millis(500));
		rotate.setCycleCount(4);
		// 
		rotate.setInterpolator(Interpolator.LINEAR);
		//Winkelbestimmung
		rotate.setByAngle(360);
		//Achsenbestimmung
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.play();
	}
	@FXML private void rotateButtonGo() {
		rotate.setNode(go);
		rotate.setDuration(Duration.millis(400));
		rotate.setCycleCount(4);
		// 
		rotate.setInterpolator(Interpolator.LINEAR);
		//Winkelbestimmung
		rotate.setByAngle(360);
		//Achsenbestimmung
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.play();
	}
	
	//Erhöht den Einsatz wie auch den Gewinnfaktor
	@FXML private void plus(MouseEvent event) {
		if(einsatz<50) {
			this.ein.setText(Double.toString(this.einsatz=einsatz+5)+ "€");
			gewinnFaktor=gewinnFaktor+0.25;
			faktor.setText("Faktor="+gewinnFaktor);
		}
		else 
			return;
	}
	//Reduziert den Einsatz wie auch den Gewinnfaktor
	@FXML private void minus(MouseEvent event) {
		if(einsatz>5) {
			this.ein.setText(Double.toString(this.einsatz=einsatz-5)+ "€");
			gewinnFaktor=gewinnFaktor-0.25;
			faktor.setText("Faktor="+gewinnFaktor);
		}
		else 
			return;
	}
	
	@FXML private void mouseEvent(MouseEvent event) {
		play(this.event);
	}
	
	@FXML private void mouseEventOn(MouseEvent event) {
		fade3.setNode(go);
		fade3.setDuration(Duration.millis(1));
		fade3.setCycleCount(1);
		fade3.setInterpolator(Interpolator.LINEAR);
		fade3.setFromValue(0.75);
		fade3.setToValue(1);
		fade3.play();
		
		fade6.setNode(go);
		fade6.setDuration(Duration.millis(1));
		fade6.setCycleCount(1);
		fade6.setInterpolator(Interpolator.LINEAR);
		fade6.setFromValue(0.75);
		fade6.setToValue(1);
		fade6.play();
	}
	
	@FXML private void mouseEventOff(MouseEvent event) {
		fade3.setNode(go);
		fade3.setDuration(Duration.millis(1));
		fade3.setCycleCount(1);
		fade3.setInterpolator(Interpolator.LINEAR);
		fade3.setFromValue(1);
		fade3.setToValue(0.75);
		fade3.play();
	}
	
	@FXML private void mouseEventOnPlus(MouseEvent event) {
		fade5.setNode(plus);
		fade5.setDuration(Duration.millis(1));
		fade5.setCycleCount(1);
		fade5.setInterpolator(Interpolator.LINEAR);
		fade5.setFromValue(0.75);
		fade5.setToValue(1);
		fade5.play();
	}
	
	@FXML private void mouseEventOffPlus(MouseEvent event) {
		fade5.setNode(plus);
		fade5.setDuration(Duration.millis(1));
		fade5.setCycleCount(1);
		fade5.setInterpolator(Interpolator.LINEAR);
		fade5.setFromValue(1);
		fade5.setToValue(0.75);
		fade5.play();
	}
	
	@FXML private void mouseEventPlus(MouseEvent event) {
		plus(event);
	}
	
	@FXML private void mouseEventOnMinus(MouseEvent event) {
		fade6.setNode(minus);
		fade6.setDuration(Duration.millis(1));
		fade6.setCycleCount(1);
		fade6.setInterpolator(Interpolator.LINEAR);
		fade6.setFromValue(0.75);
		fade6.setToValue(1);
		fade6.play();
	}
	
	@FXML private void mouseEventOffMinus(MouseEvent event) {
		fade6.setNode(minus);
		fade6.setDuration(Duration.millis(1));
		fade6.setCycleCount(1);
		fade6.setInterpolator(Interpolator.LINEAR);
		fade6.setFromValue(1);
		fade6.setToValue(0.75);
		fade6.play();
	}
	
	@FXML private void mouseEventMinus(MouseEvent event) {
		minus(event);
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
			translate1.setDuration(Duration.millis(120));
			//translate1.setCycleCount(TranslateTransition.INDEFINITE);
			translate1.setCycleCount(1);
			
			translate2.setByY(460);//Imagesize Y = 460
			translate2.setDuration(Duration.millis(120));
			//translate2.setCycleCount(TranslateTransition.INDEFINITE);
			translate2.setCycleCount(1);

			translate3.setByY(460);//Imagesize Y = 460
			translate3.setDuration(Duration.millis(120));
			//translate3.setCycleCount(TranslateTransition.INDEFINITE);
			translate3.setCycleCount(1);

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
			translate1.setDuration(Duration.millis(120));
			//translate1.setCycleCount(TranslateTransition.INDEFINITE);
			translate1.setCycleCount(1);
			
			translate2.setFromY(-460); 
			translate2.setToY(0); //Imagesize Y = 460
			translate2.setDuration(Duration.millis(120));
			//translate2.setCycleCount(TranslateTransition.INDEFINITE);
			translate2.setCycleCount(1);

			translate3.setFromY(-460); 
			translate3.setToY(0); //Imagesize Y = 460
			translate3.setDuration(Duration.millis(120));
			//translate3.setCycleCount(TranslateTransition.INDEFINITE);
			translate3.setCycleCount(1);

			player.play();
			
			translate1.play();
			translate2.play();
			translate3.play();
			
			slot1.setGraphic(icon1);
		    slot2.setGraphic(icon2);
			slot3.setGraphic(icon3);
			
			
			timer = new Timeline(new KeyFrame(Duration.millis(120), event -> random()));
			timer2 = new Timeline(new KeyFrame(Duration.millis(120), event -> random2()));
			flagRandom++;
			
			if(flagRandom<=12) {
			timer.play();
			timer2.play();
			}
			if(flagRandom==13) {
			mplayer.stop();
			compare();
			go.setDisable(false);
			}
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
	}
		
		private void paarEinsZwei() {
			FadeTransition fade = new FadeTransition();
			FadeTransition fade2 = new FadeTransition();
			
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
			
			wplayer.play();
		}
		
		private void paarZweiDrei() {
			FadeTransition fade = new FadeTransition();
			FadeTransition fade2 = new FadeTransition();
			
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
			
			wplayer.play();
		}
		
		private void paarEinsDrei() {
			FadeTransition fade = new FadeTransition();
			FadeTransition fade2 = new FadeTransition();
			
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
			
			wplayer.play();
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
			
			wplayer2.play();
		}
		
	//Überprüft auf vorhandenes Guthaben
	//und beendet es wenn nicht liquide 
	private void liquide() {
		if(guthaben == 0) {
			//den Dialog erzeugen und anzeigen
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Information an den Spieler");
			alert.setHeaderText("Ihr eingezahltes Guthaben ist aufgebrauch !");
			alert.setContentText("Möchten Sie noch einmal 250;-€ einzahlen ?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					guthaben = 250;
				} else 
					System.exit(0);
		}
		
	}
}
