# CourierRobot :: A SMTP Client Automated

Authors: Loan Lassalle and Tano Iannetta 
***

CourierRobot is a cross-platform SMTP client built on Java. It allows you to send a batch of e-mails with pre-define emails addresses and contents.
It choose emails addresses and contents randomly and created emails to make a joke.
It also allows you to send another type of message by modifying class files

## Requirements

CourierRobot is built using Java and to function you need a Java 8 runtime environment. CourierRobot works on OSX, Windows and *nix based platforms that have a Java 8 runtime environment. You can download the Java 7 runtime environment [here](http://www.oracle.com/technetwork/java/javase/downloads/java-se-jre-7-download-432155.html).

## Installation / setup

The easiest way to install and run CourierRobot is by downloading the jar file [here](https://github.com/lassalleloan/courier-robot/raw/master/out/artifacts/CourierRobot_jar/CourierRobot.jar?raw=true) (right click -> "save target as"). Extract it to any place you like and start the server by running: java -jar CourierRobot.jar

This will run CourierRobot on server address localhost (default address Mock SMTP server) and server port 2525 (default port Mock SMTP server).
If you want to change the settings of the server to contact, you must change the values in config.properties.
You are be able to defines a list of emails addresses and emails contents in the emailsAddresses file and emailsContents.

### Installation / setup Mock SMTP server

A Mock SMTP server allows you to test if outgoing emails are sent (without actually sending them) and to see what they look like. Often, it provides an interface that displays which emails were sent and shows you what the contents of those emails are. If you use it you can be sure that your outgoing emails will not reach customers or users by accident.Often, It really just a mock SMTP server and has no email sending functionality.

We have used [MockMock](https://github.com/tweakers-dev/MockMock), created by tweakers-dev to experiment our tool.

The easiest way to install and run MockMock is by downloading the jar file [here](https://github.com/tweakers-dev/MockMock/blob/master/release/MockMock.jar?raw=true) (right click -> "save target as"). Extract it to any place you like and start the server by running: java -jar MockMock.jar -p 2525

This will run MockMock on the ports 2525 (for SMTP) and 8282 (default port for the web interface). After it started you can use your internet browser to navigate to [http://localhost:8282].
For more details we proposed you to read the description of MockMock project [here](https://github.com/tweakers-dev/MockMock).
