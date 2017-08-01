create table employees(
	EMPLOYEE_ID       int primary key,
	FIRST_NAME        VARCHAR2(20),
	LAST_NAME         VARCHAR2(25),
	EMAIL             VARCHAR2(25),
	PHONE_NUMBER      VARCHAR2(20),
	HIRE_DATE         DATE,
	SALARY            NUMBER(8,2)
);
create sequence empSeq;