package com.easy.cloud.core.search.test;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@RestController
public class TestElasticsearchController {
	@Autowired
    private JestClient jestClient;

	@RequestMapping(value="search")
    public SearchResult search(Search.Builder searchBuilder) throws IOException{
        SearchResult result = jestClient.execute(searchBuilder.build());
        return result;
    }

}
