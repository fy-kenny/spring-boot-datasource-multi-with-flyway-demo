/**
 * @author Kenny Fang
 * @since 0.0.1
 */
CREATE TABLE t_class
(
    id          INT AUTO_INCREMENT,
    name        VARCHAR(64) NOT NULL,
    type        INT         NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)
