/**
 * @author Kenny Fang
 * @since 0.0.1
 */
CREATE TABLE t_course (
    id INT AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    type VARCHAR(64) NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)
