package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.DataPackage;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.DataPackageDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;
import cn.edu.sjtu.ist.ecssbackendcloud.service.DataPackageService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.MD5Util;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.Result;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.ResultUtil;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/dataPackage")
public class DataPackageManagementController {

    @Autowired
    private DataPackageService dataPackageService;

    @PostMapping(value = "/{edgeId}")
    public Result receiveDataPackage(@RequestBody MultipartFile file, @PathVariable String edgeId) {
        try {
            DataPackage f = new DataPackage();
            f.setName(file.getOriginalFilename());
            f.setEdgeId(edgeId);
            f.setContentType(file.getContentType());
            f.setSize(file.getSize());
            f.setContent(new Binary(file.getBytes()));
            f.setMd5(MD5Util.getMD5(file.getInputStream()));
            dataPackageService.receiveDataPackage(f);
            return ResultUtil.success();
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    @GetMapping(value = "")
    public Result<List<DataPackageDTO>> getDataPackage() {
        return ResultUtil.success(dataPackageService.getDataPackage());
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteDataPackage(@PathVariable String id) {
        dataPackageService.deleteDataPackage(id);
        return ResultUtil.success();
    }
}