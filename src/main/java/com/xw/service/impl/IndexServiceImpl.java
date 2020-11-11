package com.xw.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import com.xw.repository.IndexRepository;
import com.xw.swing.elastic.domain.entity.TempIndexEntity;
import com.xw.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    IndexRepository indexRepository;


    @Override
    public Page<TempIndexEntity> page(EsIndexVO query, int page, int size) {
        Specification<TempIndexEntity> specification = (Specification<TempIndexEntity>) (root, criteriaQuery, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(query.getId())) {
                predicates.add(builder.equal(root.get("id"), query.getId()));
            }
            if (StringUtils.isNotBlank(query.getIndexName())) {
                predicates.add(builder.equal(root.get("indexName"), query.getIndexName()));
            }
            if (StringUtils.isNotBlank(query.getIndexAlia())) {
                predicates.add(builder.equal(root.get("indexAlia"), query.getIndexAlia()));
            }
            if (StringUtils.isNotBlank(query.getStatus())) {
                predicates.add(builder.equal(root.get("status"), query.getStatus()));
            }
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        return indexRepository.findAll(specification, PageRequest.of(page, size));
    }

    @Override
    public long count() {
        return indexRepository.count();
    }

    @Override
    public TempIndexEntity save(TempIndexEntity tempIndexEntity) {
        return indexRepository.save(tempIndexEntity);
    }

    @Override
    public TempIndexEntity getById(String id) {
        return indexRepository.findById(id).orElse(null);
    }

    @Override
    public void update(TempIndexEntity tempIndexEntity) {
        indexRepository.save(tempIndexEntity);
    }

    @Override
    public void remove(String id) {
        indexRepository.deleteById(id);
    }
}
