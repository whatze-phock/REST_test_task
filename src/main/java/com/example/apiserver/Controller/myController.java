package com.example.apiserver.Controller;

import com.example.apiserver.DTO.StatusDTO;
import com.example.apiserver.Model.User;
import com.example.apiserver.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class myController {
    @Autowired
    private UserRepo userRepo;


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<Object>(user, HttpStatus.BAD_REQUEST);
        }
        userRepo.save(user);
        return new ResponseEntity<Object>(user.getId(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        if (userRepo.findById(id).isEmpty()) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        User user = userRepo.findById(id).get();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity changeStatus(@PathVariable("id") Long id, @RequestParam(name = "online") Boolean currentStatus) throws InterruptedException {
        if (userRepo.findById(id).isEmpty()) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Thread.sleep((int) (Math.random() * 5000) + 5000);

        boolean old = userRepo.findById(id).get().isOnline();
        StatusDTO statusDTO = new StatusDTO(id, old, currentStatus);


        User user = userRepo.findById(id).get();
        user.setOnline(currentStatus);
        user.setTime(timestamp);
        userRepo.save(user);


        return new ResponseEntity<StatusDTO>(statusDTO, HttpStatus.OK);
    }




    @RequestMapping(value = "/stats", method = RequestMethod.POST)
    public List<User> getStats(@RequestParam(name = "status", required = false) Boolean online,
                               @RequestParam(name = "id", required = false) Long id) {
        if ((id != null)) {
            if ((!(online == null))) {
                return userRepo.findAllByTimeAfterAndOnline(userRepo.findById(id).get().getTime(), online);
            }
                return userRepo.findAllByTimeAfter(userRepo.findById(id).get().getTime());
        }

        else if (online != null) {
            return userRepo.findAllByOnline(online);
        } else {
            return userRepo.findAll();
        }
    }
}







