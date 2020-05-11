package com.marshon.ceasarcipher2;
import java.util.*;

public class CeasarCipher 
{
    private static Scanner input = new Scanner(System.in);
    private static String enteredValue;
    private static ArrayList<String> encodedList = new ArrayList<>();
    private static ArrayList<String> decodedList = new ArrayList<>();
    
    public static void main(String[] args)
    {
        userLoop();
    }
    
    public static String encode(String str)
    {
        String encodedString = "";
        char charArray[] = str.toCharArray();
        
        for(int x = 0; x < charArray.length; x++)
        {
            if((charArray[x] - 96) <= 21)
            {
                encodedString += (char)((int)(charArray[x]) + 5);
            }
            else
            {
                encodedString += (char)((int)(charArray[x]) + 5 - 26);
            }
        }
        
        return encodedString;
    }
    
    public static String decode(String str)
    {
        String decodedString = "";
        char charArray[] = str.toCharArray();
        
        for(int x = 0; x < charArray.length; x++)
        {
            if(Character.isLowerCase(charArray[x])) //Lowercase
            {
                if((charArray[x] - 96) <= 5)
                {
                    decodedString += (char)((int)(charArray[x]) + 26 - 5);
                }
                else
                {
                    decodedString += (char)((int)(charArray[x]) -5);
                }
            }
            else // Uppercase or symbols
            {
                if((charArray[x] - 64) <= 5)
                {
                    if(charArray[x] - 64 < 9)
                    {
                        decodedString += (char)((int)(charArray[x]) - 5);
                    }
                    else
                    {
                        decodedString += (char)((int)(charArray[x]) + 26 - 5);
                    }
                }
                else
                {
                    decodedString += (char)((int)(charArray[x]) -5);
                }
            }
        }
        
        return decodedString;
    }
    
    public static boolean testString(String str) // Test if entered String passed requirement(s)
    {
        boolean test = true;
        
        char[] charArray = str.toCharArray();
        for(char x :charArray)
        {
            if(!Character.isLetter(x) && x != '!' && x != '?' && x != '.' && x != ',' && x != '\'' && x != ' ')
            {
                test = false;
            }
        }
        
        return test;
    }
    
    public static void userLoop()
    {
        
        try
        {
            boolean end = false;
            int choice = 0;

            while(end == false)
            {
                System.out.println("\nWhat do you want to do?");
                System.out.println("1: Enter a string: ** can contain letters, !, ?, ., ,, ' **");
                System.out.println("2: Display encoded strings");
                System.out.println("3: Display entered strings");
                System.out.println("4: Decode encoded list");
                System.out.println("5: End");
                choice = input.nextInt(); input.nextLine();

                switch(choice)
                {
                    case 1: 
                        System.out.println("Enter String: ");
                        String str = input.nextLine();
                        
                        if(!testString(str))
                        {
                            throw new RuntimeException("Improper String input!");
                        }
                        
                        decodedList.add(str);
                        encodedList.add(encode(str));
                        break;

                    case 2: 
                        for(String listString: encodedList)
                        {
                            System.out.println(listString);
                        }
                        break;

                    case 3: 
                        for(String listString: decodedList)
                        {
                            System.out.println(listString);
                        }
                        break;
                        
                    case 4: 
                        for(String y: encodedList)
                        {
                            System.out.println(decode(y));
                        }
                        break;

                    case 5: end = true; System.exit(0);
                        break;

                    default: System.out.println("Choose option 1, 2, 3, 4 or 5");
                }
            }
        }
        catch(RuntimeException ex)
        {
            ex.printStackTrace();
            userLoop();
        }
    }
}