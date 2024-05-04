package com.ia.indieAn.common.responseEntity;

import lombok.Data;

@Data
public class ResponseTemplate {

    private StatusEnum status;
    private Object data;
}
