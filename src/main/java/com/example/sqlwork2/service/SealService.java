package com.example.sqlwork2.service;

import com.example.sqlwork2.Util.PDFUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SealService {
    public void sealDeal() throws Exception{
        PDFUtil.A();
    }
}
