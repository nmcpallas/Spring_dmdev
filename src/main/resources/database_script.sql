create table user_info
(
    id      SERIAL      PRIMARY KEY ,
    name    VARCHAR(10) NOT NULL ,
    surname VARCHAR(10) NOT NULL
);

create table role
(
    id      SERIAL      PRIMARY KEY ,
    role    VARCHAR(10) NOT NULL
);

create table permission
(
    id          SERIAL      PRIMARY KEY ,
    login       VARCHAR(10) NOT NULL ,
    password    VARCHAR(10) NOT NULL ,
    user_id     INT         REFERENCES user_info (id) ,
    role_id     INT         REFERENCES role (id)
);

create table bank_account
(
    id              INT         PRIMARY KEY ,
    account_number  BIGSERIAL   NOT NULL    CHECK ( account_number BETWEEN 5556000000000000 AND 5556999999999999 ),
    balance         INT         NOT NULL ,
    start_date      DATE        NOT NULL ,
    end_date        DATE        NOT NULL ,
    status          varchar(10) NOT NULL ,
    user_id         INT         REFERENCES user_info (id)
);

create table credit_card
(
    id                  INT         PRIMARY KEY ,
    credit_card_number  BIGSERIAL   NOT NULL    CHECK ( credit_card_number BETWEEN 5556000000000000 AND 5556999999999999 ),
    pin_code            INT         NOT NULL    CHECK ( pin_code BETWEEN 999 AND 9999 ),
    cvv                 INT         NOT NULL    CHECK ( cvv BETWEEN 100 AND 999 ),
    credit_balance      INT,
    amount_balance      INT         NOT NULL ,
    current_balance     INT         NOT NULL ,
    minimum_payment     INT,
    monthly_payment     INT,
    start_date          DATE        NOT NULL ,
    end_date            DATE        NOT NULL ,
    status              VARCHAR(10) NOT NULL ,
    user_id             INT         REFERENCES user_info (id)
);
