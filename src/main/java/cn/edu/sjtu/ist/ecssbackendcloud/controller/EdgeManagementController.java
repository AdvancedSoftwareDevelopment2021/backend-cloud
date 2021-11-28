package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDto;
import cn.edu.sjtu.ist.ecssbackendcloud.service.EdgeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/edge")
public class EdgeManagementController {

    @Autowired
    private EdgeManagementService edgeManagementService;

    @PostMapping()
    public ResponseEntity<?> insertEdgeInfo(@RequestBody EdgeInfoDto request) {
        return new ResponseEntity<>(edgeManagementService.addEdge(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEdgeInfo(@PathVariable String edgeId) {
        return new ResponseEntity<>(edgeManagementService.deleteEdgeInfoById(edgeId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEdgeInfo(@PathVariable String edgeId, @RequestBody EdgeInfoDto edgeInfoDto) {
        return new ResponseEntity<>(edgeManagementService.updateEdgeInfoById(edgeId, edgeInfoDto), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getEdgeList() {
        return new ResponseEntity<>(edgeManagementService.getAllEdgeInfo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEdgeInfo(@PathVariable String edgeId) {
        return new ResponseEntity<>(edgeManagementService.getEdgeInfoById(edgeId), HttpStatus.OK);
    }

    @GetMapping("/ping/{id}")
    public ResponseEntity<?> pingEdge(@PathVariable String edgeId) {
        return new ResponseEntity<>(edgeManagementService.pingEdge(edgeId), HttpStatus.OK);
    }

}
