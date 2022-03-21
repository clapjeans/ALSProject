package kopo.poly.controller;

import kopo.poly.service.IMongoService;
import kopo.poly.service.impl.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class MongoContorller {

    //Map 객체를 사용한 데이터 처리

    @Resource(name = "MongoService")
    private IMongoService mongoService;

    @GetMapping(value="mongo/test")
    public String test() throws Exception {

        log.info (this.getClass().getName()+". test Start");

        mongoService.mongoTest();

        log.info(this.getClass().getName()+".test End");

        return "mongoDB TEST!";
    }
}
