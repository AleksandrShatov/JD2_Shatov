package com.noirix.controller.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation(value = "Class for creating location entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationCreateRequest {

    private String country;

    private String city;

    private Double latitude;

    private Double longitude;

}
