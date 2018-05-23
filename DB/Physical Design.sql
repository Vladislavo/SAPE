CREATE TABLE Company (
  cif VARCHAR(15),
  mail VARCHAR(50),
  name VARCHAR(50),
  telephone VARCHAR(15),
  address VARCHAR(500),
  vat INT,
  CONSTRAINT Company_pk PRIMARY KEY (cif)
);

CREATE TABLE Internship (
  id NUMERIC(10),
  cif_company VA  RCHAR(15),
  remuneration VARCHAR(10),
  mailContactPerson VARCHAR(50),
  description VARCHAR(2000),
  CONSTRAINT Internship_pk PRIMARY KEY (id),
  CONSTRAINT Company_Internship_fk FOREIGN KEY (cif_company)
  REFERENCES Company (cif) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ProjectOffer (
  id NUMERIC(10),
  id_Internship NUMERIC(10),
  title VARCHAR(50),
  itinerary VARCHAR(50),
  startDate DATE,
  lastChangeDate DATE,
  tasks VARCHAR(50),
  objectives VARCHAR(50),
  state VARCHAR(10),
  CONSTRAINT ProjectOffer_pk PRIMARY KEY (id),
  CONSTRAINT Internship_ProjectOffer_fk FOREIGN KEY (id_Internship) 
  REFERENCES Internship (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Review (
  id_ProjectOffer NUMERIC(10),
  creationDate DATE,
  description VARCHAR(2000),
  CONSTRAINT Review_pk PRIMARY KEY (id_ProjectOffer, creationDate),
  CONSTRAINT ProjectOffer_InternshipOffer_fk FOREIGN KEY (id_ProjectOffer)
  REFERENCES ProjectOffer (id) ON UPDATE CASCADE ON DELETE CASCADE
);










CREATE TABLE Student (
    nif VARCHAR(15),
	mail VARCHAR(50),
	name VARCHAR(50),
	telephone VARCHAR(15),
	gradeCertificate VARCHAR(50),
	itinerary VARCHAR(50),
	CONSTRAINT Student_pk PRIMARY KEY (nif)
);

CREATE TABLE Tutor (
  mail VARCHAR(50),
  name VARCHAR(50),
  telephone VARCHAR(15),
  office VARCHAR(15),
  CONSTRAINT Tutor_pk PRIMARY KEY (mail)
);

CREATE TABLE Preference (
  nif_Student VARCHAR(15),
  id_ProjectOffer NUMERIC(10),
  lastchangeDate DATE,
  preference_order NUMERIC(2),
  CONSTRAINT Preference_pk PRIMARY KEY (nif_Student, id_ProjectOffer),
  CONSTRAINT Student_Preference_fk FOREIGN KEY (nif_Student)
  REFERENCES Student (nif) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT ProjectOffer_Preference_fk FOREIGN KEY (id_ProjectOffer)
  REFERENCES ProjectOffer (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Assignment (
  nif_Student VARCHAR(15),
  creationDate DATE,
  id_ProjectOffer NUMERIC(10),
  mail_Tutor VARCHAR(50),
  state VARCHAR (10),
  approvalDate DATE,
  rejectDate DATE,
  CONSTRAINT Assignment_pk PRIMARY KEY (nif_Student, creationDate),
  CONSTRAINT Student_Assignment_fk FOREIGN KEY (nif_Student)
  REFERENCES Student (nif) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT ProjectOffer_Assignment_fk FOREIGN KEY (id_ProjectOffer)
  REFERENCES ProjectOffer (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT Tutor_Assignment_fk FOREIGN KEY (mail_Tutor)
  REFERENCES Tutor (mail) ON UPDATE CASCADE ON DELETE CASCADE
);



--DROP TABLE Assigment, Preference, Tutor, Student, Review, ProjectOffer, Internship, Company;




CREATE TABLE users (
  id VARCHAR(15) not null,
  mail VARCHAR(50) not null,
  password VARCHAR(200) not null,
  role VARCHAR(50) not null,
  CONSTRAINT users_pk PRIMARY KEY (id)
);


--DROP TABLE Users;
