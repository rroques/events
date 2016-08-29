# Events

[![Build Status](https://travis-ci.org/rroques/events.svg?branch=master)](https://travis-ci.org/rroques/events)
[![Dependency Status](https://dependencyci.com/github/rroques/events/badge)](https://dependencyci.com/github/rroques/events)

A sample Spring Boot application to serve as bait for the could.

# CloudFoundry

```
cf create-service elephantsql turtle events-postgres
cf bind-service events events-postgres

cf push -p build/libs/events.jar
```
