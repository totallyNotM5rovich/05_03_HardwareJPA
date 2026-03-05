CREATE TABLE Tip
(
    id IDENTITY PRIMARY KEY,
    naziv        VARCHAR(50)    NOT NULL
);

CREATE TABLE Hardware
(
    id IDENTITY PRIMARY KEY,
    naziv        VARCHAR(100) NOT NULL,
    sifra        VARCHAR(255) NOT NULL UNIQUE,
    cijena       DECIMAL(10, 2) NOT NULL,
    kolicina     INT NOT NULL,
    tipId  INT,
    FOREIGN KEY (tipId) REFERENCES Tip(id)
);