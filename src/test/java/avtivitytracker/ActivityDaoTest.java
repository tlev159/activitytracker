package avtivitytracker;

import activitytracker.Activity;
import activitytracker.ActivityDao;
import activitytracker.ActivityType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActivityDaoTest {

    private ActivityDao activityDao;
//    private MariaDbDataSource dataSource;

    @BeforeEach
    public void init() {
//        try {
//        dataSource = new MariaDbDataSource();
//            dataSource.setUrl("jdbc:mariadb://localhost:3306/activitytracker?useUnicode=true");
//            dataSource.setUser("activitytracker");
//            dataSource.setPassword("activitytracker");
//
//            Flyway flyway = Flyway.configure()
//                    .dataSource(dataSource)
//                    .locations("db/migration")
//                    .load();
//            flyway.clean();
//            flyway.migrate();
//
//        } catch (SQLException sqle) {
//            new SQLException("Can not connect to database!", sqle);
//        }

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);

    }

    @Test
    public void testSaveThenFind() {
        Activity activity = new Activity(LocalDateTime.now(), "Biking in the town", ActivityType.BIKING);
        activityDao.saveActivity(activity);

        long id = activity.getId();
        Activity loadedActivity = activityDao.findActivityById(id);
        assertThat(loadedActivity.getDesc(), equalTo("Biking in the town"));
    }

    @Test
    public void testSaveThenListAll() {
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "Biking in the town", ActivityType.BIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "Basketball on the street", ActivityType.BASKETBALL));
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "Marathon running in Munich", ActivityType.RUNNING));

        List<Activity> activities = activityDao.listActivities();
        assertThat("Basketball on the street", equalTo(activities.get(1).getDesc()));
    }

    @Test
    public void testUpdateThenListAll() {
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "Biking in the town", ActivityType.BIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "Basketball on the street", ActivityType.BASKETBALL));
        activityDao.saveActivity(new Activity(LocalDateTime.now(), "Marathon running in Munich", ActivityType.RUNNING));

        activityDao.updateActivity(2L, "Streetball at the Discothek");

        List<Activity> activities = activityDao.listActivities();
        System.out.println(activities);
        assertThat("Streetball at the Discothek", equalTo(activities.get(1).getDesc()));
    }
}
