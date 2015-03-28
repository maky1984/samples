Notifier service
=============================

REQUIREMENTS
-----------------------------
auth_tokens table:
id BIGINT;
userid INT;
deviceid VARCHAR(255);
pushtoken VARCHAR(255);
devide_platform SMALLINT;

users table:
id BIGINT;
firstname VARCHAR(100);

device_platforms table:
id SMALLINT;
name VARCHAR(100);

User table has about 100 000 users, every user can have more than one device, so auth_tokens table will have size more than 100 000 rows as well.

Marketing team wants to send pushes to all users with ‘some text’. And they want to do it several times a week by some scheduled timetable. So for example send on Monday at 09:00am pushes with text ‘hello world’, on Tuesday 6:00pm ‘hello, how are you?’ etc. Max count of scheduled tasks will not be more than 10.



COMPILE
------------------------------
Maven tool is used for packaging this application.
