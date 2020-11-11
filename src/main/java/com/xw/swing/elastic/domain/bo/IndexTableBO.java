package com.xw.swing.elastic.domain.bo;

import com.xw.swing.education.util.AbstractModelObject;
import com.xw.swing.elastic.domain.entity.TempIndexEntity;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import com.xw.service.impl.IndexServiceImpl;
import com.xw.util.SpringContextUtil;
import org.jdesktop.observablecollections.ObservableCollections;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IndexTableBO  extends AbstractModelObject {
    private List<EsIndexVO> indexList = ObservableCollections.observableList(new ArrayList<>());
    private Integer totalNum = 0;
    private Integer page = 1;
    private Integer size = 20;
    private EsIndexVO esIndexQuery = new EsIndexVO();
    private EsIndexVO esIndexFrom = new EsIndexVO();

    public IndexTableBO(){

    }

    public static IndexTableBO create(){
        return new IndexTableBO();
    }

    public void page(){
        IndexServiceImpl bean = SpringContextUtil.getBean(IndexServiceImpl.class);
        Page<TempIndexEntity> result = bean.page(this.getEsIndexQuery(), this.getPage()-1, this.getSize());
        List<EsIndexVO> esIndexVOS = transformVO(result);
        this.getIndexList().clear();
        this.getIndexList().addAll(esIndexVOS);
        this.setTotalNum((int) result.getTotalElements());
    }

    private List<EsIndexVO> transformVO(Page<TempIndexEntity> page) {
        return  page.map(x -> {
            EsIndexVO esIndexVO = new EsIndexVO();
            BeanUtils.copyProperties(x, esIndexVO);
            return esIndexVO;
        }).stream().collect(Collectors.toList());
    }


    // ***************************GET SET ******************************


    public List<EsIndexVO> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<EsIndexVO> indexList) {
        this.indexList = indexList;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        Integer old = this.totalNum;
        this.totalNum = totalNum;
        changeSupport.firePropertyChange("totalNum", old, totalNum);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public EsIndexVO getEsIndexQuery() {
        return esIndexQuery;
    }

    public void setEsIndexQuery(EsIndexVO esIndexQuery) {
        this.esIndexQuery = esIndexQuery;
    }

    public EsIndexVO getEsIndexFrom() {
        return esIndexFrom;
    }

    public void setEsIndexFrom(EsIndexVO esIndexFrom) {
        this.esIndexFrom = esIndexFrom;
    }
}
