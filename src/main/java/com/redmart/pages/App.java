package com.redmart.pages;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Random rand = new Random();
        int randomItem= rand.nextInt(5-1) + 0;
        System.out.println(randomItem);
    }
}
