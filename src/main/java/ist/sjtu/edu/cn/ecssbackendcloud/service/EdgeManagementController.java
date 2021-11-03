package ist.sjtu.edu.cn.ecssbackendcloud.service;


import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EdgeManagementController {

    @GetMapping("/edge/list")
    public void getEdgeList() {

    }

    @GetMapping("/edge")
    public void getEdgeInfo(@RequestParam("edgeId") Long edgeId) {

    }

    @PostMapping("/edge/register")
    public void registerEdge() {
        
    }

}
