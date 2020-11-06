package com.xw.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Data
@Document(indexName = "index_bing_v1")
public class BingIndex {
    @Id
    private  String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word" ,searchAnalyzer="ik_max_word")
    private String title;


    @Field(type= FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_max_word" ,searchAnalyzer="ik_max_word")
    private String location;

    @Field(type = FieldType.Keyword)
    private String flag;


    @Field(type = FieldType.Text)
    private String article;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
    @Field(type = FieldType.Date, format = DateFormat.basic_date)
    private LocalDate createDate;

}
