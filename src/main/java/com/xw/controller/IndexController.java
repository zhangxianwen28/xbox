package com.xw.controller;

import com.xw.service.impl.IndexServiceImpl;
import com.xw.swing.education.domain.dto.PageWrapper;
import com.xw.swing.elastic.domain.entity.TempIndexDefinitionEntity;
import com.xw.swing.elastic.domain.entity.TempIndexEntity;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import com.xw.util.SpringContextUtil;
import com.xw.util.other.IDGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class IndexController {

    public PageWrapper<EsIndexVO> getEsIndexPage(EsIndexVO query, int page, int size){
        IndexServiceImpl bean = SpringContextUtil.getBean(IndexServiceImpl.class);
        Page<TempIndexEntity> result = bean.page(query, page-1, size);
        List<EsIndexVO> esIndexVOS = transformVO(result);
        PageWrapper<EsIndexVO> pageWrapper = new PageWrapper();
        pageWrapper.setData(esIndexVOS);
        pageWrapper.setTotalNum((int) result.getTotalElements());
        return pageWrapper;
    }

    public void save(EsIndexVO esIndexVO){
        TempIndexEntity tempIndexEntity = transformEntity(esIndexVO);
        IndexServiceImpl bean = SpringContextUtil.getBean(IndexServiceImpl.class);
        bean.save(tempIndexEntity);
        //List<TempIndexDefinitionEntity> tempIndexDefinitionEntities = esIndexVO.getTempIndexDefinitionEntities();
    }



    private List<EsIndexVO> transformVO(Page<TempIndexEntity> page) {
        return  page.map(x -> {
            EsIndexVO esIndexVO = new EsIndexVO();
            BeanUtils.copyProperties(x, esIndexVO);
            return esIndexVO;
        }).stream().collect(Collectors.toList());
    }

    private TempIndexEntity transformEntity(EsIndexVO esIndexVO){
        if(esIndexVO==null){
            return null;
        }
        TempIndexEntity tempIndexEntity = new TempIndexEntity();
        BeanUtils.copyProperties(esIndexVO, tempIndexEntity);
        return  tempIndexEntity;
    }
}
