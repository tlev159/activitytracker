CREATE TABLE activities (
                            id BIGINT AUTO_INCREMENT,
                            start_time DATE NOT NULL,
                            description VARCHAR(255) NOT NULL,
                            activity_type VARCHAR(100) NOT NULL,
                            CONSTRAINT pk_activities PRIMARY KEY (id)
);