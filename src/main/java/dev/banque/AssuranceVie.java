package dev.banque;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ASSURANCE_VIE")
public class AssuranceVie extends Compte {
	
		
		@Column(name="DATE_FIN", nullable=false)
		private LocalDate dateFin;
		
		@Column(name="TAUX", nullable=false)
		private double taux;


		/**
		 * @return the dateFin
		 */
		public LocalDate getDateFin() {
			return dateFin;
		}

		/**
		 * @param dateFin the dateFin to set
		 */
		public void setDateFin(LocalDate dateFin) {
			this.dateFin = dateFin;
		}

		/**
		 * @return the taux
		 */
		public double getTaux() {
			return taux;
		}

		/**
		 * @param taux the taux to set
		 */
		public void setTaux(double taux) {
			this.taux = taux;
		}

		
	}


