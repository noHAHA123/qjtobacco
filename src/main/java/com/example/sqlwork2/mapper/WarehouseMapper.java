package com.example.sqlwork2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sqlwork2.entity.Warehouse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    //insert into user(id, username, birthday, sex, address) values(#{id}, #{username}, #{birthday}, #{sex}, #{address})

    //新插入物品种类
    @Insert("insert into warehouse (id,amount) values (#{id},#{amount})")
    public int addNew(int id,int amount);

    //根据id来查物品信息
    @Select("select * from warehouse where id = #{id}")
    public Warehouse findByid(int id);

    //根据id来修改库存数
    @Update("update warehouse set amount = #{amount} where id = #{id}")
    public int udatByid(int id,int amount);

    //根据id来减少库存数
    @Update("update warehouse set amount = (amount - #{amount}) where id = #{id}")
    public int minusByid(int id,int amount);

    //查询所有库存数
    @Select("select sum(amount) from warehouse")
    public int checkallAmount();

    //根据id来增加库存数
    @Update("update warehouse set amount = (amount + #{amount}) where id = #{id}")
    public int add(int id,int amount);

}
