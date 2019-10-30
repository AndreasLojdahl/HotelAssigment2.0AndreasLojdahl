package com.company;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class HotelProgramTest {

    @org.junit.Test
    public void createPerson() {

        String input = "Andreas\n920919-1234";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        HotelProgram hotelProgram = new HotelProgram();
        int numOfAdmins = hotelProgram.getAdminsInHotel().size();


        hotelProgram.createPerson(hotelProgram.getAdminsInHotel(),HotelProgram.MAX_ADMINS,"admins");
        assertEquals(numOfAdmins+1,hotelProgram.getAdminsInHotel().size());
    }
}