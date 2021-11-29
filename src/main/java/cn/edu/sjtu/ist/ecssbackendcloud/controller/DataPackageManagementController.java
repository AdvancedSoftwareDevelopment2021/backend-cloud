package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.DataPackage;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;
import cn.edu.sjtu.ist.ecssbackendcloud.service.DataPackageService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.MD5Util;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/dataPackage")
public class DataPackageManagementController {

    @Autowired
    private DataPackageService dataPackageService;

    @PostMapping(value = "/{edgeId}")
    public ResponseEntity<?> receiveDataPackage(@RequestBody MultipartFile file, @PathVariable String edgeId) {
        try {
            DataPackage f = new DataPackage();
            f.setName(file.getOriginalFilename());
            f.setEdgeId(edgeId);
            f.setContentType(file.getContentType());
            f.setSize(file.getSize());
            f.setContent(new Binary(file.getBytes()));
            f.setMd5(MD5Util.getMD5(file.getInputStream()));
            return new ResponseEntity<>(dataPackageService.receiveDataPackage(f), HttpStatus.OK);
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new Response(500, "error", ex.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getDataPackage() {
        return new ResponseEntity<>(dataPackageService.getDataPackage(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDataPackage(@PathVariable String id) {
        return new ResponseEntity<>(dataPackageService.deleteDataPackage(id), HttpStatus.OK);
    }
}