package com.xw.util.es;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class EsSearchDemo {
    private RestHighLevelClient restHighLevelClient;

    private boolean getDocById(String[] ids) throws IOException {
        SearchRequest request = Requests.searchRequest("index_rdata_literature_v35");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery();
        idsQueryBuilder.addIds(ids);
        searchSourceBuilder.query(idsQueryBuilder);
        searchSourceBuilder.fetchSource("id", null);
        request.source(searchSourceBuilder);
        SearchResponse response = this.restHighLevelClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        SearchHit hit = null;
        if (hits.getTotalHits().value == 1000) {
            return true;
        } else {
            return false;
        }
    }
}
