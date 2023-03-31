create table user_info
(
    id      SERIAL      PRIMARY KEY ,
    name    VARCHAR(10) NOT NULL ,
    surname VARCHAR(10) NOT NULL
);

create table role
(
    id      SERIAL      PRIMARY KEY ,
    role    VARCHAR(10) NOT NULL UNIQUE
);

create table permission
(
    id          SERIAL      PRIMARY KEY ,
    login       VARCHAR(10) NOT NULL ,
    password    VARCHAR(10) NOT NULL ,
    user_id     INT         NOT NULL    UNIQUE  REFERENCES user_info (id) ,
    role_id     INT         NOT NULL    REFERENCES role (id)
);

create table bank_account
(
    id              INT         PRIMARY KEY ,
    account_number  BIGSERIAL   NOT NULL,
    balance         INT         NOT NULL ,
    start_date      DATE        NOT NULL ,
    end_date        DATE        NOT NULL ,
    user_id         INT         NOT NULL    REFERENCES user_info (id)
);

create table credit_card
(
    id                  INT         PRIMARY KEY ,
    credit_card_number  BIGSERIAL   NOT NULL    UNIQUE ,
    pin_code            INT         NOT NULL,
    cvv                 INT         NOT NULL,
    credit_balance      INT,
    amount_balance      INT         NOT NULL ,
    current_balance     INT         NOT NULL ,
    minimum_payment     INT,
    monthly_payment     INT,
    start_date          DATE        NOT NULL ,
    end_date            DATE        NOT NULL ,
    status              VARCHAR(10) NOT NULL ,
    bank_account_id     INT         NOT NULL    REFERENCES bank_account (id)
);
