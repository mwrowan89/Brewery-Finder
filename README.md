# Brewery Finder
​
This directory contains all of the starter files for your project.
​
## Project setup
​
The first thing you'll need to do is to download any dependencies by running this command:
​
```
npm install
```
​
Next take a moment to review the `.env` file that's located in the root of the project. You can store environment variables that you want to use throughout your application in this file. When you open it, it'll look like this:
​
```
VITE_REMOTE_API=http://localhost:9000
```
​
*Note:* The Java Spring Boot application is configured to run on port 9000 instead of 8080.
​
Start your Vue application with the following command:
​
```
npm run dev
```
​
## Database

Inside the `<project-root>/database/` directory, you'll find an executable Bash script (`.sh` file) and several SQL scripts (`.sql` files). These can be used to build and rebuild a PostgreSQL database for the capstone project.

From a terminal session, execute the following commands:

```
cd <project-root>/database/
./create.sh
```

This Bash script drops the existing database, if necessary, creates a new database named `final_capstone`, and runs the various SQL scripts in the correct order. You don't need to modify the Bash script unless you want to change the database name.

Each SQL script has a specific purpose as described here:

| File Name | Description |
| --------- | ----------- |
| `data.sql` | This script populates the database with any static setup data or test/demo data. The project team should modify this script. |
| `dropdb.sql` | This script destroys the database so that it can be recreated. It drops the database and associated users. The project team shouldn't have to modify this script. |
| `schema.sql` | This script creates all of the database objects, such as tables and sequences. The project team should modify this script. |
| `user.sql` | This script creates the database application users and grants them the appropriate privileges. The project team shouldn't have to modify this script. <br /> See the next section for more information on these users. |


### Datasource

A Datasource has been configured for you in `/src/resources/application.properties`. It connects to the database using the `capstone_appuser` database user. You can change the name of this database if you want, but remember to change it here and in the `create.sh` script in the database folder:

```
# datasource connection properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ale_atlas
spring.datasource.name=ale_atlas
spring.datasource.username=postgres
spring.datasource.password=postgres1
```


## Spring Boot
Note: Spring Boot has been configured to run on port `9000` for this project.