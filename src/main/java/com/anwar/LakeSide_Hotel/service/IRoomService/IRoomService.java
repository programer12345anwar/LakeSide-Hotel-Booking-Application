package com.anwar.LakeSide_Hotel.service.IRoomService;

import com.anwar.LakeSide_Hotel.model.Room;
import com.anwar.LakeSide_Hotel.repository.RoomRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public interface IRoomService {


    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SQLException;
}
