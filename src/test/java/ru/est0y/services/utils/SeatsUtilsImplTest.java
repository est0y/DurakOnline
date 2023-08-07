package ru.est0y.services.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.est0y.domain.Seat;
import ru.est0y.services.utils.seats.SeatsUtilsImpl;
import utils.SeatsCreator;

import java.util.stream.Collectors;

class SeatsUtilsImplTest {
    private final SeatsCreator seatsCreator = new SeatsCreator();

    @Test
    void sortToDealCards() {
        var seats = seatsCreator.getSeats(5);
        var seatByNumber = seats.stream().collect(Collectors.toMap(Seat::getNumber, s -> s));
        var sortedSeats = new SeatsUtilsImpl().sortToDealCards(seats, seatByNumber.get(1), seatByNumber.get(2));
       // System.out.println(sortedSeats);
        Assertions.assertEquals(1, sortedSeats.get(0).getNumber());
        Assertions.assertEquals(3, sortedSeats.get(1).getNumber());
        Assertions.assertEquals(4, sortedSeats.get(2).getNumber());
        Assertions.assertEquals(5, sortedSeats.get(3).getNumber());
        Assertions.assertEquals(2, sortedSeats.get(4).getNumber());

    }
    @Test
    void sortToDealCards1() {
        var seats = seatsCreator.getSeats(5);
        var seatByNumber = seats.stream().collect(Collectors.toMap(Seat::getNumber, s -> s));
        var sortedSeats = new SeatsUtilsImpl().sortToDealCards(seats, seatByNumber.get(2), seatByNumber.get(3));
      //  System.out.println(sortedSeats);
        Assertions.assertEquals(2, sortedSeats.get(0).getNumber());
        Assertions.assertEquals(4, sortedSeats.get(1).getNumber());
        Assertions.assertEquals(5, sortedSeats.get(2).getNumber());
        Assertions.assertEquals(1, sortedSeats.get(3).getNumber());
        Assertions.assertEquals(3, sortedSeats.get(4).getNumber());

    }
    @Test
    void sortToDealCards2() {
        var seats = seatsCreator.getSeats(5);
        var seatByNumber = seats.stream().collect(Collectors.toMap(Seat::getNumber, s -> s));
        var sortedSeats = new SeatsUtilsImpl().sortToDealCards(seats, seatByNumber.get(4), seatByNumber.get(5));
        System.out.println(sortedSeats);
        Assertions.assertEquals(4, sortedSeats.get(0).getNumber());
        Assertions.assertEquals(1, sortedSeats.get(1).getNumber());
        Assertions.assertEquals(2, sortedSeats.get(2).getNumber());
        Assertions.assertEquals(3, sortedSeats.get(3).getNumber());
        Assertions.assertEquals(5, sortedSeats.get(4).getNumber());

    }
    @Test
    void sortToDealCards3() {
        var seats = seatsCreator.getSeats(5);
        var seatByNumber = seats.stream().collect(Collectors.toMap(Seat::getNumber, s -> s));
        var sortedSeats = new SeatsUtilsImpl().sortToDealCards(seats, seatByNumber.get(5), seatByNumber.get(1));
        System.out.println(sortedSeats);
        Assertions.assertEquals(5, sortedSeats.get(0).getNumber());
        Assertions.assertEquals(2, sortedSeats.get(1).getNumber());
        Assertions.assertEquals(3, sortedSeats.get(2).getNumber());
        Assertions.assertEquals(4, sortedSeats.get(3).getNumber());
        Assertions.assertEquals(1, sortedSeats.get(4).getNumber());

    }
    @Test
    void sortToDealCards4() {
        var seats = seatsCreator.getSeats(2);
        var seatByNumber = seats.stream().collect(Collectors.toMap(Seat::getNumber, s -> s));
        var sortedSeats = new SeatsUtilsImpl().sortToDealCards(seats, seatByNumber.get(1), seatByNumber.get(2));
        System.out.println(sortedSeats);
        Assertions.assertEquals(1, sortedSeats.get(0).getNumber());
        Assertions.assertEquals(2, sortedSeats.get(1).getNumber());
        sortedSeats = new SeatsUtilsImpl().sortToDealCards(seats, seatByNumber.get(2), seatByNumber.get(1));
        Assertions.assertEquals(2, sortedSeats.get(0).getNumber());
        Assertions.assertEquals(1, sortedSeats.get(1).getNumber());
    }
}