package com.mushroom.analyzer.backend.exception.pojo;

import com.mushroom.analyzer.backend.model.dto.res.ErrorRespDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SWErrorResponse {

    private ErrorRespDto error;

    public SWErrorResponse(String code, String type, String message) {
        ErrorRespDto errorResponse = new ErrorRespDto();
        errorResponse.setCode(code);
        errorResponse.setType(type);
        errorResponse.setMessage(message);

        this.error = errorResponse;
    }
}
