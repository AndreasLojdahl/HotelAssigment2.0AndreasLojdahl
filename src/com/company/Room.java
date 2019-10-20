package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

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

    public void showRoomAvailability(){
        if(residentsInRoom.size() == 0){
            System.out.println("Room nr: " + roomNumber + " Roomtype: " + roomType.roomIdType + ": Is Available.");
        }else {
            System.out.println("Room nr: " + roomNumber + " Roomtype: " + roomType.roomIdType + ": Is < UnAvailable >");
        }

    }

    public void showRoomStatus(){
        if(residentsInRoom.size()== 0) {
            System.out.println("Room nr: " + roomNumber + " - ........");
        }
        else {
            System.out.println("Room nr: " + roomNumber + " - Occupied");
        }
    }

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

    public void showPeopleInRoom(){

        for(Resident resident: residentsInRoom){
            resident.showInfo(); View.getInstance().showMessage("And I Stay In Room: " + getRoomNumber() +
                    " In a " + getRoomType());
        }
    }

    public ArrayList<Resident> getResidentsInRoom(){
        return residentsInRoom;
    }

    public void setResidentsInRoom(Resident resident){
        residentsInRoom.add(resident);
    }

    public void printResidents(){
        for(Resident resident: residentsInRoom){
            resident.showInfo();
        }
    }



}
