package com.xw.service;

import com.xw.swing.elastic.domain.entity.TempIndexDefinitionEntity;

import java.util.List;

public interface IndexDefinitionService {

    List<TempIndexDefinitionEntity> findByIndexId(String indexId);

    void save(TempIndexDefinitionEntity tempIndexDefinitionEntity);

    void saveAll(List<TempIndexDefinitionEntity> entityList);

    TempIndexDefinitionEntity getById(String id);



    void removeByIndexId(String id);
}
