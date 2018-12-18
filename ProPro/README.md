# ProPro

ProPro is a project which aims to provide the fastest and most accurate navigation data for free.
The software consists of a two-tier model where front- and backend are completely separated.

In the current implementation, only a prototype of the backend exists while a frontend is not implemented yet.

The backend parses prebuilt graph files and builds an efficient data structure to be able to find the most efficient path even with a less powerful machine.
The underlaying algorithm is based on a one-to-all Dijkstra path-finding algorithm and tries to find the nearest available node based on the user's current position.

## Techologies used

- Backend built on Java
- Build system implemented in Gradle
- No external dependencies

## Building the software
The runnable JAR is build by gradle. Open root directory of repository and run `gradle build`. Afterwards you can find **ProPro.jar** in `./build/libs/`


## Running the software

To run the software, locate **ProPro.jar** (by default in `./build/libs/`) and run the following command:
```
java -jar ProPro.jar -f <Filename>
```

\<Filename> is either the path to a graph file or the name of a graph file in current folder, e.g. `java -jar ProPro.jar -f germany.fmi`

The program starts to read header informations followed by parallel reading of node and edge data. As soon as the graph got sucessfully read, a main menu appears with the  following options:

##### (0) Start -> End
Generates the shortest path from a start to end node and stores results in a *DijkstraResult* Object 
##### (1) Node -> All

##### (2) Process Start -> End file

##### (3) Location -> Location

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