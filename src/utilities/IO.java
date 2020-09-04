/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Scanner;

/**
 *
 * @author PC
 */
public class IO {

    private static Scanner input = new Scanner(System.in);

    /**
     * Prints 'text' on console output
     *
     * @param text text to output on console
     * @return None
     */
    public static void put(String text) {
        System.out.print(text);
    }

    /**
     * Prompts user for console input of an Integer
     *
     * @param prompt text to output on console
     * @return next Integer from console input
     */
    public static int getInt(String prompt) {
        put(prompt + " ");
        int result = input.nextInt();
        input.nextLine();           //Read up to next newline character
        return result;
    }

    /**
     * Prompts user for console input of a String
     *
     * @param prompt text to output on console
     * @return next line of console input
     */
    public static String getString(String prompt) {
        put(prompt + " ");
        return input.nextLine();
    }
}
