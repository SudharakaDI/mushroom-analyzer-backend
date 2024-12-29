package com.mushroom.analyzer.backend.exception.pojo;

import com.mushroom.analyzer.backend.utils.Constant;
import lombok.Getter;


@Getter
public enum SWExceptionCode {

    MASW001(Constant.ERROR_TYPE_SERVER),
    MASW002(Constant.ERROR_TYPE_INVALID_INPUT), //No resource found exception occurs
    MASW003(Constant.ERROR_TYPE_INVALID_INPUT), //Method Argument Not Valid Exception

    //Production
    MAPR001(Constant.ERROR_TYPE_INVALID_INPUT), // Production not found with the given id

    //Pot Stock
    MAPS001(Constant.ERROR_TYPE_INVALID_INPUT), // Pot Stock not found with the given id

    //Stake holder
    MASH001(Constant.ERROR_TYPE_INVALID_INPUT); // Stakeholder not found with the given id


    private final String errorType;

    SWExceptionCode(String type) {
        this.errorType = type;
    }

    public String getType() {
        return errorType;
    }
}

