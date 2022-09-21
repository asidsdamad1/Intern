package com.company;

import java.util.Scanner;

public class LongestWord {
    static String findLongestWord(String input) {
        String maxlethWord = "";
        // 
        String[] words = input.split(" ");

        for(int i = 0; i < words.length; i++) {
            if(words[i].length() > maxlethWord.length())
                maxlethWord = words[i];
        }

        return  maxlethWord;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter string: ");
        String input = sc.nextLine();

        System.out.println("The longest word in string is: " + findLongestWord(input));
    }

}
