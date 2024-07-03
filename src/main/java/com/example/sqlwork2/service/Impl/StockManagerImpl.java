package com.example.sqlwork2.service.Impl;

import com.example.sqlwork2.Exception.BizException;
import com.example.sqlwork2.entity.Warehouse;
import com.example.sqlwork2.enums.BizError;
import com.example.sqlwork2.mapper.WarehouseMapper;
import com.example.sqlwork2.service.StockManager;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockManagerImpl implements StockManager {

    @Autowired
    private WarehouseMapper warehouseMapper;

    Warehouse warehouse = new Warehouse();

    /**
     * 添加物件，参数为编号、数量
     * @param id
     * @param amount
     * @return
     */
    @Override
    public JSONObject checkIn(int id, int amount) {

        JSONObject outputdata = new JSONObject();

        if (id > 0 & id <= 10000){
            if (amount >= 0) {
                int result = warehouseMapper.addNew(id, amount);
                if (result != 0) {
                    outputdata.put("retcode", "0");
                    outputdata.put("retmsg", "添加成功");
                    outputdata.put("物品库存：", amount);
                    log.info("添加物品成功");
                } else {
                    log.info("插入数据异常");
                    throw new BizException(BizError.INSERT_FAILED);
                }
            }
            //插入的库存数为负数
            else {
                log.info("新物品录入插入数据不能小于0");
                throw new BizException(BizError.INSERT_LESS_ZERO);
            }
        }
        else {
            log.info("ID不能小于或为0，不能大于10000");
            throw new BizException(BizError.ID_BEYOND_RULE);
        }
        return outputdata;
    }

    /**
     * 根据物件编号查询物件信息,参数为编号
     * @param id
     * @return
     */
    @Override
    public JSONObject getItemById(int id){

        JSONObject outputdata = new JSONObject();

        if (id > 0 & id <= 10000){
            warehouse = warehouseMapper.findByid(id);
            //查询成功
            if (warehouse != null){
                outputdata.put("retcode","0");
                outputdata.put("retmsg","查询成功");
                outputdata.put("物件id：",warehouse.getId());
                outputdata.put("物件库存量：",warehouse.getAmount());
                log.info("查询物品成功，物品id："+warehouse.getId()+"   物件库存量："+warehouse.getAmount());
            }
            //查询失败
            else {
//                outputdata.put("retcode","2");
//                outputdata.put("retmsg","查询失败");
                log.info("查询数据异常");
                throw new BizException(BizError.SELECT_FAILED);
            }
        }else {
//            outputdata.put("retcode","1");
//            outputdata.put("retmsg","id不能小于或为0，且不能大于10000");
            log.info("ID不能小于或为0，不能大于10000");
            throw new BizException(BizError.ID_BEYOND_RULE);
        }
        return outputdata;
    }

    /**
     * 根据物件编号修改库存数量，参数为编号，数量
     * @param id
     * @param amount
     * @return
     */
    @Override
    public JSONObject updateAmountById(int id,int amount){

        JSONObject outputdata = new JSONObject();

        if (id > 0 & id <= 10000){
            if (amount >= 0){
                //进行操作
                int result =  warehouseMapper.udatByid(id,amount);
                //当库存数更改成功时
                if (result != 0) {
                    outputdata.put("retcode","0");
                    outputdata.put("retmsg","更改成功,现在  ID："+id+"  的货物数量为："+amount);
                    log.info("更改数据成功,现在  ID："+id+"  的货物数量为："+amount);
                }
                //更改失败
                else {
                    log.info("更改数据异常");
                    throw new BizException(BizError.UPDATE_FAILED);
                }
            }
            //修改数小于0
            else {
                log.info("修改库存数量不能小于0");
                throw new BizException(BizError.AMOUNT_CANT_LESS0);
            }
        }
        //ID不合规
        else {
            log.info("ID不能小于或为0，不能大于10000");
            throw new BizException(BizError.ID_BEYOND_RULE);
        }

        return outputdata;
    }

    /**
     * 根据物件编号查询当前库存量
     * @param id
     * @return
     */
    @Override
    public JSONObject checkAmountById(int id){

        JSONObject outputdata = new JSONObject();

        if (id > 0 & id <= 10000){
            warehouse = warehouseMapper.findByid(id);
            //查询成功
            if (warehouse != null){
                outputdata.put("retcode","0");
                outputdata.put("retmsg","查询成功");
                outputdata.put("ID为："+id+"  的物件当前库存量：",warehouse.getAmount());
                log.info("查询数据成功,ID为："+id+"  的物件当前库存量："+warehouse.getAmount());
            }
            //查询失败
            else {
                log.info("查询数据异常");
                throw new BizException(BizError.SELECT_FAILED);
            }
        }
        //ID不合规
        else{
            log.info("ID不能小于或为0，不能大于10000");
            throw new BizException(BizError.ID_BEYOND_RULE);
        }
        return outputdata;
    }

    /**
     * 物件出库，扣减库存
     * @param id
     * @param amount
     * @return
     */
    @Override
    public JSONObject checkOut(int id,int amount){

        JSONObject outputdata = new JSONObject();

        if (id > 0 & id <= 10000){
            warehouse = warehouseMapper.findByid(id);
            if (warehouse != null){
                //有数据,扣除数大于0
                if (amount > 0){
                    //且物品库存数大于等于扣除数
                    if(warehouse.getAmount() >= amount){
                        //原有库存数
                        int amountOR = warehouse.getAmount();
                        int result = warehouseMapper.minusByid(id,amount);
                        //扣除成功
                        if (result != 0){
                            outputdata.put("retcode","0");
                            outputdata.put("retmsg","扣除成功,现在ID："+id+"  的物品库存为："+(amountOR-amount));
                            log.info("扣除数据成功,现在ID："+id+"  的物品库存为："+(amountOR-amount));
                        }
                        else {
                            log.info("库存扣除异常");
                            throw new BizException(BizError.MINUS_FAILED);
                        }
                    }
                    //物品库存数小于扣除数
                    else {
                        log.info("扣除数不能大于物品库存数");
                        throw new BizException((BizError.AMOUNT_LESS_MINUS));
                    }
                }
                //有数据,但扣除数小于或为0
                else{
                    log.info("扣除数不能小于或为0");
                   throw new BizException(BizError.MINUS_CANT_LESS0);
                }
            }
            //无
            else  {
                log.info("库存扣除异常");
                throw new BizException(BizError.MINUS_FAILED);
            }
        }
        //ID不合规
        else{
            log.info("ID不能小于或为0，不能大于10000");
            throw new BizException(BizError.ID_BEYOND_RULE);
        }

        return outputdata;
    }

    /**
     * 查询仓库中库存的总数
     * @param
     * @return
     */
    @Override
    public JSONObject countAll(){
        JSONObject outputdata = new JSONObject();
        int result =  warehouseMapper.checkallAmount();
        if (result >= 0){
            outputdata.put("retcode","0");
            outputdata.put("retmsg","查询成功");
            outputdata.put("全部物品存量：",result);
            log.info("查询成功，全部物品库存量为："+result);
        }
        else
        {
            log.info("查询数据异常");
            throw new BizException(BizError.SELECT_FAILED);
        }
        return outputdata;
    }

    public JSONObject HAHA(String a){
        JSONObject data = new JSONObject();
        data.put("cisNo","111");
        return data;
    }
}