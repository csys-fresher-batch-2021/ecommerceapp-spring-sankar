create table UserRegister(User_Id serial primary key,
						 fullName varchar(100) not null,
						 userName  varchar(20) not null,
						 email varchar(100) not null,
						 mobileNumber bigint not null,
						  password varchar(100) not null,
						  address varchar(200),
						  Security_Question varchar(100) not null,
						  Security_answer varchar(100) not null,
						  created_date date not null,
						  modified_date date not null,
						  unique(email),
						  unique(mobileNumber));
						  
						  select * from UserRegister;