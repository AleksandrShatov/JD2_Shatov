package com.noirix.controller.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation("Class for aggregate search user params")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    private Long id;

    private String query;
}
