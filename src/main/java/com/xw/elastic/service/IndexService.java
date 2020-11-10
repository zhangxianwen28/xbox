package com.xw.elastic.service;

import com.xw.elastic.domain.entity.TempIndexEntity;
import com.xw.elastic.domain.vo.component.EsIndexVO;
import org.springframework.data.domain.Page;

public interface IndexService {
    Page<TempIndexEntity> page(EsIndexVO query, int page , int size);

    long count();

    TempIndexEntity save(TempIndexEntity tempIndexEntity);

    TempIndexEntity getById(String id);

    void update(TempIndexEntity tempIndexEntity);

    void remove(String id);

}
