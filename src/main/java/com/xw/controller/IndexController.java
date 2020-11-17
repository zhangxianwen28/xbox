package com.xw.controller;

import com.xw.service.impl.IndexDefinitionServiceImpl;
import com.xw.service.impl.IndexServiceImpl;
import com.xw.swing.education.domain.dto.PageWrapper;
import com.xw.swing.elastic.domain.entity.TempIndexDefinitionEntity;
import com.xw.swing.elastic.domain.entity.TempIndexEntity;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import com.xw.swing.elastic.domain.vo.IndexDefVO;
import com.xw.util.SpringContextUtil;
import com.xw.util.learn.tree.Tree;
import com.xw.util.other.IDGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IndexController {

    public EsIndexVO getEsIndexById(String id) {
        IndexServiceImpl bean = SpringContextUtil.getBean(IndexServiceImpl.class);
        TempIndexEntity result = bean.getById(id);
        return transformVO(result);
    }

    public PageWrapper<EsIndexVO> getEsIndexPage(EsIndexVO query, int page, int size) {
        IndexServiceImpl bean = SpringContextUtil.getBean(IndexServiceImpl.class);
        Page<TempIndexEntity> result = bean.page(query, page - 1, size);
        List<EsIndexVO> esIndexVOS = transformVO(result);
        PageWrapper<EsIndexVO> pageWrapper = new PageWrapper();
        pageWrapper.setData(esIndexVOS);
        pageWrapper.setTotalNum((int) result.getTotalElements());
        return pageWrapper;
    }


    public void saveIndex(EsIndexVO esIndexVO, List<IndexDefVO> vos) {
        if (esIndexVO == null) {
            return;
        }

        if (esIndexVO.getId() == null) {
            esIndexVO.setId(String.valueOf(IDGenerator.getId()));
        }
        esIndexVO.setStatus("未发布");

        TempIndexEntity tempIndexEntity = transformEntity(esIndexVO);
        IndexServiceImpl bean = SpringContextUtil.getBean(IndexServiceImpl.class);
        bean.save(tempIndexEntity);
        if (vos != null && !vos.isEmpty()) {
            List<TempIndexDefinitionEntity> entityList = new ArrayList<>();
            for (IndexDefVO vo : vos) {
                vo.setIndexId(esIndexVO.getId());
                TempIndexDefinitionEntity definitionEntity = new TempIndexDefinitionEntity();
                BeanUtils.copyProperties(vo, definitionEntity);
                entityList.add(definitionEntity);
            }
            IndexDefinitionServiceImpl bean2 = SpringContextUtil.getBean(IndexDefinitionServiceImpl.class);
            bean2.saveAll(entityList);
        }

    }

    public List<IndexDefVO> getIndexDefByIndexId(String indexId) {
        IndexDefinitionServiceImpl bean2 = SpringContextUtil.getBean(IndexDefinitionServiceImpl.class);
        List<TempIndexDefinitionEntity> tempIndexDefinitionEntities = bean2.findByIndexId(indexId);
        List<IndexDefVO> indexDefVOS = new ArrayList<>();
        for (TempIndexDefinitionEntity tempIndexDefinitionEntity : tempIndexDefinitionEntities) {
            IndexDefVO indexDefVO = new IndexDefVO();
            BeanUtils.copyProperties(tempIndexDefinitionEntity, indexDefVO);
            indexDefVOS.add(indexDefVO);
        }
        return indexDefVOS;
    }

    public List<Tree<IndexDefVO>> getIndexDefVOTree(String indexId) {
        List<IndexDefVO> indexDefByIndexId = getIndexDefByIndexId(indexId);
        return Tree.buildTree(indexDefByIndexId.stream().map(x -> {
            Tree<IndexDefVO> t = new Tree<>();
            t.setData(x);
            t.setId(x.getId());
            t.setPid(x.getPid());
            return t;
        }).collect(Collectors.toList()));
    }


    public void removeIndex(String id) {
        IndexServiceImpl bean = SpringContextUtil.getBean(IndexServiceImpl.class);
        bean.remove(id);
        IndexDefinitionServiceImpl bean2 = SpringContextUtil.getBean(IndexDefinitionServiceImpl.class);
        bean2.removeByIndexId(id);

    }


    private EsIndexVO transformVO(TempIndexEntity entity) {
        EsIndexVO esIndexVO = new EsIndexVO();
        BeanUtils.copyProperties(entity, esIndexVO);
        return esIndexVO;
    }

    private List<EsIndexVO> transformVO(Page<TempIndexEntity> page) {
        return page.map(this::transformVO).stream().collect(Collectors.toList());
    }

    private TempIndexEntity transformEntity(EsIndexVO esIndexVO) {
        if (esIndexVO == null) {
            return null;
        }
        TempIndexEntity tempIndexEntity = new TempIndexEntity();

        BeanUtils.copyProperties(esIndexVO, tempIndexEntity);
        return tempIndexEntity;
    }
}
