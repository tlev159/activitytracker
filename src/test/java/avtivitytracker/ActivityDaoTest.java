package avtivitytracker;

import activitytracker.*;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActivityDaoTest {

    private ActivityDao activityDao;

    private TrackPointDao trackPointDao;
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
        trackPointDao = new TrackPointDao(factory);

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

    @Test
    public void testAddLabelThenListById() {
        Activity activity1 = new Activity(LocalDateTime.now(), "Marathon running in Munich", ActivityType.RUNNING);
        activity1.setLabels(new ArrayList<>(List.of("running", "water", "shoe", "isotonic water")));

        Activity activity2 = new Activity(LocalDateTime.now(), "Biking in the town", ActivityType.BIKING);
        activity2.setLabels(new ArrayList<>(List.of("bike", "wheel", "pump")));

        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);

        Activity anotherActivity = activityDao.findActivityByIdWithLabels(activity1.getId());

        System.out.println(anotherActivity.getLabels());
        assertThat("water", equalTo(anotherActivity.getLabels().get(1)));
    }

    @Test
    public void testAddTrackPointsThenListById() {
        Activity activity = new Activity(LocalDateTime.now(), "Marathon running in Munich", ActivityType.RUNNING);
        activity.setTrackPoints(new ArrayList<>(List.of(
                new TrackPoint(LocalDate.of(2021, 07, 13), 41.2, 45.5),
                new TrackPoint(LocalDate.of(2021, 07, 16), 39.6, 44.1),
                new TrackPoint(LocalDate.of(2021, 07, 20), 40.5, 46.2)
        )));

        activityDao.saveActivity(activity);
        Activity anotherActivity = activityDao.findActivityByIdWithTrackPoints(activity.getId());
        assertThat(LocalDate.of(2021, 07, 16), equalTo(anotherActivity.getTrackPoints().get(1).getTime()));
    }

    @Test
    public void testFindActivityByIdWithTrackPoints() {
        TrackPoint trackPoint1 = new TrackPoint(LocalDate.of(2020, 07, 13), 41.2, 45.5);
        TrackPoint trackPoint2 = new TrackPoint(LocalDate.of(2019, 07, 16), 39.6, 44.1);
        TrackPoint trackPoint3 = new TrackPoint(LocalDate.of(2018, 07, 20), 40.5, 46.2);
        TrackPoint trackPoint4 = new TrackPoint(LocalDate.of(2017, 07, 21), 41.5, 47.2);
        TrackPoint trackPoint5 = new TrackPoint(LocalDate.of(2021, 07, 22), 42.5, 48.2);

        Activity activity = new Activity(LocalDateTime.now(), "Marathon running in Munich", ActivityType.RUNNING);
        activity.setLabels(new ArrayList<>(List.of("bike", "wheel", "pump")));

        activity.addTrackPoint(trackPoint1);
        activity.addTrackPoint(trackPoint2);
        activity.addTrackPoint(trackPoint3);
        activity.addTrackPoint(trackPoint4);
        activity.addTrackPoint(trackPoint5);

        activityDao.saveActivity(activity);
        Activity anotherActivity = activityDao.findActivityByIdWithTrackPoints(activity.getId());
        System.out.println(anotherActivity.getTrackPoints());
        assertThat(anotherActivity.getTrackPoints())
                .extracting(TrackPoint::getLat)
                .contains(41.2, 41.5);
    }

}
