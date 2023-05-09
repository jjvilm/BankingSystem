---------- drop tables
DROP TABLE ASSET;
DROP TABLE LIABILITY;
DROP TABLE TXN;
DROP TABLE ACCOUNT;
DROP TABLE CUSTOMER;
DROP TABLE USER_ROLE;

------------------ USER ROLEs ------------------------------------
CREATE TABLE USER_ROLE (
    USR_ROLE INT NOT NULL,
    DESCRIPTION varchar(30),
    ALL_USERS INT NOT NULL, 
    CREATE_CUSTOMER INT NOT NULL, 
    TRANSFER INT NOT NULL,
    EDIT_ROLE INT NOT NULL,
    CREATE_ACCOUNT INT NOT NULL, 
    PRIMARY KEY (USR_ROLE) 
);

--  Populates the Customer, adding data of 3 initial customers
Insert into USER_ROLE 
    (USR_ROLE, DESCRIPTION, ALL_USERS, CREATE_CUSTOMER, TRANSFER,EDIT_ROLE,CREATE_ACCOUNT)
Values 
-- DO NOT MODIFY CUST_ID directly from DB, must be done programatically
    (1,'User',0, 0, 1, 0, 1),
    (2,'Management',1, 1, 1, 0, 1),
    (3,'Administration',1, 1, 1, 1, 1);


----------------------------Customer-----------------------------------
-- crates the table for customer
create table CUSTOMER(
USR_ROLE INT NOT NULL, -- the role of the use
CUST_ID int not null, --customerID
FIRST_NAME varchar(30), --firstName
LAST_NAME varchar(30), --lastName
PHONE_NUMBER varchar(12), --phoneNumber
USER_ID varchar(20), --userID
PASSWORD varchar(20), --password

Primary Key(CUST_ID),
FOREIGN KEY (USR_ROLE) REFERENCES USER_ROLE(USR_ROLE) on delete cascade
);

--  Populates the Customer, adding data of 3 initial customers
Insert into Customer 
    (USR_ROLE, CUST_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER,USER_ID,PASSWORD)
Values 
-- DO NOT MODIFY CUST_ID directly from DB, must be done programatically
    (1,1, 'John', 'Smith', '620-234-5678', 'Cust1', 'cust1'),
    (1,2, 'Marry', 'Poppins', '417-123-4567', 'Cust2', 'cust2'),
    (1,3, 'Robert', 'Stones', '123-654-7890', 'Cust3', 'cust3'),
    (2,4, 'MANAGMENT', '', '000-000-0000', 'Mgt1', 'mgt1'),
    (3,5, 'ADMINISTRATOR', '', '000-000-0000', 'Admin', 'admin');



----------------------------Account------------------------------------
-- Create Accounts table and populate it with data
create TABLE Account (
    ACC_NUM INT NOT NULL, -- accountNumber
    ACC_TYPE INT NOT NULL, -- 1 for Asset 2 for Liability
    ACC_BALANCE DECIMAL(10,2), -- balance
    CUST_ID INT, -- customerID
    ACC_NAME VARCHAR(30), -- accountName
    DATE_OPENED VARCHAR(12), -- dateOpened
    PRIMARY KEY (ACC_NUM)
);

----------------------------Asset Account------------------
CREATE TABLE ASSET (
    ACC_NUM INT NOT NULL, -- accountNumber
    INT_RATE DECIMAL(3,3), -- interestRate
    TXN_FEE DECIMAL(10,2), -- transactionFee
    PRIMARY KEY (ACC_NUM),
    FOREIGN KEY (ACC_NUM) REFERENCES ACCOUNT(ACC_NUM) on delete cascade
);
--------------------------- Liability -------------------
CREATE TABLE LIABILITY (
    ACC_NUM INT NOT NULL, -- accountNumber
    INT_RATE DECIMAL(3,3), -- interestRate
    MONTHLY_PAYMENT DECIMAL(10,2), -- monthlyPayment
    PAYMENT_DATE VARCHAR(10), -- paymentDate
    PRIMARY KEY (ACC_NUM),
    FOREIGN KEY (ACC_NUM) REFERENCES ACCOUNT(ACC_NUM) on delete cascade
);

