package com.anwar.LakeSide_Hotel.model;
import org.apache.commons.lang3.RandomStringUtils;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor


public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomType;

    private BigDecimal roomPrice;

    private boolean isBooked=false;

    @Lob  //for large object
    private Blob photo;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    List<BookedRoom> bookings;

    public Room() {
        this.bookings = new ArrayList<>();
        //to avoid null pointer exception
    }

    public void addBooking(BookedRoom booking) {
        if(bookings==null){
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
         booking.setRoom(this);
         isBooked=true;
         String bookingCode=RandomStringUtils.randomNumeric(10);
         booking.setBookingConfirmationCode(bookingCode);

    }
    /*

    public Long getId() {
        return id;
    }

    public List<BookedRoom> getBookings() {
        return bookings;
    }

    public Blob getPhoto() {
        return photo;
    }

    public boolean isBooked() {
        return isBooked;
    }


    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public String getRoomType() {
        return roomType;
    }

     */

}
