/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tombola;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author acer
 */
public class Tombola extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Croupier croupier = new Croupier();
	List<Player> players = new ArrayList();
	
	GridPane tabella = new GridPane();
	List<GridPane> cartelleGiocatori = new ArrayList();
	for (int i = 0, k = 0; i < 10; i++) {
	    for (int j = 0; j < 9; j++) {
		tabella.add(new Label(croupier.tabellone.get(k++).toString()),j,i);
		tabella.setHgap(20);
		tabella.setVgap(20);
	    }
	}
	
	players.add(new Player(2));
	players.add(new Player(3));
	
        VBox root = new VBox();
	
	for (Player player : players) {
	    HBox cartelleGiocatore = new HBox();
	    for(List<Integer> cart : player.cartelle) {
		
		GridPane cartella = new GridPane();
		
		for (int i = 0, k = 0; i < 3; i++) {
		    for (int j = 0; j < 5; j++, k++) {
			Label num = new Label(cart.get(k).toString());
			cartella.add(num, j, i);
			cartella.setVgap(20);
			cartella.setHgap(20);
		    }
		}
		
		cartelleGiocatori.add(cartella);
		cartelleGiocatore.getChildren().add(cartella);
		cartelleGiocatore.setMinSize(10, 10);
	    }
	    root.getChildren().add(cartelleGiocatore);
	}
	
	Button btnExtract = new Button("Estrai");
	
	Label numeroEstratto = new Label();
	btnExtract.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	    public void handle(MouseEvent event) {
		croupier.extract();
		numeroEstratto.setText(croupier.ext_n);
		for (Node n : tabella.getChildren()) {
		    Label _l = (Label)n;
		    if (_l != null && _l.getText().compareTo(croupier.ext_n) == 0) {
			_l.setText("0");
			_l.setTextFill(Color.web("#ff0000"));
		    }
		}
		
		for (GridPane cartella : cartelleGiocatori) {
		    for (Player player : players) {
			
			for (List<Integer> cart : player.cartelle) {
			    int k = player.cerca(cart, Integer.parseInt(croupier.ext_n));
			    if (k > -1) {
				
				player.copri(Integer.parseInt(croupier.ext_n));
				for (Node n : cartella.getChildren()) {
				    Label _l = (Label)n;
				    if (_l != null && _l.getText().compareTo(croupier.ext_n) == 0) {
					_l.setText("0");
					_l.setTextFill(Color.web("#ff0000"));
				    }
				}
				
			    }
			}
			
		    }
		}
		
		for (Player player : players) {
		    if(player.hoVinto()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Fine");
			alert.setHeaderText("Un giocatore ha vinto, fine della partita.");
			alert.setContentText("Premere 'Ok' per termianre la partita");
			alert.showAndWait();
			System.exit(0);
		    }
		}
	    }
	});
	
	
	VBox tabellaCroupier = new VBox();
	tabellaCroupier.getChildren().addAll(tabella,btnExtract,numeroEstratto);
	
	
	root.getChildren().add(tabellaCroupier);
	
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Tombola");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
