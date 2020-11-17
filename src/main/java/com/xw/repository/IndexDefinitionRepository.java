package com.xw.repository;

import com.xw.swing.elastic.domain.entity.TempIndexDefinitionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2020-11-09
 */
@org.springframework.stereotype.Repository
public interface IndexDefinitionRepository extends CrudRepository<TempIndexDefinitionEntity,String> {
        List<TempIndexDefinitionEntity> findByIndexId(String indexId);

        void removeByIndexId(String indexId);

}
