/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;
	private List<Corso> corsi;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="BoxCorsi"
    private ComboBox<String> BoxCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnV"
    private CheckBox btnV; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    public void setModel(Model model) {

		this.model = model;
		setComboItems();
	}

	private void setComboItems() {
		for(Corso c : model.getTuttiICorsi())
			BoxCorsi.getItems().add(c.getNome());
	}

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	if(model.getStudenteFromMatricola(txtMatricola.getText())==null)
    		txtResult.appendText("Per favore inserire matricola valida.\n");
    	else {
    		for(Corso c : model.cercaCorsi(txtMatricola.getText()))
    		txtResult.appendText(c.toString()+"\n");
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	Corso temp = model.getCorso(BoxCorsi.getValue());
    	if(temp==null)
    		txtResult.appendText("Selezionare un insegnamento.\n");
    	for(Studente s : model.getStudentiIscrittiAlCorso(temp))
    		txtResult.appendText(s.toString()+"\n");
    }

    @FXML
    void doCompleta(ActionEvent event) {
    	Studente temp=model.getStudenteFromMatricola(txtMatricola.getText());
    	if(temp==null) {
    		txtResult.appendText("Inserire una matricola valida.\n");
    	}
    	else {
    		txtNome.setText(temp.getNome());
    		txtCognome.setText(temp.getCognome());
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert BoxCorsi != null : "fx:id=\"BoxCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnV != null : "fx:id=\"btnV\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
    }
}

