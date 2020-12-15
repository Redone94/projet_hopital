package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import Config.Context;

@Entity
@DiscriminatorValue("Secretaire")
public class Secretaire extends Compte {
	

	
	public Secretaire() {}
	
}