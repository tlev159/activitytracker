package activitytracker;

import org.hibernate.internal.SessionFactoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TrackPointDao {

    private EntityManagerFactory factory;

    public TrackPointDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveTrackPoint(TrackPoint trackPoint) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(trackPoint);
        em.getTransaction().commit();
        em.close();
    }
}
