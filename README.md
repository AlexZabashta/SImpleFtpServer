# Simple Ftp Server
[Инструкция на русском](https://github.com/AlexZabashta/SimpleFtpServer/blob/master/%D0%98%D0%BD%D1%81%D1%82%D1%80%D1%83%D0%BA%D1%86%D0%B8%D1%8F.md)

I tried to find a simple FTP-server, but could not. Therefore, I wrote my own.

This project is based on `Apache FtpServer` (https://mina.apache.org/ftpserver-project/)

This is single user FTP-server. Note that it does not use SSL.

## How to run it
All you need is **ftpserver.jar** file from this project.
Just run it in command line by **java** (version 8 or higher).

You can find the examples in **run.bat** or **run.sh** scripts.
Just edit this files as you like and use them.

If you run *.jar* file with "your mouse" (using double click), it will still launch, but you will not be able to specify the parameters and you will have to close it using the task manager. So use *run.* scripts.

## Parameters

Carefully specify the parameters. Follow the program output to check what parameters it is going to use.

The list of parameters that can be specified as program arguments:

| Parameter                 |    Type | Default value |
| ------------------------- |---------| --------------|
| Address of the FTP-server | string ([InetAddress name](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html#getByName-java.lang.String-)) | `localhost`|
| Port of the FTP-server    | integer | `21`|
| FTP-server root directory | Path to the directory in your filesystem      |   `%TEMP%` |
| Access control            | `readonly` or `writable` |     `writable` |
| User name (login)         | string  |  `anonymous` |
| Password                  | string  |  `anonymous` |

You cannot change the order of the arguments, but you can omit the last.

## How to build it

You can use **javac** and add *.jar* files from **lib** directory of this project.

Or you can use **maven** with `mvn package` command. In this case you will find result *.jar* file in the **target** directory.

## Hints

* Even if you accidentally specify a nonexistent directory, the FTP-server will still start correctly. To test it, try to download or upload a file.
* Sometimes *SLF4J* can output the strange errors, for exapmle `Failed to load class...`. Do not worry, this does not affect the FTP-server. Ignore it and other output from *SLF4J*.
* Many FTP-clients are able to automatically use the *anonymous* login and password if you specify this option or leave these fields blank.
* Enclose the path in quote, if it contains spaces.
