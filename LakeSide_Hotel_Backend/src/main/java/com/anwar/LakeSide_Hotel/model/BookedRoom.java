package com.anwar.LakeSide_Hotel.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name="check_In")
    private LocalDate checkInDate;

    @Column(name="check_Out")
    private LocalDate checkOutDate;

    @Column(name = "guest_FullName")
    private String guestFullName;

    @Column(name = "guest_Email")
    private String guestEmail;

    @Column(name = "adults")
    private int numOfAdults;

    @Column(name = "children")
    private int numOfChildren;

    @Column(name = "total_guests")
    private int totalNumOfGuests;

    @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;//to get id of the room

    //specific implementation for getter and setter
    public void calculateTotalNumberOfGuests(){
        this.totalNumOfGuests = this.numOfAdults + numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
        calculateTotalNumberOfGuests();//for current updation of the guest if changes
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
        calculateTotalNumberOfGuests();//if any changes done by the guest ,it should also reflect to the repository

    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
        //for current updation of the confirmation code if changes

    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