-- Populate Account table with only shared columns
INSERT INTO ACCOUNT (ACC_NUM, ACC_TYPE, ACC_BALANCE, CUST_ID, ACC_NAME, DATE_OPENED)
VALUES
--- DO NOT MODIFY ACC_TYPE or ACC_NUM directly from database, must be done programatically
    (1001, 1, 1000, 1, 'Investments', '01/01/2023'),
    (1002, 1, 1000, 2, 'Savings', '01/02/2023'),
    (1003, 1, 1000, 3, 'Checking', '01/03/2023'),
    (1004, 1, 1000, 4, 'Checking', '01/03/2023'),
    (1005, 1, 1000, 5, 'Checking', '01/03/2023'),

    (2001, 2, 1000, 1, 'Credit', '01/04/2023'),
    (2002, 2, 1000, 2, 'Loans', '01/05/2023'),
    (2003, 2, 1000, 3, 'Mortgage', '01/06/2023'),
    (2004, 2, 1000, 4, 'Mortgage', '01/06/2023'),
    (2005, 2, 1000, 5, 'Mortgage', '01/06/2023');


-- Populate Asset table
INSERT INTO ASSET (ACC_NUM, INT_RATE, TXN_FEE)
VALUES
    (1001, 0.01, 1.25),
    (1002, 0.02, 1.50),
    (1003, 0.03, 1.75),
    (1004, 0.03, 1.75),
    (1005, 0.03, 1.75);

-- Populate Liability table
INSERT INTO LIABILITY (ACC_NUM, INT_RATE, MONTHLY_PAYMENT, PAYMENT_DATE)
VALUES
    (2001, 0.04, 5, '02/04/2023'),
    (2002, 0.05, 6, '02/05/2023'),
    (2003, 0.06, 7, '02/06/2023'),
    (2004, 0.06, 7, '02/06/2023'),
    (2005, 0.06, 7, '02/06/2023');

-----------------------------Transaction----------------------------------
-- crates the table for transactions
create table TXN(
TXN_ID int not null, --transactionID
TXN_DATE varchar(12), --transactionDate
ACC_NUM int not null, --accountNumber
DESCRIPTION varchar(30), --description
TXN_AMOUNT decimal(10,2), --transactionAmount
ACCOUNTED int not null, --accounted, checks wehther txn has been accounted for

PRIMARY KEY (TXN_ID, ACC_NUM),
FOREIGN KEY (ACC_NUM) REFERENCES ACCOUNT(ACC_NUM) on delete cascade
);

-- create 3 transactions per account, per customer.  18 total transactions
INSERT INTO TXN
    (TXN_ID, TXN_DATE, ACC_NUM, DESCRIPTION, TXN_AMOUNT, ACCOUNTED)
VALUES
--- DO NOT MODIFY TXN_ID, ACC_NUM directly from DB must be done programatically
--Cust1
            --Asset
    (1, '01/01/2023', 1001, 'Sales Item', 10,0),
    (2, '01/02/2023', 1001, 'Receipt',  20,0),
    (3, '01/03/2023', 1001, 'Purchase',30,0),
            --Liability
    (1, '01/04/2023', 2001, 'Random bills',25,0),
    (2, '01/05/2023', 2001, 'Restaurant',50,0),
    (3, '01/06/2023', 2001, 'Travel',75,0),
--Cust2
            --Asset
    (1, '01/07/2023', 1002, 'Gifts',5,0),
    (2, '01/08/2023', 1002, 'Payment',10,0),
    (3, '01/09/2023', 1002, 'Payment',15,0),
            --Liability
    (1, '01/10/2023', 2002, 'Travel',100,0),
    (2, '01/11/2023', 2002, 'Hotel Stay',250,0),
    (3, '01/12/2023', 2002, 'Exotic drink',350,0),
--Cust3
            --Asset
    (1, '01/13/2023', 1003, 'Stock X',5,0),
    (2, '01/14/2023', 1003, 'Security Y',10,0),
    (3, '01/15/2023', 1003, 'CD',15,0),
            --Liability
    (1, '01/16/2023', 2003, 'Loan X',35,0),
    (2, '01/17/2023', 2003, 'Mortage Y',47,0),
    (3, '01/19/2023', 2003, 'Credit Card X',95,0),

-- cust4
--Asset
    (1, '01/13/2023', 1004, 'Stock X',5,0),
    (2, '01/14/2023', 1004, 'Security Y',10,0),
    (3, '01/15/2023', 1004, 'CD',15,0),
            --Liability
    (1, '01/16/2023', 2004, 'Loan X',35,0),
    (2, '01/17/2023', 2004, 'Mortage Y',47,0),
    (3, '01/19/2023', 2004, 'Credit Card X',95,0),
--cust 5
--Asset
    (1, '01/13/2023', 1005, 'Stock X',5,0),
    (2, '01/14/2023', 1005, 'Security Y',10,0),
    (3, '01/15/2023', 1005, 'CD',15,0),
            --Liability
    (1, '01/16/2023', 2005, 'Loan X',35,0),
    (2, '01/17/2023', 2005, 'Mortage Y',47,0),
    (3, '01/19/2023', 2005, 'Credit Card X',95,0);

    
