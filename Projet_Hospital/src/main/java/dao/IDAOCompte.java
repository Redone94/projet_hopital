package dao;

import java.util.List;

import com.sun.istack.Nullable;

import model.Secretaire;
import model.Medecin;
import model.Compte;

public interface IDAOCompte extends IDAO<Compte,Integer> {
	
	public List<Secretaire> SelectSecretaire();
	public List<Medecin> SelectMedecin();
	
	public Compte SelectByLoginMdp(String login, String mdp);
	@Nullable
	public Compte SelectByLogin(String login);

}
