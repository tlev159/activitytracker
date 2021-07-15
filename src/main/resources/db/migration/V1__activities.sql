CREATE TABLE activities (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            start_time DATE NOT NULL,
                            description VARCHAR(255) NOT NULL,
                            activity_type VARCHAR(100) NOT null
);