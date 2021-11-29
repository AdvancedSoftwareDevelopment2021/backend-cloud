package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
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
    public ResponseEntity<?> insertEdgeInfo(@RequestBody EdgeInfoDTO request) {
        return new ResponseEntity<>(edgeManagementService.addEdge(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEdgeInfo(@PathVariable String id) {
        return new ResponseEntity<>(edgeManagementService.deleteEdgeInfoById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEdgeInfo(@PathVariable String id, @RequestBody EdgeInfoDTO edgeInfoDto) {
        return new ResponseEntity<>(edgeManagementService.updateEdgeInfoById(id, edgeInfoDto), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getEdgeList() {
        return new ResponseEntity<>(edgeManagementService.getAllEdgeInfo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEdgeInfo(@PathVariable String id) {
        return new ResponseEntity<>(edgeManagementService.getEdgeInfoById(id), HttpStatus.OK);
    }

    @GetMapping("/ping/{id}")
    public ResponseEntity<?> pingEdge(@PathVariable String id) {
        return new ResponseEntity<>(edgeManagementService.pingEdge(id), HttpStatus.OK);
    }

    @GetMapping("/ping/stop/{id}")
    public ResponseEntity<?> pingStopEdge(@PathVariable String id) {
        return new ResponseEntity<>(edgeManagementService.pingStopEdge(id), HttpStatus.OK);
    }

}
