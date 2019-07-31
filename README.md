# SimpleFtpServer
This project is based on `Apache FtpServer` (https://mina.apache.org/ftpserver-project/)

This is single user FTP server. Note that it does not use SSL.

## How to run it
All you need is **ftpserver.jar** file from this project.
Just run it by **java** (version 8 or higher).

You can find the examples in **run.bat** or **run.sh**.
Just edit this files as you like and use them.

If you run it with "your mouse" (using double click), it will still launch, but you will not be able to specify the parameters   and you will have to close it using the task manager.

## Parameters

Carefully specify the parameters. Follow the program output to check what parameters it is going to use.

The list of parameters that can be specified as program arguments:

| Parameter                 |    Type | Default value |
| ------------------------- |---------| --------------|
| Address of the FTP server | string ([see InetAddress documentation](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html#getByName-java.lang.String-)) | `localhost`|
| Port of the FTP server    | integer | `21`|
| FTP server root directory | Path to the directory in you filesystem      |   `%TEMP%` |
| Access control            | `readonly` or `writable` |     `writable` |
| User name (login)         | string  |  `anonymous` |
| Password                  | string  |  `anonymous` |

You cannot change the order of the arguments, but you can omit the last.

## How to build it

You can use **javac** and add *.jar* files from **lib** directory of this project.

Or you can use **maven** with `mvn package` command. In this case you will find result *.jar* file in the **target** directory.
