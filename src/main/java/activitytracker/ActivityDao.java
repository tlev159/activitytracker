package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ActivityDao {

    private EntityManagerFactory entityManagerFactory;

    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveActivity(Activity activity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
    }

    public Activity findActivityById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
        Activity activity = em.find(Activity.class, id);
        em.close();
        return activity;
    }

    public List<Activity> listActivities() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT a FROM Activity a ORDER BY a.startTime", Activity.class).getResultList();
    }

    public void updateActivity(long id, String desc) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class, id);
        activity.setUpdatedAt();
        activity.setDesc(desc);
        em.getTransaction().commit();
    }

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.createQuery("SELECT a FROM Activity a join fetch a.labels WHERE id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }
}
