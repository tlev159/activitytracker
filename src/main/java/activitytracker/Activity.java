package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(generator = "Act_Gen")
    @TableGenerator(name = "Act_Gen", table = "act_id_gen", pkColumnName = "id_gen", valueColumnName = "id_val")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "description", nullable = false, length = 200)
    private String desc;

    @Column(name = "activity_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "act_id"))
    @Column(name = "label")
    private List<String> labels = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "activity")
    @OrderBy(value = "time")
    private List<TrackPoint> trackPoints;

    public void addTrackPoint(TrackPoint trackPoint) {
        if (trackPoints == null) {
            trackPoints = new ArrayList<>();
        }
        trackPoints.add(trackPoint);
        trackPoint.setActivity(this);
    }

    public Activity() {
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

    public Activity(LocalDateTime startTime, String desc, ActivityType type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    private void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PostUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", desc='" + desc + '\'' +
                ", type=" + type +
                '}';
    }
}
