package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Config.Context;
import model.Compte;
import model.Medecin;
import model.Patient;
import model.Secretaire;
import model.Visite;

public class App_hospital {
	private static Compte connected =null;
	//
	private static int pause=0;
	private static List<Patient> fileA= new ArrayList();
	
	private static List<Visite> visiteF= new ArrayList();
	
	public static int saisieInt(String msg) 
	{
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public static double saisieDouble(String msg) 
	{
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.nextDouble();
	}

	public static String saisieString(String msg) 
	{
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
//////////////////////////////////////////////////////////////////
	private static void menuPrincipal() {
		System.out.println("\nBienvenue dans votre hopital");
		System.out.println("1 - Connection");
		System.out.println("2 - Prendre rendez-vous");
		System.out.println("3 - Sortir");

		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:menuLogin();break;
		case 2:Rendez_vous();break;
		case 3:System.exit(0);break;
		default: menuPrincipal(); break;
		}

		menuPrincipal();
	}

	private static void menuLogin() {

		String login=saisieString("Saisir votre login");
		String password=saisieString("Saisir votre password");

		connected=Context.getInstance().getDaoCompte().SelectByLoginMdp(login, password); 

		if(connected==null) 
		{
			System.out.println("Mauvais identifiants");
			menuPrincipal();
		}
		else if(connected instanceof Medecin) 
		{
			menuMedecin(connected);
			
		}
		else if(connected instanceof Secretaire)
		{
			//fileA.clear();
			////
			if(pause==1) {
			for(Patient p : Context.getInstance().getDaoPatientSerial().selectAll()) 
			{
				fileA.add(p);
			}
			Context.getInstance().getDaoPatientSerial().deleteFile();
			
			}
			menuSecretaire();
			pause=0;

		}
	}

	private static void Rendez_vous() {
		System.out.println("Pour prendre un rendez-vous merci de remplire la fiche suivante: ");
		int n_patient= saisieInt("Entrez votre numéro de sécu");

		if (Context.getInstance().getDaoPatient().selectById(n_patient) == null) {
			String nom= saisieString("Votre nom:");
			String prenom= saisieString("Votre prenom:");
			Integer num_voie= saisieInt("Numero de voie");
			String voie= saisieString("Nom de la voie");
			String code_postal= saisieString("Code postal");
			String ville= saisieString("Ville");
			// CREATION DANS LA BASE DES PATIENTS
			Patient p=new Patient(n_patient,code_postal, voie, num_voie,ville,nom, prenom);
			Context.getInstance().getDaoPatient().ajouter(p);
		
			fileA.add(p);
		}else {
			Patient p= Context.getInstance().getDaoPatient().selectById(n_patient);
			fileA.add(p);
		}
		System.out.println("Rendez-vous enregistré");
		
		}

	private static void menuSecretaire() {
		System.out.println("1 - Ajouter un patient à la file d’attente ");
		System.out.println("2 - Afficher l’état de la file d’attente ");
		System.out.println("3 - Afficher les visites d'un patient");
		System.out.println("4 - Prendre une pause");
		System.out.println("5 - Sortir");
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:AjoutPatient();break;
		case 2:AfficherFileS();break;
		case 3:showVisteById();break;
		case 4:Partirenpause();break;
		case 5:menuPrincipal();break;
		default: menuPrincipal(); break;
		}

		menuSecretaire();
	}

	private static void AjoutPatient() {
		System.out.println("Pour prendre un rendez-vous merci de remplire la fiche suivante: ");
		int n_patient= saisieInt("Entrez votre numéro de sécu");

		if (Context.getInstance().getDaoPatient().selectById(n_patient) == null) {
			String nom= saisieString("Votre nom:");
			String prenom= saisieString("Votre prenom:");
			Integer num_voie= saisieInt("Numero de voie");
			String voie= saisieString("Nom de la voie");
			String code_postal= saisieString("Code postal");
			String ville= saisieString("Ville");
			// CREATION DANS LA BASE DES PATIENTS
			Patient p=new Patient(n_patient,code_postal, voie, num_voie,ville,nom, prenom);
			Context.getInstance().getDaoPatient().ajouter(p);
		
			fileA.add(p);
		}else {
			Patient p= Context.getInstance().getDaoPatient().selectById(n_patient);
			fileA.add(p);
		}
		System.out.println("Patient ajouté");	
		menuSecretaire();
	}

	private static void AfficherFileS() {

		for (Patient p:fileA) {
			System.out.println(p.toString());
		}
		menuSecretaire();
	}

	private static void showVisteById() {
		System.out.println("Pour afficher la liste des visites merci de: ");
		int n_patient= saisieInt("Entrez votre numéro de sécu");

		System.out.println(Context.getInstance().getDaoVisite().selectByIdPatient(n_patient));	
		menuSecretaire();
	}

	private static void Partirenpause() {

		for(Patient a:fileA) {
			Context.getInstance().getDaoPatientSerial().ajouter(a);
		}
		fileA.clear();
		//
		pause=0;
		menuPrincipal();
	}

//	private static void showViste() {
//		System.out.println("\nListe des rendez-vous : \n");
//		for(Patient p : Context.getInstance().getDaoPatientSerial().selectAll()) 
//		{
//			System.out.println(p);
//		}
//		menuSecretaire();
//	}

	private static void menuMedecin(Compte connected) {
		
		System.out.println("1 - Prendre un patient");
		System.out.println("2 - Afficher l’état de la file d’attente ");
		System.out.println("3 - Sauvgarder la liste des patients");
		System.out.println("4 - Sortir");


		int choix = saisieInt("");
		switch(choix) 
		{
		case 1:PrendrePatient(connected);break;
		case 2:AfficherFile();break;
		case 3:SauvgarderVisites(connected);break;
		case 4:menuPrincipal();break;
		default: menuPrincipal(); break;
		}

		menuMedecin(connected);
		
	}

	private static void PrendrePatient(Compte connected) {
		System.out.println(fileA.get(0));
		Patient np = fileA.get(0);
		Integer salle=((Medecin) connected).getSalle();
		Integer prix=20;
		LocalDateTime date = LocalDateTime.now(ZoneId.systemDefault());
		Visite v= new Visite(np,(Medecin) connected,prix,salle,date);
		((Medecin) connected).getVisites().add(v);	
		fileA.remove(0);
		
		if (((Medecin) connected).getVisites().size()>=10) {
			SauvgarderVisites(connected);
		}
		menuMedecin(connected);
	}
	
	private static void AfficherFile() {

		for (Patient p:fileA) {
			System.out.println(p.toString());
		}
		menuMedecin(connected);
	}
	
	private static void SauvgarderVisites(Compte connected) {
		System.out.println(((Medecin) connected).getVisites());
		Context.getInstance().getDaoCompte().modifier(connected);
	((Medecin) connected).getVisites().clear();
                
		menuMedecin(connected);
	}

	public static void main(String[] args) {		
		menuPrincipal();
	}
}
