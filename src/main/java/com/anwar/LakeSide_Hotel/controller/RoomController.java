package com.anwar.LakeSide_Hotel.controller;

import com.anwar.LakeSide_Hotel.service.IRoomService.IRoomService;

import lombok.RequiredArgsConstructor;

import com.anwar.LakeSide_Hotel.model.Room;
import com.anwar.LakeSide_Hotel.response.RoomResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor

public class RoomController {

	private final IRoomService roomService;

	@PostMapping("/add/new-room")
	public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile photo,
			@RequestParam("roomType") String roomType, @RequestParam("roomPrice") BigDecimal roomPrice)
			throws SQLException, IOException {

		Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
		RoomResponse response = new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(), savedRoom.getRoomPrice());

		return ResponseEntity.ok(response);

	}

}
