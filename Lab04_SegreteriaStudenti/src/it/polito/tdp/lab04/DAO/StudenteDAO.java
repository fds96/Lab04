package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String matricola = rs.getString("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				studenti.add(new Studente( matricola,  cognome,  nome,  CDS));
			}

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Corso> cercaCorsi(String matricola){
	
			final String sql = "SELECT c.codins, crediti, nome, pd " + 
					"from corso as c, iscrizione as i " + 
					"where c.codins=i.codins " + 
					"and matricola=?";

			List<Corso> corsi = new LinkedList<>();

			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, matricola);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					String codins = rs.getString("c.codins");
					int numeroCrediti = rs.getInt("crediti");
					String nome = rs.getString("nome");
					int periodoDidattico = rs.getInt("pd");

					System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

					// Crea un nuovo JAVA Bean Corso
					// Aggiungi il nuovo oggetto Corso alla lista corsi
					
					corsi.add(new Corso( codins,  numeroCrediti,  nome,  periodoDidattico));
				}

				return corsi;

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
	}
	
	
	public Studente getStudenteFromMatricola(String matricola) {
		for(Studente s : this.getTuttiGliStudenti())
			if(s.getMatricola().equals(matricola))
				return s;
		return null;
		
	}


}
