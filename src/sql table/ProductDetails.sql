create table productInfo(SerialNo serial not null,
					  ProductId varchar(20) primary key,
					 ProductName varchar(200) not null,
					Category varchar(100) not null,
					  Price int not null,
					  quantity int not null,
					  Status varchar(20) not null default 'available',
					  Quantitypurchased  int default 0,
					  QuantitiesLeft  int default 0,
					  check (status in('available' ,'not available'))
					 );
					 