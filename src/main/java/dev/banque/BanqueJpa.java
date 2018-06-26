package dev.banque;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BanqueJpa {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banque");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		// Instancie une banque
		Banque banque = new Banque();
		banque.setNom("BCP");
		em.persist(banque);
		
		// Instancie des clients
		Adresse a1 = new Adresse(14, "rue Victor Hugo", 44000, "Nantes");
		Client c1 = new Client("Loup", "Patricia", LocalDate.of(1972, 3, 18), banque, a1);
		em.persist(c1);
		
		Adresse a2 = new Adresse(3, "rue du Chat", 44000, "Nantes");
		Client c2 = new Client("Dupont", "Paul", LocalDate.of(1962, 9, 6), banque, a2);
		em.persist(c2);
		
		Adresse a3 = new Adresse(3, "rue du Chat", 44000, "Nantes");
		Client c3 = new Client("Dupont", "Elodie", LocalDate.of(1967, 5, 23), banque, a3);
		em.persist(c2);
		
		// Instancie des comptes
		LivretA compte1 = new LivretA();
		compte1.setNumero("08LP43");
		compte1.setSolde(1004.70);
		compte1.setTaux(0.4);
		em.persist(compte1);
		
		AssuranceVie compte2 = new AssuranceVie();
		compte2.setNumero("33MP69");
		compte2.setSolde(15020);
		compte2.setTaux(1.8);
		compte2.setDateFin(LocalDate.of(2040, 4, 1));
		em.persist(compte2);
		
		LivretA compte3 = new LivretA();
		compte3.setNumero("89JC81");
		compte3.setSolde(2000);
		compte3.setTaux(0.4);
		em.persist(compte3);

		// Instancie des opérations
		Virement vir1 = new Virement();
		vir1.setDate(LocalDateTime.of(2018, 1, 1, 12, 0, 0));
		vir1.setMontant(805);
		vir1.setMotif("Virement_loyer");
		vir1.setBeneficiaire("Monsieur Martin");
		
		Virement vir2 = new Virement();
		vir2.setDate(LocalDateTime.of(2018, 2, 11, 12, 3, 6));
		vir2.setMontant(69);
		vir2.setMotif("Virement_EDF");
		vir2.setBeneficiaire("EDF");
		
		
		// Lien clients et comptes
		c1.getComptes().add(compte1); // insérer un client avec plusieurs comptes : livret A
		c1.getComptes().add(compte2); // insérer un client avec plusieurs comptes : assurance vie
		c2.getComptes().add(compte3); // insérer un compte associé à 2 clients
		c3.getComptes().add(compte3); // insérer un compte associé à 2 clients
		
		// Lien comptes et opérations
		compte1.getOperations().add(vir1);
		compte3.getOperations().add(vir2);
		
		et.commit();
		
		em.close();
		entityManagerFactory.close();

	}

}
