package com.xw.elastic.domain.dto;

import lombok.Data;

@Data

public class EsIndexQuery {

    private String id;

    private String indexName;

    private String indexAlia;

    private String status;

}
