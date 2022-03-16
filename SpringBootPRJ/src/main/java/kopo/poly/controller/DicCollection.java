package kopo.poly.controller;

import kopo.poly.service.IDicService;
import kopo.poly.service.impl.DicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class DicCollection {

    @Resource(name="DicService")
    private IDicService dicservice;


    //Dictionary페이지
    @GetMapping(value = "search")
    public String listPg(Model model) throws Exception {
        log.info(getClass().getName()+"Start SearchList ");

        List<Map<String,Object>>  Titlelist = dicservice.getList();

        model.addAttribute("list",Titlelist);


        log.info(getClass().getName()+"End SearchList ");
        return"/search/listPg";
    }

}
