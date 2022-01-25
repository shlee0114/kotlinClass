CREATE TABLE notice
(
    seq      BIGINT        NOT NULL AUTO_INCREMENT,
    writer   VARCHAR2(10)  NOT NULL,
    title    VARCHAR2(100) NOT NULL,
    content  VARCHAR2(500) NOT NULL,
    password VARCHAR2(10) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    PRIMARY KEY (seq)
);
