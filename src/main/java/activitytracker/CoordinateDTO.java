package activitytracker;

public class CoordinateDTO {

    private double lat;

    private double lon;

    public CoordinateDTO() {
    }

    public CoordinateDTO(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
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
        return "CoordinateDTO{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
