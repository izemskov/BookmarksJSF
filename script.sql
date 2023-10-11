CREATE TABLE BOOKMARKS_FOLDER(ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    NAME VARCHAR(255),
    USERNAME VARCHAR(255),
    PARENT_ID INTEGER);

CREATE TABLE BOOKMARKS(
    ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    FOLDER_ID INTEGER NOT NULL,
    NAME VARCHAR(255),
    LINK VARCHAR(1000));