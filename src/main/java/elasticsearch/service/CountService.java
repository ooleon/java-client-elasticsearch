/**
 * 
 */
package elasticsearch.service;

/**
 * @author ooleon
 *
 */
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.stereotype.Service;
import org.apache.lucene.search.TotalHits;

import static org.elasticsearch.index.query.QueryBuilders.*;

//@Service
public class CountService {

	Client client;

	public CountService(Client client) {
		this.client = client;
	}

	public long getMatchAllQueryCount() {
		QueryBuilder query = matchAllQuery();
		System.out.println("getMatchAllQueryCount query =>" + query.toString());
		
		long count = ((TotalHits) client.prepareSearch().setQuery(query).setSize(0).execute().actionGet().getHits().getTotalHits()).value;
		return count;
	}

	public long getBoolQueryCount() {
		QueryBuilder query = boolQuery().must(termQuery("name", "shyam")).must(termQuery("location", "india"));
		System.out.println("getBoolQueryCount query =>" + query.toString());
		long count = ((TotalHits) client.prepareSearch("test").setQuery(query).setSize(0).execute().actionGet().getHits()
				.getTotalHits()).value;
		return count;
	}

	public long getPhraseQueryCount() {
		QueryBuilder query = matchPhraseQuery("name", "Monica");
		System.out.println("getPhraseQueryCount query =>" + query.toString());
		long count = ((TotalHits) client.prepareSearch("test").setQuery(query).setSize(0).execute().actionGet().getHits()
				.getTotalHits()).value;
		return count;
	}

}
