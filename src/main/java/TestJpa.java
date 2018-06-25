import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dev.biblio.Client;
import dev.biblio.Emprunt;
import dev.biblio.Livre;

public class TestJpa {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("biblio");
		EntityManager em = entityManagerFactory.createEntityManager();

		// méthode find permettant d’extraire un livre en fonction de son id
		Livre l = em.find(Livre.class,1);
		if (l != null){
			System.out.println(l.getId());
		}

		// requête JPQL pour trouver un LIVRE en fonction de son TITRE
		TypedQuery<Livre> query = em.createQuery("select l from Livre l where l.titre='Germinal'", Livre.class);

		List<Livre> listLivre = query.getResultList();
		System.out.println(listLivre.get(0).getAuteur());

		// requête JPQL pqui permet d’extraire un emprunt et tous ses livres associés.
		TypedQuery<Emprunt> query2 = em.createQuery("select e from Emprunt e where e.id=1", Emprunt.class);
		List<Emprunt> listEmprunt = query2.getResultList();
		
		Emprunt e = listEmprunt.get(0);
		// boucle sur les livres d'un emprunt donné
		for (Livre livre: e.getLivres())
			System.out.println(livre.getAuteur() + ", " + livre.getTitre());

		// requête JPQL qui permet d’extraire tous les emprunts d’un client donné.
		TypedQuery<Emprunt> query3 = em.createQuery("select e from Emprunt e where e.client.id=1", Emprunt.class);
		List<Emprunt> listEmprunt2 = query3.getResultList();
		
		for (Emprunt emprunt : listEmprunt2 )
			System.out.println(emprunt.getId() + ", " + emprunt.getDateDebut());
		
		/*Client client = em.find(Client.class, 1);
		List<Emprunt> emprunts = client.getEmprunts();
		for (Emprunt e : emprunts) {
			System.out.println(e.getId() + ", " + e.getDateDebut());
		} */

		em.close();
		entityManagerFactory.close();

	}

}
