package dao;

import java.util.List;

import model.Visite;

public interface IDAOVisite extends IDAO<Visite,Integer>{

	public List<Visite> selectByIdPatient(Integer n_patient);

}
