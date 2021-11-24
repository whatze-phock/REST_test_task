package com.example.apiserver.Controller;

import com.example.apiserver.Model.User;
import com.example.apiserver.Repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/img")
public class imgController {
    @Autowired
    private UserRepo userRepo;
    @Value("${upload.path}")
    private String path;



    @PostMapping("/upload")
    public String upload (@RequestParam(name = "file") MultipartFile file,
                          @RequestParam(name = "id") Long id) throws IOException {
        //String name = file.getOriginalFilename();
        //String original = UUID.randomUUID().toString()+name;
        //file.transferTo(new File(path+name));
        String pathOfFile = path;
        String filename =  file.getOriginalFilename();
        String test = pathOfFile+"/"+filename;
        file.transferTo(new File(test));
        User user = userRepo.findById(id).get();
        user.setImage(filename);
        userRepo.save(user);
        return test;
    }

}
