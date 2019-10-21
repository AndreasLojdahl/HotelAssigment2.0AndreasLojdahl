package com.company;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HotelProgram {

    ArrayList<Room> roomsInHotel = new ArrayList<>();
    ArrayList<Admin> adminsInHotel = new ArrayList<>();
    ArrayList<Resident> residentsInHotel = new ArrayList<>();
    ArrayList<Employee> employeesInHotel = new ArrayList<>();

    final static int MAX_ADMINS = 3;
    final static int MAX_EMPLOYEES = 10;
    final static int MAX_RESIDENTS = 30;
    final static int MAX_ROOMS = 10;


    public HotelProgram() {

        createRooms();

        hotelProgramMenu();
    }

    public void hotelProgramMenu() {
        int menuChoice;

        do {
            menuChoice = View.getInstance().showMainMenu();
            switch (menuChoice) {
                case 1:
                    addWorkerSemiGeneric();
                    break;
                case 2:
                    checkIn();
                    break;
                case 3:
                    showPeople();
                    break;
                case 4:
                    checkOutOrQuitSubMenu();
                    break;
                case 5:
                    saveToFile();
                    break;
                case 6:
                    loadFromFile();
                    break;
            }
        } while (menuChoice != 7);

        View.getInstance().showMessage("*** WELCOME BACK ANYTIME! ***");
    }

    /**
     * Method creates 10 hotel rooms
     */
    public void createRooms() {

        //Creating 10 rooms
        int roomNr = 1;
        for (Room.RoomId roomId : Room.RoomId.values()) {
            for (int i = 0; i < 5; i++) {
                roomsInHotel.add(new Room(roomNr, roomId));
                roomNr++;
            }
        }
    }

    public void loadFromFile() {
        // adds room objects from file to room array.
        ArrayList<Admin> adminsFromFile = (ArrayList<Admin>) FilesUtility.loadObject("admins.ser");
        if(adminsFromFile != null){
            for (Admin admin : adminsFromFile) {
                adminsInHotel.add(admin);
            }
        }

        ArrayList<Resident> residentsFromFile = (ArrayList<Resident>) FilesUtility.loadObject("residents.ser");
        if(residentsFromFile != null){
            for (Resident resident : residentsFromFile) {
                residentsInHotel.add(resident);
            }
        }

        ArrayList<Employee> employeesFromFile = (ArrayList<Employee>) FilesUtility.loadObject("employees.ser");
        if(employeesFromFile != null){
            for (Employee employee : employeesFromFile) {
                employeesInHotel.add(employee);
            }
        }
        /*
        ArrayList<Room> roomsFromFile = (ArrayList<Room>) FilesUtility.loadObject("rooms.ser");
        if(roomsFromFile != null){

            for (Room room: roomsFromFile){
                roomsInHotel.add(room);
            }
        }

         */
    }

    public void saveToFile() {

        FilesUtility.saveObject(adminsInHotel, "admins.ser", StandardOpenOption.CREATE);
        FilesUtility.saveObject(residentsInHotel, "residents.ser", StandardOpenOption.CREATE);
        FilesUtility.saveObject(employeesInHotel, "employees.ser", StandardOpenOption.CREATE);
        FilesUtility.saveObject(roomsInHotel,"rooms.ser",StandardOpenOption.CREATE);

    }

    /**
     * Check if all rooms are occupied if not checks in resident, depending on room type and chosen resident per room.
     */
    public void checkIn() {

        boolean isEmpty;
        int roomChoice;
        boolean correctRoomChoice;

        int emptyRooms = showRoomsAvailability();
        if (emptyRooms == 0) {
            View.getInstance().showMessage("Sorry! We Are Fully Booked At The Moment!");
        } else {
            View.getInstance().showMessage("Lets Get You Checked In!\n");
            View.getInstance().showMessage("We Have: " + emptyRooms + " Available Rooms At The Moment!\n" +
                    "These Are Our Rooms:\n");

            // Printing Room Array and checking if user input is within Array size.
            do {
                displayRooms(1);
                View.getInstance().showMessage("\nIn Which Available Room Would You Like To Stay in?");
                roomChoice = View.getInstance().chooseRoomInHotel();
                correctRoomChoice = checkingIfNumberIsCorrect(roomChoice);

            } while (!correctRoomChoice);

            // Checks if room from user input is available.
            isEmpty = roomsInHotel.get(roomChoice).isAvailable((roomChoice + 1));

            if (isEmpty == true) {

                View.getInstance().showMessage("Alright! This is a " + roomsInHotel.get(roomChoice).getRoomType() + " Room!");
                if (roomsInHotel.get(roomChoice).getRoomType() == Room.RoomId.DOUBLE) {

                    View.getInstance().showMessage("Max People In This Room is: 2, How Many will Be Staying in this Room?: ");
                    int personNumbers = View.getInstance().setNumberOfPersons(2);

                    for (int i = 0; i < personNumbers; i++) {

                        //createPerson(residentsInHotel,MAX_RESIDENTS,"resident");
                        String name = View.getInstance().setName();
                        long socialNumber = View.getInstance().setSocialNumber();
                        Resident resident = new Resident(name, socialNumber);
                        residentsInHotel.add(resident);
                        roomsInHotel.get(roomChoice).setResidentsInRoom(resident);
                    }
                    View.getInstance().showMessage("Welcome! Have A Nice Stay!");

                } else if (roomsInHotel.get(roomChoice).getRoomType() == Room.RoomId.SUITE) {

                    View.getInstance().showMessage("Max People In This Room is: 4, How Many will Be Staying in this Room?: ");
                    int personNumbers = View.getInstance().setNumberOfPersons(4);

                    for (int i = 0; i < personNumbers; i++) {

                        String name = View.getInstance().setName();
                        long socialNumber = View.getInstance().setSocialNumber();
                        Resident resident = new Resident(name, socialNumber);
                        residentsInHotel.add(resident);
                        roomsInHotel.get(roomChoice).setResidentsInRoom(resident);
                    }
                    View.getInstance().showMessage("Welcome! Have A Nice Stay!");
                }
            } else {
                View.getInstance().showMessage("Sorry! This Room Is Already Occupied!");
            }

        }
    }

    public int showRoomsAvailability() {

        // Checking if Array in each room is empty, if empty they are available.
        int emptyRooms = 10;
        boolean availableRooms;
        for (Room room : roomsInHotel) {
            availableRooms = room.isAvailable(room.getRoomNumber());
            if (availableRooms == false) {
                emptyRooms--;
            }
        }
        return emptyRooms;
    }

    public void displayRooms(int displayMode) {

        // Displays Rooms.
        switch (displayMode) {
            case 1:
                for (Room room : roomsInHotel) {
                    room.showRoomAvailability();
                }
                break;
            case 2:
                for (Room room : roomsInHotel) {
                    room.showRoomStatus();
                }
        }
    }

    public void checkPeopleInRoomAndDisplay(){

        boolean correctRoomChoice;
        int roomChoice;
        // Displays Residents from Resident-Array in each individual room object from user input choice.
        do {
            int emptyRooms = showRoomsAvailability();
            View.getInstance().showMessage("There Are Currently: " + (MAX_ROOMS - emptyRooms) + " Rooms Check Ins!\n");

            if (MAX_ROOMS - emptyRooms == 0) {
                return;
            } else {
                View.getInstance().showMessage("Which Room Would You Like To Display?: \n");
                displayRooms(2);
                roomChoice = View.getInstance().chooseRoomInHotel();
                correctRoomChoice = checkingIfNumberIsCorrect(roomChoice);
            }
        } while (!correctRoomChoice);


        if (roomsInHotel.get(roomChoice).getResidentsInRoom().size() == 0) {
            View.getInstance().showMessage("Sorry! No Residents Have Checked In Here!");
        } else {
            roomsInHotel.get(roomChoice).showPeopleInRoom();
        }

    }

    public void checkOutFromRoom() {
        boolean correctRoomChoice;
        int roomChoice;

        do {
            View.getInstance().showMessage("Which Room Would You Like To Check Out From?: ");
            displayRooms(2);
            roomChoice = View.getInstance().chooseRoomInHotel();
            correctRoomChoice = checkingIfNumberIsCorrect(roomChoice);
        } while (!correctRoomChoice);

        if (roomsInHotel.get(roomChoice).getResidentsInRoom().size() == 0) {
            View.getInstance().showMessage("Sorry! No One Has Checked In Here!");
        } else {

            for (Resident resident : roomsInHotel.get(roomChoice).getResidentsInRoom()) {
                residentsInHotel.remove(resident);
                View.getInstance().showMessage("You have Successfully Checked Out, " + resident.getName());
            }
            roomsInHotel.get(roomChoice).getResidentsInRoom().clear();

            View.getInstance().showMessage("Thanks For Choosing BackYard Inn! ");
        }

    }

    public void checkOutOrQuitSubMenu() {
        int choiceOption;

        do {
            View.getInstance().showMessage("Hi! Would You Like To Check Out Or Quit Work?\n" +
                    "1. Check Out\n" +
                    "2. Quit Work\n" +
                    "3. Back To Main Menu");
            choiceOption = View.getInstance().setNumberForMenu(3);
        } while (choiceOption < 1 || choiceOption > 3);

        switch (choiceOption) {
            case 1:
                checkOutFromRoom();
                break;
            case 2:
                quitWork();
            case 3:
                //back to main menu

        }

    }

    public void addWorkerSemiGeneric() {

        //Creating Type Of Worker Depending On User Input In View

        int whichWorkerChoice = View.getInstance().setWhichWork();

        switch (whichWorkerChoice) {
            case 1:
                createPerson(adminsInHotel,MAX_ADMINS,"admins");
                break;
            case 2:
                createPerson(employeesInHotel,MAX_EMPLOYEES,"employees");
        }
    }

    public <T extends Collection> void createPerson(T arrayList, int maxNumber, String typeOfPerson){

        if(arrayList.size() == maxNumber){
            View.getInstance().showError(" Sorry We're Not Looking For Any "+ typeOfPerson+" At The Moment");
        }else{
            String name = View.getInstance().setName();
            long socialNumber = View.getInstance().setSocialNumber();

            switch (typeOfPerson){
                case "admin":
                    arrayList.add(new Admin(name,socialNumber));
                    View.getInstance().showMessage("Welcome " + name + "! Lets Get You To Work!");
                    break;
                case "employee":
                    arrayList.add(new Employee(name,socialNumber));
                    View.getInstance().showMessage("Welcome " + name + "! Lets Get You To Work!");
                    break;
            }
        }
    }

    public void quitWork() {
        //Method to search the arrays from user input if a match remove object from list.
        Admin matchSearchAdmin;
        Employee matchSearchEmployee;
        String name;

        View.getInstance().showMessage("Which Person Is Thinking About Quiting?: ");

        name = View.getInstance().setName();
        matchSearchAdmin = searchThroughAdmin(name);
        if (matchSearchAdmin != null) {
            adminsInHotel.remove(matchSearchAdmin);
            View.getInstance().showMessage(name + " Have Quit Their Work!");
        }

        matchSearchEmployee = searchThroughEmployee(name);
        if (matchSearchEmployee != null) {
            employeesInHotel.remove(matchSearchEmployee);
            View.getInstance().showMessage(name + " Have Quit Their Work!");
        }

        if (matchSearchAdmin == null && matchSearchEmployee == null) {
            View.getInstance().showMessage("Sorry! No " + name + " is Working Here!");
        }
    }

    public void showPeople() {

        int menuChoice = View.getInstance().showPeopleAtHotel();

        // Show Info From Objects Depending on Users choice
        switch (menuChoice) {
            case 1:
                showAllAdminsAndEmployeesAndResidents(adminsInHotel, "Sorry! No Admins Have Started Working");
                break;

            case 2:
                showAllAdminsAndEmployeesAndResidents(employeesInHotel, "Sorry! No Employees Have Started Working!");
                break;

            case 3:
                showAllAdminsAndEmployeesAndResidents(residentsInHotel,"Sorry! No Residents Have Checked In!");
                break;

            case 4:
                // Comparing all objects names from all Arrays with searched name from user.

                View.getInstance().showMessage("Please Enter Name Of Person You Would Like To Find: ");
                String searchedName = View.getInstance().setName();
                searchThroughAllArrays(searchedName);
                break;

            case 5:
                checkPeopleInRoomAndDisplay();
                break;

            case 6:
                // Back To Main Menu
        }
    }

    public <T extends List> void showAllAdminsAndEmployeesAndResidents(T arrayList, String message){

        if(arrayList.size() == 0){
            View.getInstance().showMessage(message);
        }else{
            for(Object t: arrayList){
                View.getInstance().showMessage(t.toString());
            }
        }
    }

    public void searchThroughAllArrays(String searchedName) {

        if (adminsInHotel.size() != 0) {
            for (Admin admin : adminsInHotel) {
                if (searchedName.equalsIgnoreCase(admin.getName())) {
                    View.getInstance().showMessage("Match! Here Is Info About " + admin.getName());
                    admin.showInfo();
                    return;
                }
            }
        }
        if (employeesInHotel.size() != 0) {
            for (Employee employee : employeesInHotel) {
                if (searchedName.equalsIgnoreCase(employee.getName())) {
                    View.getInstance().showMessage("Match! Here Is Info About " + employee.getName());
                    employee.showInfo();
                    return;
                }
            }
        }
        if (residentsInHotel.size() != 0) {
            for (Resident resident : residentsInHotel) {
                if (searchedName.equalsIgnoreCase(resident.getName())) {
                    View.getInstance().showMessage("Match! Here Is Info About " + resident.getName());
                    resident.showInfo();
                    return;
                }
            }
        }
        View.getInstance().showMessage("Sorry! Looks Like There Is No One " + searchedName + " is Staying At This Hotel!");
    }

    public Admin searchThroughAdmin(String searchedName) {

        if (adminsInHotel.size() != 0) {
            for (Admin admin : adminsInHotel) {
                if (searchedName.equalsIgnoreCase(admin.getName())) {
                    return admin;
                }
            }
        }
        return null;
    }

    public Employee searchThroughEmployee(String searchedName) {
        if (employeesInHotel.size() != 0) {
            for (Employee employee : employeesInHotel) {
                if (searchedName.equalsIgnoreCase(employee.getName())) {
                    return employee;
                }
            }
        }
        return null;
    }

    public boolean checkingIfNumberIsCorrect(int number) {

        // Method to return true or false depending on number from user input
        if (number < 0) {
            return false;
        } else if (number > 9) {
            return false;
        }
        return true;
    }

    public Resident searchThroughResidentsInRoom(String searchedName) {

        for(int i = 0; i < roomsInHotel.size(); i++){

            if(roomsInHotel.get(i).getResidentsInRoom().size() == 0){
                break;
            }
            else if(roomsInHotel.get(i).getResidentsInRoom().size() != 0){

                for(Resident resident: roomsInHotel.get(i).getResidentsInRoom()){
                    if(resident.getName().equalsIgnoreCase(searchedName)){
                        return resident;
                    }
                }
            }
            else{
                roomsInHotel.get(i).printResidents();
            }
        }
        View.getInstance().showMessage("Sorry! No Residents Have Checked In!");
        return null;
    }

    




}

