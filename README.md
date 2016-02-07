# DD Demo APP

[![Build Status](https://travis-ci.org/dd-stockholm/dd-demo-app.svg?branch=master)](https://travis-ci.org/dd-stockholm/dd-demo-app)

## Run locally

    ./gradlew run

Then go to *http://localhost:8080/*.


## Deploy

Builds and uploads latest shadowJar to http://dd-demo-app.marianna.se/.

*You must have your public ssh key on the server to be able to deploy*

    ./gradlew deploy


## Cucumber features

### Run

    ./gradlew cucumber
    ./gradlew cucumber_wip // For work in progress features

