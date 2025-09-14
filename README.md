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

## API
### Auth API
```
POST
/auth/login

GET
/auth/logout
```

### Buku API
```
POST
/buku/edit

POST
/buku/find-all

GET
/buku/find-by-id/{id}

POST
/buku/save
```

### File API
```
POST
/file/upload

GET
/file/view
```

### Peminjaman API
```
POST
/peminjaman/find-all

GET
/peminjaman/find-by-id/{id}

POST
/peminjaman/kembali-buku

POST
/peminjaman/pinjam-buku
```
### User API
```
DELETE
/user/delete/{id}

POST
/user/edit

POST
/user/find-all

GET
/user/find-by-id/{id}

POST
/user/save
```

## Documentation
[Swagger](https://swagger.io/) for documentation
```
http://localhost:8081/swagger-ui/index.html#
```