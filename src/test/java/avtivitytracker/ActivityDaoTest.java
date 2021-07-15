package avtivitytracker;

import activitytracker.Activity;
import activitytracker.ActivityDao;
import activitytracker.ActivityType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActivityDaoTest {

    private ActivityDao activityDao;
    private MariaDbDataSource dataSource;


    @BeforeEach
    public void init() {
        try {
        dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/activitytracker");
            dataSource.setUser("activitytracker");
            dataSource.setPassword("activitytracker");

            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .locations("db/migration")
                    .load();
            flyway.clean();
            flyway.migrate();
        } catch (SQLException sqle) {
            new SQLException("Can not connect to database!", sqle);
        }

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);
    }

    @Test
    public void testSaveThenFind() {
        Activity activity = new Activity(LocalDateTime.now(), "Biking in the town", ActivityType.BIKING);
        activityDao.saveActivity(activity);
        Activity loadedActivity = activityDao.findActivityById(activity.getId());
        assertThat(loadedActivity.getDesc(), equalTo("Biking in the town"));
    }
}
