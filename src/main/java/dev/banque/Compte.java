package dev.banque;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Aurelie-B
 * Compte abstrait : Livret A et Assurance Vie
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="COMPTE")
public class Compte {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany
	@JoinTable(name="COM_CLI",
	joinColumns= @JoinColumn(name="ID_COM", referencedColumnName="ID"),
	inverseJoinColumns= @JoinColumn(name="ID_CLI", referencedColumnName="ID")
	)
	private List<Client> clients = new ArrayList<>(); // référence vers les clients

	@Column(name="NUMERO", length = 30, nullable=false)
	private String numero;
	
	@Column(name="SOLDE", nullable=false)
	private double solde;
	
	@OneToMany(mappedBy="compte")
	private List<Operation> operations = new ArrayList<>(); // référence vers les opérations

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the clients
	 */
	public List<Client> getClients() {
		return clients;
	}

	/**
	 * @param clients the clients to set
	 */
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the solde
	 */
	public double getSolde() {
		return solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}

	/**
	 * @return the operations
	 */
	public List<Operation> getOperations() {
		return operations;
	}

	/**
	 * @param operations the operations to set
	 */
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	
}
