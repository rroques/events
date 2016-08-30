# Events

[![Build Status](https://travis-ci.org/rroques/events.svg?branch=master)](https://travis-ci.org/rroques/events)
[![Dependency Status](https://dependencyci.com/github/rroques/events/badge)](https://dependencyci.com/github/rroques/events)
[![Issue Count](https://codeclimate.com/github/rroques/events/badges/issue_count.svg)](https://codeclimate.com/github/rroques/events)

A sample Spring Boot application to serve as bait for the could.

# CloudFoundry

```
cf push -p build/libs/events.jar

cf create-service elephantsql turtle events-postgres
cf bind-service events events-postgres

```
