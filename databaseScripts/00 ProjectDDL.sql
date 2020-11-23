DROP DATABASE IF EXISTS Project304;
CREATE DATABASE Project304;
USE Project304;

SET FOREIGN_KEY_CHECKS = 0;
#-----

#---------------
# PostalCode
#---------------

DROP TABLE IF EXISTS PostalCode CASCADE;

CREATE TABLE PostalCode (
    PostalCode CHAR(7) NOT NULL,
    City VARCHAR(50) NOT NULL,
    Province CHAR(2) NOT NULL,
    PRIMARY KEY (PostalCode)
);

#---------------
# AdvisoryRating
#---------------

DROP TABLE IF EXISTS AdvisoryRating CASCADE;

CREATE TABLE AdvisoryRating (
    AdvisoryRatingCode CHAR(5) NOT NULL,
    Description VARCHAR(25) ,
    PRIMARY KEY (AdvisoryRatingCode)
);

#---------------
# CapacityType
#---------------

DROP TABLE IF EXISTS CapacityType CASCADE;

CREATE TABLE CapacityType (
    CapacityTypeCode CHAR(5) NOT NULL,
    Description VARCHAR(25) NOT NULL,
    PRIMARY KEY (CapacityTypeCode)
);

#---------------
# ShowingStatus
#---------------

DROP TABLE IF EXISTS ShowingStatus CASCADE;

CREATE TABLE ShowingStatus (
    ShowingStatusCode CHAR(5) NOT NULL,
    Description VARCHAR(25) NOT NULL,
    PRIMARY KEY (ShowingStatusCode,Description)
);

#---------------
# Venue
#---------------

DROP TABLE IF EXISTS Venue CASCADE;

CREATE TABLE Venue (
    VenueCode CHAR(10) NOT NULL,
    Name VARCHAR(50) NOT NULL,
    Street VARCHAR(100) NOT NULL,
    PostalCode CHAR(7) NOT NULL,
    PRIMARY KEY (VenueCode),
    FOREIGN KEY  (PostalCode)
        REFERENCES PostalCode (PostalCode)
            ON DELETE CASCADE
);

#---------------
# Employee
#---------------

DROP TABLE IF EXISTS Employee CASCADE;

CREATE TABLE Employee (
    EmployeeID INTEGER NOT NULL,
    VenueCode CHAR(10) NOT NULL,
    GivenName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    PRIMARY KEY (EmployeeID,VenueCode),
    FOREIGN KEY  (VenueCode)
        REFERENCES Venue (VenueCode)
            ON DELETE CASCADE
);

#---------------
# Role
#---------------

DROP TABLE IF EXISTS Role CASCADE;

CREATE TABLE Role (
    RoleCode CHAR(10) NOT NULL,
    RoleName VARCHAR(25) NOT NULL,
    IsCast BOOLEAN ,
    PRIMARY KEY (RoleCode)
);

#---------------
# Artist
#---------------

DROP TABLE IF EXISTS Artist CASCADE;

CREATE TABLE Artist (
    ArtistID INTEGER NOT NULL,
    GivenName VARCHAR(100) ,
    LastName VARCHAR(100) NOT NULL,
    PRIMARY KEY (ArtistID)
);

#---------------
# Movie
#---------------

DROP TABLE IF EXISTS Movie CASCADE;

CREATE TABLE Movie (
    MovieID INTEGER NOT NULL,
    Title VARCHAR(255) NOT NULL,
    AdvisoryRatingCode CHAR(5) ,
    PRIMARY KEY (MovieID),
    FOREIGN KEY  (AdvisoryRatingCode)
        REFERENCES AdvisoryRating (AdvisoryRatingCode)
            ON DELETE CASCADE
);

#---------------
# MovieCastCrew
#---------------

DROP TABLE IF EXISTS MovieCastCrew CASCADE;

CREATE TABLE MovieCastCrew (
    RoleCode CHAR(10) NOT NULL,
    ArtistID INTEGER NOT NULL,
    MovieID INTEGER NOT NULL,
    PartPlayed VARCHAR(100) ,
    Position VARCHAR(100) ,
    PRIMARY KEY (RoleCode,ArtistID,MovieID),
    FOREIGN KEY  (RoleCode)
        REFERENCES Role (RoleCode)
            ON DELETE CASCADE,
    FOREIGN KEY  (ArtistID)
        REFERENCES Artist (ArtistID)
            ON DELETE CASCADE,
    FOREIGN KEY  (MovieID)
        REFERENCES Movie (MovieID)
            ON DELETE CASCADE
);

#---------------
# Theatre
#---------------

DROP TABLE IF EXISTS Theatre CASCADE;

