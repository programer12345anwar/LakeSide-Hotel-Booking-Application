package com.anwar.LakeSide_Hotel.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomResponse {
	// all the attributes of Room Entity is copied here
	private Long id;

	private String roomType;

	private BigDecimal roomPrice;

	private boolean isBooked = false;

	// for large object
	private String photo;

	private List<BookingResponse> bookings;

	public RoomResponse(Long id, String roomType, BigDecimal roomPrice) {
		this.id = id;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
	}

	public RoomResponse(Long id, byte[] photoBytes, List<BookingResponse> bookings, boolean isBooked,
			BigDecimal roomPrice, String roomType) {
		this.id = id;
		this.photo = photoBytes != null ? Base64.getEncoder().encodeToString(photoBytes) : null;
		this.bookings = bookings;
		this.isBooked = isBooked;
		this.roomPrice = roomPrice;
		this.roomType = roomType;
	}
}
