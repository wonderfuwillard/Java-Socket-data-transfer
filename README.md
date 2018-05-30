# Java-Socket-data-transfer
Use Java Socket do some basic data transfer(TCP&amp;UDP).

###Steps: 

| Client | Server |
| ------ | ------ |
||Creat TCP socket and print port #|
|Creat TCP socket and send req_code (TCP)||
| |Receive and Check the req_code|
| |Creat UDP socket and send port number to client(UDP)|
|receive server's UDP port number| |
|Send input_string(UDP)| |
||Receive input_string|
||Send reversed input_string(UDP)|
|Receive reversed input_string| |


###UML diagram(seems not support)
```sequence
Client->Server: req_code (TCP）
Note right of Server: Check the req_code
Server->Client: UDP port number(UDP)
Client->Server:input_string(UDP)
Note right of Server: reverse input_string
Server->Client: send reversed input_string(UDP)
```







use `make` command to compile the program.

to run server, call: ./server <req_code>
<req_code> can be any integer, client will use the req_code to connect.
server will print the <n_port> after run.

to run client, call: ./client <server_address> <n_port> <req_code> <input_string>
<n_port> is print on server
the <req_code> must same as the server.


GNU Make 3.81

javac 1.8.0_91