CREATE TABLE Theatre (
    TheatreID INTEGER NOT NULL,
    VenueCode CHAR(10) NOT NULL,
    DisplayName CHAR(25) NOT NULL,
    MapRows INTEGER ,
    MapColumns INTEGER ,
    PRIMARY KEY (TheatreID),
    FOREIGN KEY  (VenueCode)
        REFERENCES Venue (VenueCode)
            ON DELETE CASCADE
);

#---------------
# Capacity
#---------------

DROP TABLE IF EXISTS Capacity CASCADE;

CREATE TABLE Capacity (
    TheatreID INTEGER NOT NULL,
    CapacityTypeCode CHAR(5) NOT NULL,
    QuantityAvailable INTEGER NOT NULL,
    PRIMARY KEY (TheatreID,CapacityTypeCode),
    FOREIGN KEY  (TheatreID)
        REFERENCES Theatre (TheatreID)
            ON DELETE CASCADE,
    FOREIGN KEY  (CapacityTypeCode)
        REFERENCES CapacityType (CapacityTypeCode)
            ON DELETE CASCADE
);

#---------------
# Seat
#---------------

DROP TABLE IF EXISTS Seat CASCADE;

CREATE TABLE Seat (
    SeatID INTEGER ,
    TheatreID INTEGER NOT NULL,
    CapacityTypeCode CHAR(5) NOT NULL,
    RowCode CHAR(10) NOT NULL,
    SeatCode CHAR(10) NOT NULL,
    MapRow  INTEGER ,
    MapColumn INTEGER ,
    PRIMARY KEY (SeatID),
    FOREIGN KEY  (TheatreID)
        REFERENCES Theatre (TheatreID)
            ON DELETE CASCADE,
    FOREIGN KEY  (CapacityTypeCode)
        REFERENCES Capacity (CapacityTypeCode)
            ON DELETE CASCADE,
    UNIQUE (SeatID)
);

#---------------
# Showing
#---------------

DROP TABLE IF EXISTS Showing CASCADE;

CREATE TABLE Showing (
    ShowingID INTEGER NOT NULL,
    TheatreID INTEGER NOT NULL,
    MovieID INTEGER NOT NULL,
    StartDtm DATETIME NOT NULL,
    EndDtm DATETIME ,
    IsReservedSeating BOOLEAN ,
    ShowingStatusCode CHAR(5) ,
    PRIMARY KEY (ShowingID),
    FOREIGN KEY  (TheatreID)
        REFERENCES Theatre (TheatreID)
            ON DELETE CASCADE,
    FOREIGN KEY  (MovieID)
        REFERENCES Movie (MovieID)
            ON DELETE CASCADE,
    FOREIGN KEY  (ShowingStatusCode)
        REFERENCES ShowingStatus (ShowingStatusCode)
            ON DELETE CASCADE,
    UNIQUE (ShowingID)
);

#---------------
# UsedCapacity
#---------------

DROP TABLE IF EXISTS UsedCapacity CASCADE;

CREATE TABLE UsedCapacity (
    ShowingID INTEGER NOT NULL,
    TheatreID INTEGER NOT NULL,
    CapacityTypeCode CHAR(5) NOT NULL,
    QuantityUsed INTEGER NOT NULL,
    PRIMARY KEY (ShowingID,TheatreID,CapacityTypeCode),
    FOREIGN KEY  (ShowingID)
        REFERENCES Showing (ShowingID)
            ON DELETE CASCADE,
    FOREIGN KEY  (TheatreID,CapacityTypeCode)
        REFERENCES Capacity (TheatreID,CapacityTypeCode)
            ON DELETE CASCADE,
    UNIQUE (ShowingID,TheatreID,CapacityTypeCode)
);

#---------------
# UsedSeat
#---------------

DROP TABLE IF EXISTS UsedSeat CASCADE;

CREATE TABLE UsedSeat (
    ShowingID INTEGER NOT NULL,
    CapacityTypeCode CHAR(5) NOT NULL,
    TheatreID INTEGER NOT NULL,
    SeatID INTEGER NOT NULL,
    PRIMARY KEY (ShowingID,CapacityTypeCode,TheatreID,SeatID),
    FOREIGN KEY  (ShowingID,TheatreID,CapacityTypeCode)
        REFERENCES UsedCapacity (ShowingID,TheatreID,CapacityTypeCode)
            ON DELETE CASCADE,
    FOREIGN KEY  (SeatID)
        REFERENCES Seat (SeatID)
            ON DELETE CASCADE
);

#-----
# Turn all foreign key checking back ON
#-----
SET FOREIGN_KEY_CHECKS = 1;
#-----

