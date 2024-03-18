package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class Main {

  public static void main(String[] args) {

    // working with file
    File file = new File("src/main/resources/myFile.txt");

    try {
      boolean isCreated = file.createNewFile();
      if (isCreated) {
        System.out.println("File created " + file.getAbsolutePath());
      } else {
        System.out.println("File already exists " + file.getAbsolutePath());
      }
    } catch (IOException e) {
      System.out.println("File creation not successful. Got exception: " + e.getMessage());
    }

    System.out.println("Can write: " + file.canWrite());
    System.out.println("Can execute: " + file.canExecute());
    System.out.println("Is absolute: " + file.isAbsolute());
    System.out.println("Is directory: " + file.isDirectory());
    System.out.println("Parent: " + file.getParent());
    System.out.println(file.toURI());

    // working with directory
    File dir = new File("src/main/resources/myDir");
    boolean isDirCreated = dir.mkdir();
    if (isDirCreated) {
      System.out.println("Directory created " + dir.getAbsolutePath());
    } else {
      System.out.println("Directory already exists " + dir.getAbsolutePath());
    }
    System.out.println("Is directory: " + dir.isDirectory());
    System.out.println("Free space: " + dir.getFreeSpace());
    String[] dirChildren = dir.list();
    for (String child : dirChildren) {
      System.out.println(child);
    }

    // nested directory
    File dir3 = new File("src/main/resources/myDir/myDir2/myDir3");
    boolean isDir3Created = dir3.mkdirs();
    if (isDir3Created) {
      System.out.println("Directory created " + dir3.getAbsolutePath());
    } else {
      System.out.println("Directory already exists " + dir3.getAbsolutePath());
    }

    // working with temp files
//    File tempFile = File.createTempFile("Files_project", "log");
//    if (tempFile.canRead()) {
//      System.out.println("File created " + tempFile.getAbsolutePath());
//    } else {
//      System.out.println("File already exists " + tempFile.getAbsolutePath());
//    }

    InputStream inputStream = null;
    Reader reader = null;
    BufferedReader bufferedReader = null;
    InputStream myFileStream = null;
    Reader myFileReader = null;
    Writer fileWriter = null;
    try {
      // reading
      inputStream = new FileInputStream(file);
      int data = inputStream.read();
      while (data != -1) {
        // do something with read byte
        System.out.print((char) data);
        data = inputStream.read();
      }

      // buffering
      System.out.println();
      reader = new FileReader(file);
      bufferedReader = new BufferedReader(reader);
      int character = bufferedReader.read();
      while (character != -1) {
        System.out.print((char) character);
        character = bufferedReader.read();
      }

      // conversion between input stream and reader
      System.out.println();
      myFileStream = new FileInputStream("myFile.txt");
      myFileReader = new InputStreamReader(myFileStream);
      int character1 = myFileReader.read();
      while (character1 != -1) {
        System.out.print((char) character1);
        character1 = myFileReader.read();
      }

      // writing
      OutputStream outputStream = new FileOutputStream("myFile.txt", true);
      outputStream.write(68); // D letter
      outputStream.write("CI".getBytes());

      fileWriter = new FileWriter("myFile.txt", true);

      fileWriter.write("data 1");
      fileWriter.write("data 2");
      fileWriter.write("data 3");
    } catch (IOException e) {
      System.out.println("Problem occurred working with io operations. Got exception: " + e.getMessage());
    } finally {
      try {
        inputStream.close();
        bufferedReader.close();
        reader.close();
        myFileReader.close();
        myFileStream.close();
        fileWriter.close();
      } catch (IOException e) {
        System.out.println("Problem closing resource. Got exception: " + e.getMessage());
      }
    }

  }
}