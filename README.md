# hcl-lloyds-ewallet-project
1. User

sql
CREATE TABLE User (
user_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100),
email VARCHAR(100) UNIQUE,
phone VARCHAR(15),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


---

2. Bank

sql
CREATE TABLE Bank (
bank_id INT PRIMARY KEY AUTO_INCREMENT,
bank_name VARCHAR(100),
branch VARCHAR(100)
);


---

3. Wallet

sql
CREATE TABLE Wallet (
wallet_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
bank_id INT,
balance DECIMAL(12,2) DEFAULT 0.00,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES User(user_id),
FOREIGN KEY (bank_id) REFERENCES Bank(bank_id)
);


---

4. Transaction_Ledger

sql
CREATE TABLE Transaction_Ledger (
txn_id INT PRIMARY KEY AUTO_INCREMENT,
wallet_id INT,
amount DECIMAL(12,2),
txn_type ENUM('credit', 'debit'),
description VARCHAR(255),
txn_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id)
);


---

5. Merchant

sql
CREATE TABLE Merchant (
merchant_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100),
category VARCHAR(50),
contact VARCHAR(100)
);


---

6. App_Store

sql
CREATE TABLE App_Store (
app_id INT PRIMARY KEY AUTO_INCREMENT,
app_name VARCHAR(100),
merchant_id INT,
price DECIMAL(10,2),
FOREIGN KEY (merchant_id) REFERENCES Merchant(merchant_id)
);


---

7. Payment

sql
CREATE TABLE Payment (
payment_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
app_id INT,
amount DECIMAL(12,2),
payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES User(user_id),
FOREIGN KEY (app_id) REFERENCES App_Store(app_id)
);


---

8. Notification

sql
CREATE TABLE Notification (
notification_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
message TEXT,
sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES User(user_id)
);