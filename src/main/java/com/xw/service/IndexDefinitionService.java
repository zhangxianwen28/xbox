package com.xw.service;

import com.xw.swing.elastic.domain.entity.TempIndexDefinitionEntity;

import java.util.List;

public interface IndexDefinitionService {

    List<TempIndexDefinitionEntity> findByIndexId(String indexId);

    void save(TempIndexDefinitionEntity tempIndexDefinitionEntity);

    TempIndexDefinitionEntity getById(String id);
}
