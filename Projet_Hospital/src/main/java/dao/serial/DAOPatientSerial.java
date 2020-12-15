package dao.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import dao.IDAOPatient;
import model.Patient;

public class DAOPatientSerial implements IDAOPatient {
	static File f = new File("Patients.txt");
	
	@Override
	public void ajouter(Patient p) {
		List<Patient> sauvegarde=selectAll();
		try (
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				){
		
			sauvegarde.add(p);
			oos.writeObject(sauvegarde);

		}
		catch(Exception exception ) 
		{
			exception.printStackTrace();
		}
	}

	@Override
	public void modifier(Patient d) {
		List<Patient> sauvegarde=selectAll();
		try (
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				)
		{
			
			for(int i=0;i<sauvegarde.size();i++)
			{
				if (sauvegarde.get(i).getN_patient()==d.getN_patient())
				{
					sauvegarde.remove(i);
				}
			}
			sauvegarde.add(d);
			oos.writeObject(sauvegarde);

		}
		catch(Exception exception ) 
		{
			exception.printStackTrace();
		}
	}

	@Override
	public void supprimer(Integer id) {
		List<Patient> sauvegarde=selectAll();
		try (
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				)
		{
			
			for(int i=0;i<sauvegarde.size();i++)
			{
				if (sauvegarde.get(i).getN_patient()==id)
				{
					sauvegarde.remove(i);
				}
			}
			oos.writeObject(sauvegarde);

		}
		catch(Exception exception ) 
		{
			exception.printStackTrace();
		}
	}

	@Override
	public Patient selectById(Integer id) {
		try 
		{
			List<Patient> sauvegarde = selectAll();
			for(Patient d : sauvegarde) 
			{
				if(d.getN_patient()==id) 
				{
					return d;
				}
			}
		}
		catch(Exception exception){exception.printStackTrace();}
		return null;
	}

	@Override
	public List<Patient> selectAll() {
		List<Patient> sauvegarde=new ArrayList();

		try (
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				){


			sauvegarde=(List<Patient>) ois.readObject();

		}
		catch(Exception e ) 
		{
			e.printStackTrace();
		}
		return sauvegarde;
	}

	public void supprimerAll() {
		List<Patient> sauvegarde=selectAll();
		try (
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				)
		{
			
			for(int i=0;i<sauvegarde.size();i++)
			{
				
					sauvegarde.remove(i);
				
			}
			oos.writeObject(sauvegarde);

		}
		catch(Exception exception ) 
		{
			exception.printStackTrace();
		}		
	}
	
	public void deleteFile() {
        f.delete();

    }


}
