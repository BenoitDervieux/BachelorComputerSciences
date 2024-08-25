# TFTP Server Project README

This repository contains a TFTP (Trivial File Transfer Protocol) server project developed for Assignment 3.

## Purpose of a TFTP Server

The TFTP server is useful to send and receive files regardless of the operating system you have.

## Launching the Server

To launch it:
- Navigate to the folder containing the server code.
- Open a command line interface.
- Compile the server by typing: ```javac TFTPServer.java```
- Launch the server by typing: ```java TFTPServer```

Once the server is running, you can start sending or receiving files.

## Testing

A Python folder named `test_a3` is included for testing purposes. It fetches files from the `read` folder and copies those files ending with `.bin` to files ending with `.ul`. Ensure to delete any existing `.ul` files before testing.


Both André and Benoit contributed equally to the development of this project.

## Checkstyle

We tried to run the checkstyle but the provided template was not compatible with the google checkstyle. We hence did our best to clean the code as we could.