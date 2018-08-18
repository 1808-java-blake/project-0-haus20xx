SET SCHEMA 'bank';
DROP TABLE users;
DROP TABLE transactions;

CREATE TABLE users(
	user_id VARCHAR(24),
	pass VARCHAR(64),
	balance Numeric(20,2),
	is_admin BOOLEAN,
	user_serial SERIAL PRIMARY KEY);
	
CREATE TABLE transactions(
	transaction_serial SERIAL PRIMARY KEY,
	user_serial INTEGER REFERENCES users(user_serial),
	amount NUMERIC(20,2)
	);