# SCC_Coursework

This program is what I created for Service-Centric and Cloud Computing (SCC).

The application uses the Springboot framework in Java to host an Apache Tomcat 9.0 server.
Using this server and a rest controller, I implemented several function calls:
1) /weather
2) /user
3) /query
4) /submit
5) /intent
6) /submit

First, /weather makes a call to the weather rest service that gets the weather data from a location provided on a given date using the 7timer API.
Second, /user makes a call to the user ID rest service that generates a user ID in UUID format and is returned as a user Class object.
Third, /query makes a call to the RabbitMQ Subscriber that checks if any trips in the message queue match the given parameters and returns the received message and weather data for this date.
Fourth, /submit makes a call to the RabbitMQ Subscriber that checks which users intend to attend a trip.
Fifth, /intent makes a call to the RabbitMQ Publisher that publishes a users intent to attend a trip.
Finally, /submit makes a call to the RabbitMQ Publisher that publishes a trip proposal, along with the co-ordinates of the trips location.

There were many features that I intended to implement but didn't have time to before submission. These may be later added to this Github, on an additional branch.
This included:
- hosting the trip data on a secure server
- providing clients in other languages (such as a web-based, JavaScript client)
- most importantly, improving how the RabbitMQ messaging service functions.
