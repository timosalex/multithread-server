# multithread-server

java project


A multithread server example written in JAVA. 

Server runs on initial port 2222 and waits for a TCP connection by a client. When this happens it sends client the available htmls from the specified folder and a new available port and waits for a new session by creating a new thread. 

As a simple demonstration the thread waits for the client for a specific html and then sends the file

client here:
https://github.com/timosalex/mutlithread-client/
