# Introduction

Welcome to the Garden Service repository of the Plant Project, where software meets soil and seeds. This repository encompasses the components that allow users to catalog, track, and manage their plants, providing a digital reflection of their real-world garden.

The Garden Service lies at the heart of our system, orchestrating interactions between the user, the plants, and the various hardware devices. It's here where users can create and manage their garden entities, define schedules, and respond to the feedback from sensors.

This repository comprises the source code and documentation for the Garden Microservice, which handles the registration and control of individual gardens and their contents. It also interfaces with the Hardware Service for the operation of hardware controllers, lighting systems, water pumps, temperature controllers, and more.

# Installation Instructions

## Garden Service Client

1. **Clone the Garden Service Client repository:**

Open your terminal and type in the following command:

```bash
git clone https://github.com/UrbanJungleTech/GardenServiceClient.git
```

2. **Build the Garden Service Client:**

After successfully cloning the client repository, you need to build the client. Navigate into the GardenServiceClient directory:

```bash
cd GardenServiceClient
```

Then use Maven to build and install the client library in your local repository:

```bash
mvn clean install
```

This command cleans the target directory, builds your project, and installs the library into your local Maven repository.

## Garden Service

Clone the Garden Service repository:

Now that the client is built and installed, you can proceed to clone the Garden Service repository. Navigate back to your root directory (or whichever directory you wish to clone the repo into) and run:

```bash
git clone https://github.com/UrbanJungleTech/GardenService.git
```

Build the Garden Service:

Next, navigate into the GardenService directory:

```bash
cd GardenService
```

And build the Garden Service using Maven:

```bash
mvn clean install
```

Run the Garden Service:

After a successful build, you can run the Garden Service using the following command:

```bash
java -jar target/plants-0.0.1-SNAPSHOT.jar
```
