package com.xw.elastic.repository;

import com.xw.elastic.domain.entity.TempIndexDefinitionEntity;
import org.springframework.data.repository.CrudRepository;

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

}
