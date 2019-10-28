package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Room implements Serializable {

    /**
     * Room
     * Room Class
     */

    private ArrayList<Resident> residentsInRoom = new ArrayList<>();
    private int roomNumber;
    private RoomId roomType;


    public enum RoomId{
        SUITE("Suite",4),
        DOUBLE("Double",2);

        private String roomIdType;
        private int roomMaxResidents;

        private RoomId(String roomIdType,int roomMaxResidents){
            this.roomIdType = roomIdType;
            this.roomMaxResidents = roomMaxResidents;
        }

    }
    public Room(int roomNumber, RoomId roomType){
        this.roomNumber = roomNumber;
        this.roomType = roomType;

    }

    /**
     * Displays if room is available for check in.
     */
    public void showRoomAvailability(){
        if(residentsInRoom.size() == 0){
            System.out.println("Room nr: " + roomNumber + " Roomtype: " + roomType.roomIdType + ": Is Available.");
        }else {
            System.out.println("Room nr: " + roomNumber + " Roomtype: " + roomType.roomIdType + ": Is < UnAvailable >");
        }

    }

    /**
     * Displays if room is empty or occupied.
     */

    public void showRoomStatus(){
        if(residentsInRoom.size()== 0) {
            System.out.println("Room nr: " + roomNumber + " - ........");
        }
        else {
            System.out.println("Room nr: " + roomNumber + " - Occupied");
        }
    }

    /**
     * Method to check if room has residents checked in.
     * @param number of room
     * @return true if room is available.
     */
    public boolean isAvailable(int number){

        if(residentsInRoom.size() == 0 && roomNumber == number){
            return true;
        }
        return false;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public RoomId getRoomType(){
        return roomType;
    }

    /**
     * Displays people in room.
     */
    public void showPeopleInRoom(){


        for(Resident resident: residentsInRoom){

            resident.showInfo(); View.getInstance().showMessage("And I Stay In Room: " + getRoomNumber() +
                    " In a " + getRoomType().roomIdType);
        }
    }

    public ArrayList<Resident> getResidentsInRoom(){
        return residentsInRoom;
    }

    public void setResidentsInRoom(Resident resident){
        residentsInRoom.add(resident);
    }


}
