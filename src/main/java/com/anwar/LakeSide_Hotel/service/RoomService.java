package com.anwar.LakeSide_Hotel.service;

import com.anwar.LakeSide_Hotel.model.Room;
import com.anwar.LakeSide_Hotel.repository.RoomRepository;
import com.anwar.LakeSide_Hotel.service.IRoomService.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;


@Service //why here @Service is used here
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    // Implementation of IRoomService interface here.
    // This service should interact with the database to save room data.
    // You can use Spring Data JPA for database operations.
    // The photo should be saved as a Base64 encoded string.
    // This method should also handle the case when the room already exists in the database.
    // Return the saved room object.
    // You can assume that the necessary dependencies are already set up.
    private  final RoomRepository roomRepository;
    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException {
        // Implementation here
        // Save room data to the database.
        // Convert the photo to Base64 encoded string.
        // Create a new Room object with the provided data.
        // Save the room in the database.
        // Return the saved room object.
        Room room = new Room();
        // Implement the logic to save room data to the database.
        // Implement the logic to convert the photo to Base64 encoded string.
        // Set the roomType, roomPrice, and photo fields of the Room object.
        // Save the room in the database using the roomRepository.save(room) method.
        // Return the saved room object.
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if(!file.isEmpty()){
            byte[] photoBytes = file.getBytes();
            Blob photoBlob =new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room);
    }
}
