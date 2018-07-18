[![Build Status](https://travis-ci.org/ilredeitopi/Basher.svg?branch=master)](https://travis-ci.org/ilredeitopi/Basher)

# Basher
A command line social network.

Implementation of the programming kata described in: [xpeppers/social_networking_kata](https://github.com/xpeppers/social_networking_kata).

## Available Commands

By default Basher reads a command from the standard input and prints its output to the standard output.

A command is in the form `username [action [parameter]]`.

Basher provides the following commands:

- post: (e.g.:`ada -> hello basher!`) creates a new message posted by the user _ada_.
- read: (e.g.:`ada`) shows the list of messages posted by the user _ada_.
- follow: (e.g.:`ada follow bob`) allows _ada_ to follow _bob_'s messages.
- wall: (e.g.:`ada wall`) prints a list of _ada_'s messages, together with the messages of all the users she is following.
- users: (e.g.:`ada users`) prints a list of users subscribed to the system.
- reset: (e.g.:`ada reset`) deletes all the data from the current database.
- quit: (e.g.:`ada quit`) terminates the application.

You can quit the application by either using the `quit` command or hitting `Ctrl/Cmd-D`.

## Run Basher

### Build a Docker Container (Recommended)

You can build and run Basher using _docker-compose_.
You need [Docker](https://www.docker.com/) installed on your machine.
From the project directory run:
```
docker-compose run app
```

This will setup two containers: one running the Basher application and one running the MongoDB database.
You can stop and delete the containers by running the command:
```
docker-compose down
```

### Build Basher Locally

Note that Basher is build for Java 10. You will need a Java 10 JDK installed in your machine to build Basher locally.

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
./bin/basher
```

## Select the Database

Basher comes with the support for in-memory and MongoDB databases.

By default Basher is configured to use in-memory database to minimize the install impact, while it uses MongoDB when run in a Docker container.
You can explicitly select to use MongoDB by setting the environment variable `BASHER_DATABASE` to `'mongo'` before starting the application.
```
export BASHER_DB="mongo"
```
Moreover, you can specify the host address and port used by MongoDB by modifying the environment variable `BASHER_MONGO_HOST`. The default is `localhost`.

```
export BASHER_MONGO_HOST="mongo"
```

If you wish to run Basher locally with a MongoDB database, we suggest you to run it with the following command:
```
./bin/basher 2>/dev/null
```
The standard error is redirected to /dev/null to suppress the output of the overly verbose MongoDB driver.
