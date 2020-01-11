package com.sise.ccj.admin.controller;

import com.sise.ccj.pojo.Move;
import com.sise.ccj.request.move.MoveRequest;
import com.sise.ccj.service.MoveService;
import com.sise.ccj.vo.HttpBody;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName MoveController
 * @Description
 * @Author CCJ
 * @Date 2019/12/29 15:38
 **/
@RestController
@RequestMapping("/move")
public class MoveController {
    @Autowired
    private MoveService moveService;

    @PostMapping("/addMove")
    public HttpBody addMove(@RequestBody MoveRequest move){
        moveService.addMove(move);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/selectMove")
    public HttpBody selectMove(){
        return HttpBody.getSucInstance(moveService.selectMove());
    }

    @PostMapping("/updateMove")
    public HttpBody updateMove(@RequestBody Move move){

        return HttpBody.SUCCESS;
    }

    @PostMapping("/delMove")
    public HttpBody delMove(@RequestBody String id){
        moveService.delMove(new ObjectId(id));
        return HttpBody.SUCCESS;
    }
}
