package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;

public class View {


    Scanner input = new Scanner(System.in);
    private static View instance = null;

    public View(){

    }

    public static View getInstance(){
        if(instance == null){
            instance = new View();
        }
        return instance;
    }

    public int showMainMenu(){

        int menuChoiceToInt;
        String menuChoice;
        do{
            System.out.println("\n *** Welcome To BackYard Inn Hotel! ***\n          How Can I Help You?\n");
            System.out.println("1. Look For Work\n" +
                    "2. Check In\n" +
                    "3. Show People At Hotel\n" +
                    "4. Check Out / Quit Work\n" +
                    "5. Save To File\n" +
                    "6. Load From File\n" +
                    "7. Quit Program\n");

            menuChoice = input.next();

            // Checking if user input is a number.
            if (isNumeric(menuChoice) == true && menuChoice.matches("[1-7]")) {
                menuChoiceToInt = Integer.parseInt(menuChoice);
            } else {
                menuChoiceToInt = 0;
                System.out.println("Sorry I Couldn't get that!?, Please Choose 1 - 7 !\n");
            }

        }while (menuChoiceToInt == 0);

        return menuChoiceToInt;
    }

    public int setWhichWork(){

        int menuChoiceToInt;
        String menuChoice;
        do{
            System.out.println("Alright! Lets See If We Got a Job For You!\nWhich Type Of Employment Are You Looking For?\n" +
                    "\n1. Administrator\n" +
                    "2. Employee\n" +
                    "3. Back To Main Menu");
                    menuChoice = input.next();

            if (isNumeric(menuChoice) == true && menuChoice.matches("[1-3]")) {
                menuChoiceToInt = Integer.parseInt(menuChoice);
            } else {
                menuChoiceToInt = 0;
                System.out.println("Sorry I Couldn't get that!?, Please Choose 1 - 3 !\n");
            }

        }while (menuChoiceToInt == 0);

        return menuChoiceToInt;
    }

    public String setName(){

        String name;
        do {
            System.out.println("Enter Name Please: ");
            name = input.next();


        }while (!name.matches("[a-öA-Ö]+"));

        return name;
    }

    public long setSocialNumber(){

        long socialNumberToLong;
        String socialNumber;
        boolean correctInput = false;

        do{
            do{
                System.out.println("Enter SocialSecurity Number YYMMDD-NNNN:");
                socialNumber = input.next();

                if(socialNumber.length() > 6 && socialNumber.charAt(6) == '-' && socialNumber.length() == 11 ){
                    correctInput = true;
                    socialNumber = socialNumber.replace("-","");
                }else {
                    System.out.println("Sorry I Couldn't Get That!, Please Use YYMMDD-NNNN!");
                }
            }while (!correctInput);



            if (isNumeric(socialNumber) == true) {
                socialNumberToLong = Long.parseLong(socialNumber);
            } else {
                socialNumberToLong = 0;
                System.out.println("Sorry I Couldn't Get That!, Please Use YYMMDD-NNNN!\n");
            }

        }while (socialNumberToLong == 0);

        return socialNumberToLong;
    }

    public int showPeopleAtHotel(){

        int menuChoiceToInt;
        String menuChoice;
        do{
            System.out.println("On Which Would You Like To Peek on?\n" +
                    "\n1. The Admins\n" +
                    "2. The Employees\n" +
                    "3. The Residents\n" +
                    "4. Search A Specific Person\n" +
                    "5. Show Residents In Specific Room\n" +
                    "6. Back To Main Menu\n");
            menuChoice = input.next();

            if (isNumeric(menuChoice) == true && menuChoice.matches("[1-6]")) {
                menuChoiceToInt = Integer.parseInt(menuChoice);
            } else {
                menuChoiceToInt = 0;
                System.out.println("Sorry I Couldn't get that!?, Please Choose 1 - 6 !\n");
            }

        }while (menuChoiceToInt == 0);

        return menuChoiceToInt;
    }

    public int chooseRoomInHotel(){

        String guestChoice;
        int guestChoiceToInt;

        guestChoice = input.next();
        if (isNumeric(guestChoice) == true && guestChoice.matches("[1-9]|10")){
            guestChoiceToInt = Integer.parseInt(guestChoice);
        } else {

            guestChoiceToInt = 0;
            System.out.println("Sorry I Couldn't get that!?, Please Choose A Room, 1 - 10!\n");
        }
        return (guestChoiceToInt-1);
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public void showError(String errorMessage){
        System.out.println("Error: " + errorMessage + "\n");
    }

    public static boolean isNumeric(String strNum) {

        try {
            @SuppressWarnings("unused")
            double d = Double.parseDouble(strNum);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public int setNumberOfPersons(int maxResidents){

        String numberOfPersons = input.next();
        int numberOfPersonsToInt;
        if(maxResidents == 2){
            if (isNumeric(numberOfPersons) == true && numberOfPersons.matches("[1-2]")) {
                numberOfPersonsToInt = Integer.parseInt(numberOfPersons);
            } else {
                numberOfPersonsToInt = 0;
                System.out.println("Sorry I Couldn't get that!?, Please Choose 1 - 2 !\n");
            }
            return numberOfPersonsToInt;
        }
        else if(maxResidents == 4){
            if (isNumeric(numberOfPersons) == true && numberOfPersons.matches("[1-4]")) {
                numberOfPersonsToInt = Integer.parseInt(numberOfPersons);
            } else {
                numberOfPersonsToInt = 0;
                System.out.println("Sorry I Couldn't get that!?, Please Choose 1 - 4 !\n");
            }
            return numberOfPersonsToInt;
        }
        return 0;
    }

    public int setNumberForMenu(int numberOfChoices){

        String choiceNumberFromUser;
        int choiceNumberFromUserToInt;
        choiceNumberFromUser = input.next();

        if (isNumeric(choiceNumberFromUser) == true && choiceNumberFromUser.matches("[1-3]")) {
            choiceNumberFromUserToInt = Integer.parseInt(choiceNumberFromUser);

        } else {
            choiceNumberFromUserToInt = 0;
            System.out.println("Sorry I Couldn't get that!?, Please Choose 1 - " + numberOfChoices + "!\n");
        }
        return choiceNumberFromUserToInt;
    }

    public long setSocial(){
        long socialNumberToInt;
        String socialNumber;
        do{
            System.out.println("Enter SocialSecurity Number Please: ");
            socialNumber = input.next();

            if (isNumeric(socialNumber) == true) {
                socialNumberToInt = Long.parseLong(socialNumber);
            } else {
                socialNumberToInt = 0;
                System.out.println("Sorry I Couldn't get that!, Please Use Numbers!\n");
            }
        }while (socialNumberToInt == 0);

        return socialNumberToInt;
    }

}
