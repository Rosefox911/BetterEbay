CREATE TABLE Users(
username VARCHAR(15) PRIMARY KEY,
password VARCHAR(20) NOT NULL,
firstname VARCHAR(30),
lastname VARCHAR(30),
email VARCHAR(30),
avatar VARCHAR(30),
bio VARCHAR(400)
);

CREATE TABLE Likes(
username VARCHAR(15),
sku VARCHAR(15) NOT NULL,
dateliked TIMESTAMP NOT NULL,
FOREIGN KEY(username) references Users(username) on delete cascade,
PRIMARY KEY (username, sku)
);


CREATE TABLE Follows(
userfollowing VARCHAR(15),
userfollowed VARCHAR(15), 
datefollowed TIMESTAMP NOT NULL,
FOREIGN KEY(userfollowing) references Users(username) on delete cascade,
FOREIGN KEY(userfollowed) references Users(username) on delete cascade,
PRIMARY KEY(userfollowing, userfollowed),
CHECK(userfollowing != userfollowed)
);

CREATE TABLE Comments(
commentid INT PRIMARY KEY AUTO_INCREMENT,
commenter VARCHAR(15),
FOREIGN KEY(commenter) references Users(username) on delete cascade,
sku VARCHAR(15) NOT NULL,
date TIMESTAMP NOT NULL,
content VARCHAR(400)
);


alter table Users add sex ENUM('Male', 'Female') default 'Male';
alter table Comments add title varchar(200);