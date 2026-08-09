# Diplomna Backend

Spring Boot REST API developed for my Bachelor's Degree project at the Technical University of Sofia.

It provides backend functionality for the accompanying Android application.

## Tech Stack

* Java 17
* Spring Boot
* Spring Web
* Spring Data MongoDB
* Spring Security
* MongoDB
* Gradle
* JUnit

## Features

* User registration and login
* Password encoding
* User profiles and profile pictures
* Comments
* Ratings
* Reservations
* REST API communication with the Android client

## Run Locally

Clone the repository:

```bash
git clone https://github.com/Av2231/Diplomna-Backend.git
cd Diplomna-Backend
```

Make sure MongoDB is running, then start the backend:

```bash
./gradlew bootRun
```

Windows:

```powershell
gradlew.bat bootRun
```

## Build & Test

```bash
./gradlew build
./gradlew test
```

## Android Client

The Android application using this API is available here:

https://github.com/Av2231/Diplomna

## Author

**Alexander Avdjiev**

Bachelor of Computer Systems and Technologies
Technical University of Sofia

GitHub: https://github.com/Av2231
