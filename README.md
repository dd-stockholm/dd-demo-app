# DD Demo APP

[![Build Status](https://travis-ci.org/dd-stockholm/dd-demo-app.svg?branch=master)](https://travis-ci.org/dd-stockholm/dd-demo-app)

## About

The goal for this project is to provide an app where actual "Riksdagsfrågor" can be voted on and delegates can be selected to handle 
voting for questions the individual aren't interested to participate in.

It is important to understand that this is only meant as a demonstration on what can be done to support "Flytande demokrati" with
today's technology, not an actual voting app that in any way reflects the participants real views on matters at hand.

## Scope

The original scope was that the app would support voting and delegating and that users would use their Facebook open id to login to the app.
Data from riksdagen would be imported into votable subjects together with information needed to understand what a 'yes' or 'no' would mean.
Our own database would hold these questions together with available delegates and also store the votes that would be gathered.

Some basic statistics would then be presented to users. Data would be anonymized in this statistics.

## Security

To get people to share their views by voting on real subjects from Riksdagen, a guarantee is needed so that this information doesn't leak or is shared to other parties.
As mentioned above this is only a demo app and the intention with the current scope was never to work for real, therefore no particular security of voting was part of the scope.
In the long run though, work would be done to protect this information to guarantee the safety of the collected data so voters could feel safe and secure with the information 
they provide by voting in the app.

## Technical aspects

The application is written in Java8 and AngularJS 1.4.8. Both the web part and the backend API is bundled together when building the project.
But with this clear separation between backend and frontend other things may also communicate with the backend such as a native mobile app.
The backend is build with DropWizard 0.9.1 which provide a simple REST api that accepts and return json. 

### Database

The database is bundled together with the app which means that all data is hold in memory and is wiped out when application is stopped.
This is only because it speeds upp development and no requirement to install a database to just test the app is needed.
When the project begins to reach the finish line though, it will be replaced with PostgreSQL or some other good sql-database. 
This should be a simple procedure with only some dependency and configuration changes.

### Run locally

    ./gradlew run

DropWizard prints the available REST routes in the console
The AngularJS web is then accessible on *http://localhost:8080/*.

### Documentation by Swagger

While the application is up and running you may navigate to *http://localhost:8080/api/swagger* where the API is documented and also testable.
Great huh!? :)

### Deploy

Builds and uploads latest [shadowJar](https://github.com/johnrengelman/shadow) to http://dd-demo-app.marianna.se/.
Everything except the DropWizard config is bundled together during deploy and uploaded to the server we've set in place to demonstrate it.

*You must have your public ssh key on the server to be able to deploy*

    ./gradlew deploy


### Cucumber features

#### Run

    ./gradlew cucumber
    ./gradlew cucumber_wip // For work in progress features

