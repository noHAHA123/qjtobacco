package com.example.sqlwork2.controller;

import com.example.sqlwork2.Util.PDFUtil;
import com.example.sqlwork2.service.Impl.StockManagerImpl;
import com.example.sqlwork2.service.SealService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/seal")
public class SealTestController {
    @Autowired
    private SealService sealService;
    @RequestMapping(value = "/pdfGo",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getPdf() throws Exception{
        sealService.sealDeal();
    }
}
