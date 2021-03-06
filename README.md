# ProPro

ProPro is a project which aims to provide the fastest and most accurate navigation data for free.
The software consists of a two-tier model where front- and backend are completely separated.

In the current implementation, only a prototype of the backend exists while a frontend is not implemented yet.

The backend parses prebuilt graph files and builds an efficient data structure to be able to find the most efficient path even with a less powerful machine.
The underlaying algorithm is based on a one-to-all Dijkstra path-finding algorithm and tries to find the nearest available node based on the user's current position.

## Techologies used

- Backend built on Java
- Build system implemented in Gradle
- External dependencies
  - Gson (not used yet)
  - JUnit 4.12
  - All external dependecies are downloaded automatically from gradle while building

## Building the software
The runnable JAR is build by gradle. To install the newest version of gradle we prepared a skript to run. Open root directory of repository and run `sudo sh prepareProPro.sh` this will install gradle, the needed java version and build the project.
While running the skript will ask you a few times to confirm the installation of the parts type yes or press enter if you are asked to.
At the end it may ask you "What would you like to do about menu.lst?" then just type keep and you are done with preparing and building.
Afterwards you can find **ProPro.jar** in `./build/libs/`

## How To Frontend
[See here](https://github.com/TheMrPhantom/Programmierprojekt/blob/master/README_WEB.md)

## How To Backend standalone (deprecated)

To run the software, locate **ProPro.jar** (by default in `./build/libs/`) and run the following command:
```
java -Xmx5g -jar ProPro.jar -f <Filename>
```

\<Filename> is either the path to a graph file or the name of a graph file in current folder, e.g. `java -Xmx5g -jar ProPro.jar -f germany.fmi`

The program starts to read header informations followed by parallel reading of node and edge data. As soon as the graph got sucessfully read, a main menu appears with the  following options:

##### (0) Start -> End
Generates the shortest path from a start to end node and returns the path length in nodes and meters plus the needed time to calculate
##### (1) Node -> All
Generates the shortest path from a start to all nodes and returns the needed time to calculate. The program will ask for a node id ot which you want to know the shortest distance
##### (2) Process Start -> End file
Asks you for a .que file and generates a .sol file
##### (3) Location -> Location
Asks for coordinates and gives you the index of the nearest node to this location
##### (4) Exit
Closes the program

#### Graph Files
A graph file consists of a header followed by node and edge data. There are no empty lines between these three segments.

##### Header
The header has seven lines of code. For our program, the first five lines get skipped and only the last two lines are considered.  Line 6 is the number of nodes, Line 7 is the number of edges.

Example: 
```
# Id : 0
# Timestamp : 1508267560
# Type : maxspeed
# Revision : 1

1132113
2292887
```
##### Node Data
Each node data consists of five informations separated by a whitespace. These informations are nodeID, nodeID2, latitute, longitude and elevation, even though we are ignoring nodeID2 and elevation.

Each node has its own line.

Example:

```
0 163354 48.66743380000000485 9.24459110000000095 0
1 163355 48.66947440000000569 9.24326250000000016 0
2 163358 48.66619320000000215 9.25155360000000115 0
```
##### Edge Data
Each edge data consists of five informations separated by a whitespace. These informations are sourceID, targetID, cost, type and maxspeed, even though we are ignoring type and maxspeed.

Each edge has its own line.


Example: 
```
0 1104366 55 5 80
0 1104376 66 5 80
1 1104566 74 5 80
1 1104589 104 5 80
2 1104289 93 5 80
```
