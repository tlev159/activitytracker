package activitytracker;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.time.LocalDate;

@Embeddable
public class TrackPoint {

    private LocalDate time;

    @Column(name = "latitude")
    private double lat;

    @Column(name = "longitude")
    private double lon;

    public TrackPoint() {
    }

    public TrackPoint(LocalDate time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "TrackPoint{" +
                "time=" + time +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
