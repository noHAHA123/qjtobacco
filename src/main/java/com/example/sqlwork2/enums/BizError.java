package com.example.sqlwork2.enums;
import com.example.sqlwork2.enums.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum BizError implements Error {
    SELECT_FAILED(1,"查询数据异常"),
    INSERT_FAILED(6,"插入数据异常"),
    UPDATE_FAILED(45,"更改数据异常"),
    MINUS_FAILED(68,"库存扣除异常"),
    ID_BEYOND_RULE(118,"ID不能小于或为0，不能大于10000"),
    INSERT_LESS_ZERO(198,"新物品录入插入数据不能小于0"),
    AMOUNT_CANT_LESS0(348,"修改库存数量不能小于0"),
    MINUS_CANT_LESS0(648,"扣除数不能小于或为0"),
    AMOUNT_LESS_MINUS(1298,"扣除数不能大于物品库存数"),
    ;

    @Getter
    private final int code;

    @Getter
    private final String message;
}