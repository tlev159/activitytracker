package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityTrackerMain {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Activity activity1 = new Activity(LocalDateTime.of(2021,07,01,10,30), "Basketball on the beach", ActivityType.BASKETBALL);
        Activity activity2 = new Activity(LocalDateTime.of(2021,07,02,12,45), "Biking to the Grandma", ActivityType.BIKING);

        entityManager.persist(activity1);
        entityManager.persist(activity2);

        entityManager.getTransaction().commit();

        long id = activity1.getId();

        Activity activity = entityManager.find(Activity.class, id);

        entityManager.getTransaction().begin();
        activity = entityManager.find(Activity.class, id);
        activity.setDesc("Basketball at home");
        entityManager.getTransaction().commit();

        List<Activity> activities = entityManager.createQuery("SELECT a FROM Activity a", Activity.class)
                .getResultList();
        System.out.println(activities);

        entityManager.getTransaction().begin();

        activity = entityManager.find(Activity.class, id);
        entityManager.remove(activity);
        entityManager.getTransaction().commit();

        activities = entityManager.createQuery("SELECT a FROM Activity a", Activity.class)
                .getResultList();
        System.out.println(activities);


        entityManager.close();
        factory.close();

    }

    private static void addEntityToDatabase(EntityManager entityManager) {

    }
}
