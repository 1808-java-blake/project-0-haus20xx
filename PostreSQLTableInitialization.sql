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
	
--Create first admin user_id
INSERT INTO users(user_id,pass,balance,is_admin) VALUES ('housek','supersecret', 1250, true);
INSERT INTO transactions (user_serial, amount) VALUES (1,1250);
--First transaction listed manually, future transactions automated by program.