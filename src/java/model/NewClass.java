/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.*;
/**
 *
 * @author chukr
 */
public class NewClass {

  

    public static void main(String args[])
    {
  
        // try catch block to handle exceptions
        try {
  
            // Create a file object
            File f = new File("");
  
            // get the absolute path
            // of file f
            String absolute = f.getAbsolutePath();
  
            // display the file path of the file object
            // and also the file path of absolute file
            System.out.println("Original  path: "
                               + System.getProperty("user.dir"));
            System.out.println("Absolute  path: "
                               + absolute);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}