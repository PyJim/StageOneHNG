package com.hng.stageone.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseModel {
    private String client_ip;
    private String location;
    private String greeting;

}
