# librarymanagementsystem-SKPlus-Assesment

## Setup Database

Setup Database with code run in postgres command prompt:
```
CREATE USER lms_user WITH PASSWORD 'lms123';
CREATE DATABASE lmsdb OWNER lms_user;
GRANT ALL PRIVILEGES ON DATABASE lmsdb TO lms_user;
```
## Flow Chart Peminjaman Buku

<img src="https://github.com/hariantoputro/librarymanagementsystem-SKPlus-Assesment/blob/main/flowchart-lms.png"/>