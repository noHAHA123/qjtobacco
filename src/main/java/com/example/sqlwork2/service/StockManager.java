package com.example.sqlwork2.service;
import net.sf.json.JSONObject;

public interface StockManager {

    /**
     * 添加物件，参数为编号、数量
     * @param id
     * @param amount
     * @return
     */
    JSONObject checkIn(int id, int amount);

    /**
     * 根据物件编号查询物件信息,参数为编号
     * @param id
     * @return
     */
    JSONObject getItemById(int id);

    /**
     * 根据物件编号修改库存数量，参数为编号，数量
     * @param id
     * @param amount
     * @return
     */
    JSONObject updateAmountById(int id,int amount);

    /**
     * 根据物件编号查询当前库存量
     * @param id
     * @return
     */
    JSONObject checkAmountById(int id);

    /**
     * 物件出库，扣减库存
     * @param id
     * @param amount
     * @return
     */
    JSONObject checkOut(int id,int amount);

    /**
     * 查询仓库中库存的总数
     * @param
     * @return
     */
    JSONObject countAll();

}
