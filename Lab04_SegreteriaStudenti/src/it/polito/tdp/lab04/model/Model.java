package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	
	private StudenteDAO studenteDAO;
	private CorsoDAO corsoDAO;
	
	public Model() {
		studenteDAO = new StudenteDAO();
		corsoDAO = new CorsoDAO();
	}

	public List<Corso> getTuttiICorsi() {
		return corsoDAO.getTuttiICorsi();
	}
	
	public List<Studente> getTuttiGliStudenti() {
		return studenteDAO.getTuttiGliStudenti();
	}
	
	public Studente getStudenteFromMatricola(String matricola) {
		return studenteDAO.getStudenteFromMatricola(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public Corso getCorso(String codins) {
		return corsoDAO.getCorso(codins);
	}
	
	public List<Corso> cercaCorsi(String matricola){
		return studenteDAO.cercaCorsi(matricola);
	}
}
