package elasticsearch.service;

import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
/**
 * @author ooleon
 *
 */
public class DeleteService {

	Client client;

	public DeleteService(Client client) {
		this.client = client;
	}

	public void delete(String id) {
		client.prepareDelete("twitter", "tweet", id).get();
	}

	public long deleteByMatchQuery(String name) {

		// BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
		// 		.filter(QueryBuilders.matchQuery("user", name))
		// 		//.filter(QueryBuilders.typeQuery("tweet"))
		// 		.source("twitter").get();

		BulkByScrollResponse response =
		new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE)
			.filter(QueryBuilders.matchQuery("user", name)) 
			.source("twitter")                                  
			.get();                                             
		long deleted = response.getDeleted();                   
		return deleted;
	}

	public long deleteByTermQuery(String name) {

		// BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
		// 		.filter(QueryBuilders.termQuery("user", name))
		// 		.source("twitter").get();
		// return response.getDeleted();

		BulkByScrollResponse response =
		new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE)
			.filter(QueryBuilders.termQuery("user", name)) 
			.source("twitter")                                  
			.get();                                             
		long deleted = response.getDeleted();                   
		return deleted;
	}
}
