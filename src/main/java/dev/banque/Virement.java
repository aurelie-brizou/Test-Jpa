package dev.banque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="VIREMENT")
public class Virement extends Operation {
	
	
	@Column(name="BENEFICIAIRE", length = 30, nullable=false, unique=true)
	private String beneficiaire;


	/**
	 * @return the beneficiaire
	 */
	public String getBeneficiaire() {
		return beneficiaire;
	}

	/**
	 * @param beneficiaire the beneficiaire to set
	 */
	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
	
	

}
