package com.xw.swing.elastic.domain.bo;

import com.xw.swing.education.util.AbstractModelObject;
import com.xw.swing.elastic.domain.entity.TempIndexEntity;
import com.xw.swing.elastic.domain.vo.EsIndexVO;
import lombok.Data;
import org.jdesktop.observablecollections.ObservableCollections;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Data
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
}
