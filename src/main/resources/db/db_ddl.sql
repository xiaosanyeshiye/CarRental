create schema rental default character set utf8mb4 collate utf8mb4_unicode_ci;

CREATE TABLE t_car (
    id BIGINT(32) PRIMARY KEY AUTO_INCREMENT,
    car_name VARCHAR(32) NOT NULL DEFAULT '',
    max_time_to_rented TINYINT(4) NOT NULL DEFAULT 4,
    rented_record_id BIGINT(32) NOT NULL DEFAULT 0,
    last_rented_time DATETIME,
    car_status VARCHAR(16) NOT NULL DEFAULT 'FREE' COMMENT 'car status, contains FREE, RENTED, OFF_SHELVES',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0
);

CREATE TABLE t_rented_record (
    id BIGINT(32) PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT(32) NOT NULL DEFAULT 0,
    car_id BIGINT(32) NOT NULL DEFAULT 0,
    car_name VARCHAR(32) NOT NULL DEFAULT '',
    start_rented_time DATETIME,
    expect_return_time DATETIME,
    actual_return_time DATETIME,
    rented_status VARCHAR(16) NOT NULL DEFAULT 'RENTED' COMMENT 'car status, contains RENTED, RETURN',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0
);

CREATE TABLE t_customer (
    id BIGINT(32) PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(32) NOT NULL DEFAULT '',
    login_account VARCHAR(32) NOT NULL DEFAULT '',
    token VARCHAR(36) NOT NULL DEFAULT '',
    token_expire_time DATETIME,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,
    UNIQUE(login_account)
);


