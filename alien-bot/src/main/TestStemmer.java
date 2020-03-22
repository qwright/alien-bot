/*
 * This is a standalone main function for internal testing purposes
 * Usecase: Test how Stemmer stems strings
 * Note: Only the first word in user input is stemmed, the rest are ignored
 *
 * Some Examples:
 *      eating  -> eat
 *      eating? -> eat
 *      eats    -> eat
 *
 *      political  -> polit
 *      politics   -> polit
 *
 *      spaceship  -> spaceship
 *      spaceships -> spaceship
 */

package main;

import java.util.Scanner;

public class TestStemmer {
    public static void main(String[] args) {
        Stemmer stem = new Stemmer();
        Scanner in = new Scanner(System.in);

        System.out.println(stem.run(in.next()));  // return the first stemmed word in the user input
    }
}