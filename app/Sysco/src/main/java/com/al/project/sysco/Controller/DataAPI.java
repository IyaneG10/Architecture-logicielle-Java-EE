package com.al.project.sysco.Controller;


import com.al.project.sysco.Service.DataService;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;


@RestController
@RequestMapping("/api-datas")
public class DataAPI {


    DataService dataService = new DataService("jdbc:mariadb://localhost:3306/sysco", "admin", "admin");


    @GetMapping("historic/all")
    public ResponseEntity<String> findAllDatas() throws SQLException, JSONException {

        return new ResponseEntity<String>(dataService.getDataByAll()+ "", HttpStatus.OK);
    }

    @GetMapping("historic/room{room_id}")
    public ResponseEntity<String> findDataByRoom(@PathVariable("room_id") int id)  throws SQLException, JSONException {

        return new ResponseEntity<String>(dataService.getDataByRoom(id)+ "", HttpStatus.OK);
    }

    @GetMapping("historic/room{room_id}/{measureName}")
    public ResponseEntity<String> getDataByRoomAndSensor(@PathVariable("room_id") int room_id,@PathVariable("measureName") String measureName)  throws SQLException, JSONException {

        return new ResponseEntity<String>(dataService.getDataByRoomAndSensor(room_id, measureName)+ "", HttpStatus.OK);
    }

    @GetMapping("realtime/room{room_id}")
    public ResponseEntity<String> getRealTimeDataByRoom(@PathVariable("room_id") int room_id)  throws SQLException, JSONException {
        return new ResponseEntity<String>(dataService.getRealTimeData(room_id)+ "", HttpStatus.OK);
    }


    @GetMapping("historic/roomlist")
    public ResponseEntity<String> getRoom()  throws SQLException, JSONException {

        return new ResponseEntity<String>(dataService.getRoom()+ "", HttpStatus.OK);
    }

}
