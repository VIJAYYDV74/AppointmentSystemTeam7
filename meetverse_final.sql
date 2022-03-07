create database meetverse;

use meetverse;

/*
 users table that contains the basic
 information of users while registering
 like firstname, lastname, email, password.
 email should be unique for each user.
*/
create table users(
	userid bigint not null auto_increment,
	firstname varchar(50) not null,
    lastname varchar(50) not null,
    email varchar(100),
    userpassword varchar(255),
    mobilenumber varchar(255),
    createdtime timestamp not null default current_timestamp,
    isblocked boolean default false,
    profileimage varchar(256),
    emailverified boolean default false,
    userrole varchar(50) default "USER",
    primary key (userid),
    unique (email)
);

/*
categories table maintains the list
of all categories of businesses like
hospital, saloon, restaurent etc,.. 
*/
create table categories(
	categoryid int not null,
    categoryname varchar(100),
    primary key(categoryid)
);

create table gendercategories(
	id int not null,
    gender varchar(30)
);

alter table gendercategories add primary key(id);

/*
Business address table stores the address of
the businesses
*/
create table businessaddresses(
	addressid bigint not null auto_increment,
    addressline1 varchar(255) not null,
    addressline2 varchar(255),
    postalcode varchar(6) not null,
    state varchar(32) not null,
    country varchar(32) not null,
    latitude decimal(8,6),
    langitude decimal(9,6),
    primary key (addressid)
);

create table images(
	id bigint not null auto_increment,
    businessid bigint not null,
    imagepath varchar(100),
    primary key(id),
    foreign key (businessid) references business(businessid)
);

/*
business table stores the information related
to business like the owner of business, category
and other details. 
*/
create table business(
	businessid bigint not null auto_increment,
    userid bigint not null,
    businessname varchar(255) not null,
    businesstitle varchar(255),
    businessdescription text,
    businesscategory int not null,
    businessnumber varchar(13),
    businessemail varchar(50),
    cancellationavailable boolean default false,
    slotduration int not null,
    genderid int not null default 1,
    emailverified boolean default false,
    createdtime timestamp not null default current_timestamp,
    businessaddress bigint,
    primary key(businessid),
    foreign key(userid) references users(userid),
    foreign key(businesscategory) references categories (categoryid),
    foreign key(businessaddress) references businessaddresses(addressid),
    foreign key(genderid) references gendercategories(id)
);


create table workinghours(
	id bigint not null auto_increment,
    businessid bigint not null,
    nameofday varchar(32),
    starthour time,
    endhour time,
    primary key (id),
    foreign key (businessid) references business(businessid)
);

/*
services table stores the meta data of
the business like what are the services
provided by the business to users.
Eg: KIMS is a business whose category is
hospital that has servivices like 
orthopaedic, neurology, cardio etc.,
*/
create table services(
	serviceid bigint not null auto_increment,
    businessid bigint not null,
    servicename varchar(100) not null,
    servicedesc text,
    serviceprice mediumint,
    primary key(serviceid),
    foreign key(businessid) references business(businessid)
);


/*
appointmemts table stores the information
related to the appointment like who booked the
appointment, to which business at which date,
on which date and beginning time and ending time
of the appointment. And also the cancellation
details if any and also stores the feedback given
by the user for the service they have booked. 
*/
create table payments(
	paymentid bigint not null auto_increment,
    paymentdate timestamp not null default current_timestamp,
    paymentmethod varchar(20) not null,
    amount 	long,
    primary key (paymentid)
);

alter table payments add billingid bigint;

alter table payments add foreign key (billingid) references billingdetails(id);

create table appointments(
	appointmentid bigint not null auto_increment,
    userid bigint not null,
    businessid bigint not null,
    typeofservice bigint not null,
    bookeddate timestamp not null default current_timestamp,
    dateofappointment date not null,
    begintime time not null,
    endtime time not null,
    totalprice int,
    activestatus varchar(20) default 'active',
    iscancelled bool,
    cancellationreason text default null,
    paymentid bigint,
    primary key (appointmentid),
    foreign key (userid) references users(userid),
    foreign key (businessid) references business(businessid),
    foreign key (typeofservice) references services(serviceid),
    foreign key (paymentid) references payments(paymentid)
);

create table comments(
	commentid bigint not null auto_increment,
    commentedto bigint,
    commentedby bigint not null,
    feedback text,
    rating smallint,
    createdtime timestamp default current_timestamp,
    primary key (commentid),
    foreign key (commentedto) references business(businessid),
    foreign key (commentedby) references users(userid)
);

create table notificationtypes(
	id int not null,
    notificationtype varchar(50) not null,
    primary key(id)
);

create table notifications(
	id bigint not null auto_increment,
    header varchar(255),
    message text,
    sender bigint,
    receiver bigint not null,
    createdtime timestamp default current_timestamp,
    state boolean default false,
    notificationtype int,
    primary key(id),
    foreign key (sender) references users(userid),
    foreign key (receiver) references users(userid),
    foreign key (notificationtype) references notificationtypes(id)
);

create table favourites(
	id bigint not null auto_increment,
    userid bigint not null,
    businessid bigint not null,
    primary key (id),
    foreign key (userid) references users(userid),
    foreign key (businessid) references business(businessid),
    unique (userid, businessid)
);

create table visitings(
	id bigint not null auto_increment,
    userid bigint,
    businessid bigint not null,
    searcheddatetime timestamp default current_timestamp,
    primary key(id),
    foreign key (userid) references users (userid),
    foreign key (businessid) references business(businessid)
);


create table likes(
	id bigint not null auto_increment,
    likedby bigint not null,
    likedto bigint not null,
    primary key (id),
    foreign key (likedby) references users(userid),
    foreign key (likedto) references business(businessid),
    unique (likedby, likedto)
);


alter table business add isblocked boolean default false;

alter table services add isblocked boolean default false;

alter table categories add isblocked boolean default false;

alter table images add isblocked boolean default false;

create table billingdetails(
	id bigint not null auto_increment,
    country varchar(50),
    firstname varchar(100),
    lastname varchar(100),
    address text,
    city varchar(128),
    state varchar(100),
    postalcode varchar(10),
    billingemailid varchar(100),
    billingmobilenumber varchar(20),
    primary key (id)
);






select * from users;

insert into categories(categoryid, categoryname) values (1, "hospitals"), (2, "hotels"),(3, "saloons");

insert into notificationtypes (id, notificationtype) values (1, "appointment"), (2, "payments"), (3, "searching");

insert into gendercategories(id, gender) values (3, "both");

select * from categories;

select * from business;

select * from workinghours;

select * from comments;

select * from services;

select * from appointments;

select * from payments;

select * from notificationtypes;

select * from notifications;

select * from gendercategories;

select * from visitings;

alter table users add userrole varchar(50) default "USER";











