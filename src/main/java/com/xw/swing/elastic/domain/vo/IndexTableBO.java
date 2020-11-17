package com.xw.swing.elastic.domain.vo;

import com.xw.swing.education.util.AbstractModelObject;
import org.jdesktop.observablecollections.ObservableCollections;

import java.util.ArrayList;
import java.util.List;

public class IndexTableBO  extends AbstractModelObject {
    private List<EsIndexVO> indexList = ObservableCollections.observableList(new ArrayList<>());
    private Integer totalNum = 0;
    private Integer page = 1;
    private Integer size = 20;
    private EsIndexVO indexQuery = new EsIndexVO();

    public IndexTableBO(){
    }

    public static IndexTableBO create(){
        return new IndexTableBO();
    }


    /**
     * 索引列表大小
     * @return
     */
    public int getIndexSize(){
        return indexList.size();
    }


    public List<EsIndexVO> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<EsIndexVO> indexList) {
        List<EsIndexVO> old = this.indexList;
        this.indexList = indexList;
        changeSupport.firePropertyChange("indexList", old, indexList);

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
        Integer old = this.page;
        this.page = page;
        changeSupport.firePropertyChange("page", old, page);

    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        Integer old = this.page;
        this.size = size;
        changeSupport.firePropertyChange("size", old, page);

    }

    public EsIndexVO getIndexQuery() {
        return indexQuery;
    }

    public void setIndexQuery(EsIndexVO indexQuery) {
        EsIndexVO old = this.indexQuery;
        this.indexQuery = indexQuery;
        changeSupport.firePropertyChange("indexQuery", old, indexQuery);

    }
}
