DROP TABLE IF EXISTS PORTFOLIO;

CREATE TABLE PORTFOLIO (
    id INT PRIMARY KEY,
    portfolio_name varchar(255) not null,
    ticker varchar(5) not null,
    date date not null,
    open_price DECIMAL(19,4) not null,
    high_price DECIMAL(19,4) not null,
    low_price DECIMAL(19,4) not null,
    close_price DECIMAL(19,4) not null,
    adjusted_close_price DECIMAL(19,4) not null,
    volume int not null,
    quantity DECIMAL(19,4) not null,
    market_value DECIMAL(19,4) not null
)