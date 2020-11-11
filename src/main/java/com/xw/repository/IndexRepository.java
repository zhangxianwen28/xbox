package com.xw.repository;

import com.xw.swing.elastic.domain.entity.TempIndexEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
public interface IndexRepository extends CrudRepository<TempIndexEntity,String> , JpaSpecificationExecutor<TempIndexEntity> {


}
