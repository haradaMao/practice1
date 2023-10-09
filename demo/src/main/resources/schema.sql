create table issues(
    -- AUTO_INCREMENTは投入されたデータにidを自動的につけてくれる
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    summary VARCHAR(256) NOT NULL,
    description VARCHAR(256) NOT NULL
);
