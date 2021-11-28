package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.service.DataPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/dataPackage")
public class DataPackageManagementController {

    @Autowired
    private DataPackageService dataPackageService;

    @PostMapping(value = "/{edgeId}")
    public ResponseEntity<?> receiveDataPackage(@RequestBody MultipartFile file, @PathVariable String edgeId) {
        return new ResponseEntity<>(dataPackageService.receiveDataPackage(edgeId, file), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getDataPackage() {
        return new ResponseEntity<>(dataPackageService.getDataPackage(), HttpStatus.OK);
    }
}