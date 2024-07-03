package com.example.sqlwork2.controller;

import com.example.sqlwork2.service.Impl.StockManagerImpl;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private StockManagerImpl smd;

    /**
     * 添加物件，参数为编号、数量
     * @param id
     * @param amount
     * @return
     */
    @RequestMapping(value = "/checkIn",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject checkIn(int id, int amount){
        JSONObject outputdata = new JSONObject();
        System.out.println("  添加新物品 ");
        log.info("  添加新物品开始 ");
        outputdata = smd.checkIn(id,amount);
        log.info("  添加新物品结束 ");
        return outputdata;
    }

    /**
     * 根据物件编号查询物件信息,参数为编号
     * @param id
     * @return
     */
    @RequestMapping(value = "/getItemById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
   // @ResponseBody
    public JSONObject getItemById(int id){
        JSONObject outputdata = new JSONObject();
        System.out.println("  查询物品 ");
        log.info("  查询物品信息开始 ");
        outputdata = smd.getItemById(id);
        log.info("  查询物品信息结束 ");
        return outputdata;
    }
   
    /**
     * 根据物件编号修改库存数量，参数为编号，数量
     * @param id
     * @param amount
     * @return
     */
    @RequestMapping(value = "/updateAmountById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject updateAmountById(int id,int amount){
        JSONObject outputdata = new JSONObject();
        System.out.println("  修改物品库存 ");
        log.info("  修改物品库存开始 ");
        outputdata = smd.updateAmountById(id,amount);
        log.info("  修改物品库存结束 ");
        return outputdata;
    }

    /**
     * 根据物件编号查询当前库存量
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkAmountById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject checkAmountById(int id){
        JSONObject outputdata = new JSONObject();
        System.out.println("  查询物品库存 ");
        log.info("  查询物品库存开始 "); 
        outputdata = smd.checkAmountById(id);
        log.info("  查询物品库存结束 ");
        return outputdata;
    }

    /**
     * 物件出库，扣减库存
     * @param id
     * @param amount
     * @return
     */
    @RequestMapping(value = "/checkOut",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject checkOut(int id,int amount){
        JSONObject outputdata = new JSONObject();
        System.out.println("  减少物品库存 ");
        log.info("  减少物品库存开始 ");
        outputdata = smd.checkOut(id,amount);
        log.info("  减少物品库存结束 ");
        return outputdata;
    }

    /**
     * 查询仓库中库存的总数
     * @param
     * @return
     */
    @RequestMapping(value = "/countAll",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject checkOut(){
        JSONObject outputdata = new JSONObject();
        System.out.println("  查询所有物品库存开始 ");
        log.info("  查询所有物品库存开始 ");
        outputdata = smd.countAll();
        log.info("  查询所有物品库存结束 ");
        return outputdata;
    }

    @RequestMapping(value = "/haha",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject haha(@RequestBody JSONObject object){
        log.info("测试一下");
        return smd.HAHA(object.getString("phone"));
    }

}
