package com.xw.service.impl;

import com.xw.repository.IndexDefinitionRepository;
import com.xw.service.IndexDefinitionService;
import com.xw.swing.elastic.domain.entity.TempIndexDefinitionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexDefinitionServiceImpl implements IndexDefinitionService {

    @Autowired
    IndexDefinitionRepository indexDefinitionRepository;

    @Override
    public List<TempIndexDefinitionEntity> findByIndexId(String indexId) {
        return indexDefinitionRepository.findByIndexId(indexId);
    }

    @Override
    public void save(TempIndexDefinitionEntity tempIndexDefinitionEntity) {
        indexDefinitionRepository.save(tempIndexDefinitionEntity);
    }

    @Override
    public void saveAll(List<TempIndexDefinitionEntity> entityList) {
        indexDefinitionRepository.saveAll(entityList);
    }
    @Override
    public TempIndexDefinitionEntity getById(String id) {
        return indexDefinitionRepository.findById(id).orElse(null);
    }


}
