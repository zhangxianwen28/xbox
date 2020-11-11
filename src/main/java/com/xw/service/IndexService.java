package com.xw.service;

import com.xw.swing.elastic.domain.entity.TempIndexEntity;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import org.springframework.data.domain.Page;

public interface IndexService {
    Page<TempIndexEntity> page(EsIndexVO query, int page , int size);

    long count();

    TempIndexEntity save(TempIndexEntity tempIndexEntity);

    TempIndexEntity getById(String id);

    void update(TempIndexEntity tempIndexEntity);

    void remove(String id);

}
