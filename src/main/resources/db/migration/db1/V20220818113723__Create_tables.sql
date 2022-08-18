/**
 * @author Kenny Fang
 * @since 0.0.1
 */
CREATE TABLE t_user
(
    id          INT AUTO_INCREMENT,
    username    VARCHAR(64) NOT NULL,
    nick_name   VARCHAR(64) NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)
