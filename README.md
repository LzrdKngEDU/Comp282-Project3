project 3
Aaron and Jason


COMP 282 Project 3: Graphs
Idea: In the future, space travel is commonplace and mankind has colonized different planets.
You are tasked with creating a program according to the specifications provided below. You
should provide three java files and a driver. The three java files should correspond to three
classes called “Patrol,” “Travel,” and “Tour.” These should follow the specifications provided
below.
Your program should be able to read in and process the graph data contained in the “patrol.txt”
and “travel.txt” files, described below.
There can be more than 8 planets represented in the patrol file and travel file. There can be a
weighted edge between a planet and any other planet. For example, if there are eight planets,
each planet can have a maximum degree of 7 if undirected (patrol), and a maximum degree of 7
for outgoing and incoming edges if directed (travel).
Note: The examples below are simple and is meant to only show how the program should run.
Your implementation will be tested with bigger graphs. I should be able to use different patrol
and travel files to test your program.
Secure Paths:
Space piracy have become rampant. The inhabitants of different planets have demanded more
security for trade ships that travel to different planets. Providing a security patrol between planets
is costly. You are tasked with providing a secure path from a planet to any other planet while
minimizing cost. You are provided a list of weighted-undirected edges that represents the cost of
a security patrol between a planet to another planet in a text file (ascii) called “patrol”.
patrol.txt
In the patrol file, each undirected edge is provided in a separate line with the two neighboring
planets listed first followed by their cost (delimited by a space). For example, the travel file can
list the following undirected weighted edges (all positive integers).
Earth Mars 50
Earth Saturn 500
Venus Saturn 80
Earth Venus 30
The edge (Earth, Mars, 50) is the same as (Mars, Earth, 50)in the patrol file.
Patrol.java:
The Patrol java file must correspond to the Patrol class that has the following methods: (1) a no
argument constructor and (2) public void patrolEdges(). You should implement an
algorithm discussed in class to provide a solution. You can have other methods in the Patrol
class.
Sample Run:
Patrol myPatrol = new Patrol();
myPatrol.patrolEdges()
Should print:
Total Cost: 160
(Earth, Venus, 30) (Earth, Mars, 50) (Venus, Saturn, 80)
Quick Travel:
To respond to planetary emergencies, you should be able to find the quickest route to space travel
from a planet (start) to any other planet. The data for travel time between planets is given as a
weighted-directed graph in the travel.txt file.
travel.txt:
In the travel file, each edge is provided in a separate line with the parent and child planets listed
first followed by their travel time (delimited by a space). For example, the travel file can list the
following directed-weighted edges (all positive integers).
Earth Mars 50
Mars Earth 53
Earth Saturn 500
Saturn Earth 600
Saturn Mars 300
Venus Saturn 80
Earth Venus 30
The edge (Earth, Mars, 50) means that it takes 50 travel time to get from Earth directly to
Mars. The edge (Mars, Earth, 53)means that it takes 53 travel time to get from Mars to
Earth.
Travel.java:
The Travel.java file must correspond to the Travel class that has the following methods: (1) a no
argument constructor and (2) public void quickTravel(). You can have other methods
in the Travel class. You should implement an algorithm discussed in class to provide a solution.
Sample Run:
Travel myTravel = new Travel();
myTravel.quickTravel(Earth, Saturn);//Earth as starting planet, Saturn as the destination
Should print:
Path: Earth, Venus, Saturn
Total Travel Time: 110
Tour Planets:
Occasionally, would need to survey all the planets from the planet that you are currently on (you
can have different planets as a starting point). You are tasked with finding a path that would visit
all the planets once before returning to the starting planet (traveling salesman problem). Your
solution should provide the tour with the least travel time. Provide the path and the path cost for
the minimum cost tour that you find. The travel times between planets used in this case is the
same data as in the travel file.
Tour.java:
The Tour.java file must correspond to the Tour class that has the following methods: (1) a no
argument constructor and (2) public void quickTour(). You can have other methods in
the Tour class.
Sample Run:
Tour myTour = new Tour();
myTour.quickTour(Earth);//Earth as starting planet
Path: Earth, Venus, Saturn, Mars, Earth
Total Tour Time: 463
The group must also have a file called groupstatus.txt that contains your member names and a
short description of the status of your program. This file should be an ascii file. Though you may
create it with MS Word (or notepad/wordpad/jGrasp/etc), you should be certain that it is a text
file.
Submission: Prior to the deadline, one group member uploads your files (java and groupstatus.txt)
to Canvas (class files and data files are neither necessary nor wanted). Each student uploads an
evaluation.txt. I would suggest uploading long before the deadline and updating/replacing as you
go work on it today and upload, work on it tomorrow and replace, work on it the next day and
replace, . . ..
Cheating: This project is a group project (no more than 4 students per group). You can discuss
this project with other students. You can explain what needs to be done and give suggestions on
how to do it. You can use the web to find ideas. You cannot share source code with students
outside your group or submit solutions written by someone outside of your group (including code
downloaded from the internet).
