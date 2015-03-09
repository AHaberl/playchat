package helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceHelper {

	public static void preparePersistency(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emf");
		EntityManager em = emf.createEntityManager();
		/* User user = new User();
		 * user.setName("Max");
		 * user.setCreated(new Date());
		 * Message message = new Message();
		 * message.setCreated(new Date());
		 * message.setContent("Content");
		 * 
		 * em.getTransaction().begin();
		 * em.persist(user);
		 * em.persist(message);
		 * em.getTransaction().commit();
		 * 
		 * List messages = em.createQuery("from Message where owner = :owner")
		 * .setParameter("owner", user)
		 * .setMaxResults(5)
		 * .getResultList()
		 * 
		 * 
		 */
	}
}
