# Zesco Issue Tracker Web backend

## Description

This is the backtend of the app. It helps Zesco to keep track of issues with their equipments and the current statuses of the equipments

## Technologies used

- [java openjdk](https://openjdk.java.net/)
- [spring](https://spring.io/)
- [junit](https://junit.org/junit5/)
- [sqlite jdbc](https://github.com/xerial/sqlite-jdbc/)


## Installation Guide

> NOTE: The app doesn't follow the best security practice.

- Clone the repo

```bash
git clone https://github.com/favourMusenga/isssue_tracker_web.git
```

- Make sure you have maven installed on your workspace. To check if maven is installed, run the following command in the terminal: 

```bash
mvn -version
```

- Open the project in your IDE of choice. This was tested using IntelliJ IDEA 2022.1.1 (Ultimate Edition)

- Make all the dependencies are installed before running the project.

- Run the project.

- Server should be running on localhost:8080


## TODO

- [x] Initial Structure
- [x] Adding roles of users
- [x] Adding users
- [x] Getting all users
- [x] User Authentication
- [x] Adding Location
- [x] Getting all location
- [x] Adding Equipment
- [x] Getting all equipment
- [x] Adding Status
- [x] Getting all status
- [x] Adding Issues
- [x] Getting all Issues
- [ ] (Optional) Generating report
