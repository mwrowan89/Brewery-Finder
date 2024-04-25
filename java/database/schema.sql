BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, breweries, beers, reviews, favorite_breweries CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE breweries (
    brewery_id SERIAL,
    name VARCHAR(50),
    phone varchar(255),
    address varchar(255),
	zip_code varchar(255),
	state varchar(255),
	city varchar(255),
    history TEXT,
    hours varchar(255),
    image varchar(55555),
	website_url varchar(255),
	rating DECIMAL(3,1),
	CONSTRAINT PK_brewery PRIMARY KEY (brewery_id)
);

CREATE TABLE beers (
	beer_id SERIAL,
	name VARCHAR(500),
	brewery_id INT,
	type VARCHAR(500),
	abv DECIMAL(3, 1),
	ibu INT,
	description TEXT,
	image VARCHAR(255),
	show BOOLEAN DEFAULT true,
	CONSTRAINT PK_beer PRIMARY KEY (beer_id),
	FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)
);

CREATE TABLE reviews (
	review_id SERIAL,
	beer_id INT,
	user_id INT,
	rating DOUBLE PRECISION,
	review TEXT,
	CONSTRAINT PK_review PRIMARY KEY (review_id),
	FOREIGN KEY (beer_id) REFERENCES beers(beer_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE favorite_breweries(
    user_id INT,
    brewery_id INT,
    notes TEXT,
    CONSTRAINT PK_favorite_breweries PRIMARY KEY (user_id, brewery_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)
);


COMMIT TRANSACTION;
