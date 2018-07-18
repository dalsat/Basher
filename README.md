[![Build Status](https://travis-ci.org/ilredeitopi/Basher.svg?branch=master)](https://travis-ci.org/ilredeitopi/Basher)

# Basher
A command line social network.

Implementation of the programming kata described in: [xpeppers/social_networking_kata](https://github.com/xpeppers/social_networking_kata)

## Available Commands

- read
- post
- follow
- wall
- users
- reset


## Run Basher

### In a Docker Container (Recommended)
TODO: mongo compose file

### Build Basher Locally
Build the project with:
```
./gradlew assemble
```

Then extract the .tar (or .zip) archive in build/distributions:
```
cd build/distributions
tar -xf basher-*.tar
cd basher-*
```

Finally, run the startup script:
```
./bin/basher 2>/dev/null
```
The standard error is redirected to /dev/null to suppress the output of the overly verbose MongoDB driver.

## Select the Database

Basher comes with the support for in-memory and MongoDB databases.

By default Basher is configured to use in-memory database to minimize the install impact, while it uses MongoDB when run in a Docker container.
You can explicitly select to use MongoDB by setting the environment variable `BASHER_DATABASE` to `'mongo'` before starting the application.
```
export BASHER_DB="mongo"
```
