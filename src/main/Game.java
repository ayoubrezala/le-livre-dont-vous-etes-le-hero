package main;

import java.util.Scanner;

import net.sf.json.JSONObject;
import properties.Properties;

public class Game {

    private static String welcomeMessage = "Welcome to the GameBook !";
    private static String inputMessage = "Enter your choice for section number";
    private static String ws = " ";
    private static String startingSectionId = "1";




    public static void PlayGame(JSONObject book) throws InvalidBookException
    {


        


        printWelcomeMessage();


        JSONObject sections = book.getJSONObject(Properties.sectionsString);




        JSONObject startingSection = sections.getJSONObject(startingSectionId);


        if(!isBookValid(startingSection))
        {
            throw new InvalidBookException();
        }


        String startingText = startingSection.getString(Properties.textString);



        print(startingText);


        Integer currentSectionId = Integer.parseInt(startingSectionId);




        for(; !hasEnded(sections.getJSONObject(currentSectionId.toString())); currentSectionId++)
        {
            // for()
        }

















    }



    // boolean functions

    private static boolean hasWon(JSONObject section)
    {
        return section.getBoolean(Properties.winString);
    }


    private static boolean hasEnded(JSONObject section)
    {
        return section.getBoolean(Properties.endString);
    }

    /*
     * function checks whether the book is valid or not, if the first section is a dead end. the Book isn't valid, true otherwise.
     */
    private static boolean isBookValid(JSONObject startingSection)
    {
        return !hasEnded(startingSection);
    }

    // printing functions

    private static void printWelcomeMessage()
    {
        System.out.println(welcomeMessage);
    }
    

    private static void printInputMessage(String sectionNumber)
    {

        String s = inputMessage + ws + sectionNumber + ":" + ws; 

        System.out.print(s);
    }

    private static void print(String string)
    {
        System.out.println(string);
    }

    // others


    private static String getInput()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice for section number: ");
        String input = scanner.nextLine();
        scanner.close();

        return input;
    }



    
}
