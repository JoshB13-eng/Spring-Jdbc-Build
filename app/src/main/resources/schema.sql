
CREATE TABLE FRUIT(
    
    numberOfFruit INT NOT NULL UNIQUE AUTO_INCREMENT,
    
    fileByt Image,
    
    PRIMARY KEY(numberOfFruit)
);

CREATE TABLE APPLE(

    numberOfApple INT NOT NULL UNIQUE AUTO_INCREMENT,
    
    amount INT NOT NULL,
    
    PRIMARY KEY(numberOfApple),
    
    FOREIGN KEY (numberOfApple) REFERENCES FRUIT(numberOfFruit)
);


CREATE TABLE ORANGE(

    numberOfOrange INT NOT NULL UNIQUE AUTO_INCREMENT,
    
    amount INT NOT NULL,
    
    PRIMARY KEY(numberOfOrange),
    
    FOREIGN KEY (numberOfOrange) REFERENCES FRUIT(numberOfFruit)
);


CREATE TABLE LEMON(

    numberOfLemon INT NOT NULL UNIQUE AUTO_INCREMENT,
    
    amount INT NOT NULL,
    
    PRIMARY KEY(numberOfLemon),
    
    FOREIGN KEY (numberOfLemon) REFERENCES FRUIT(numberOfFruit)
);


INSERT INTO FRUIT(numberOfFruit,fileByt) VALUES (0,0x00012DF4F6C);

INSERT INTO FRUIT(numberOfFruit,fileByt) VALUES (1,0x00012DA4F6C);

INSERT INTO FRUIT(numberOfFruit,fileByt) VALUES (2,0x00011DF4F6C);

INSERT INTO FRUIT(numberOfFruit,fileByt) VALUES (3,0x00022DF4F6C);

INSERT INTO FRUIT(numberOfFruit,fileByt) VALUES (4,0x00012DF4F6C5);


INSERT INTO APPLE(numberOfApple,amount) VALUES (0,0);

INSERT INTO APPLE(numberOfApple,amount) VALUES (1,5);

INSERT INTO APPLE(numberOfApple,amount) VALUES (2,10);

INSERT INTO APPLE(numberOfApple,amount) VALUES (3,15);

INSERT INTO APPLE(numberOfApple,amount) VALUES (4,20);



INSERT INTO LEMON(numberOfLemon,amount) VALUES (0,0);

INSERT INTO LEMON(numberOfLemon,amount) VALUES (1,3);

INSERT INTO LEMON(numberOfLemon,amount) VALUES (2,6);

INSERT INTO LEMON(numberOfLemon,amount) VALUES (3,9);

INSERT INTO LEMON(numberOfLemon,amount) VALUES (4,12);



INSERT INTO ORANGE(numberOfOrange,amount) VALUES (0,0);

INSERT INTO ORANGE(numberOfOrange,amount) VALUES (1,2);

INSERT INTO ORANGE(numberOfOrange,amount) VALUES (2,4);

INSERT INTO ORANGE(numberOfOrange,amount) VALUES (3,6);

INSERT INTO ORANGE(numberOfOrange,amount) VALUES (4,8);
