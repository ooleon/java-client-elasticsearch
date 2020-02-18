package elasticsearch.app;

import java.util.Arrays;

import org.elasticsearch.client.Client;

import elasticsearch.service.CountService;
import elasticsearch.service.DataService;
import elasticsearch.service.DeleteService;
import elasticsearch.service.IngestService;
import elasticsearch.util.ESTransportClient;

import org.springframework.stereotype.Service;

/**
 * @author ooleon
 *
 */

//@Service
public class ESMain {
	public static void main(String[] args) throws Exception{
	app();
	}

	public static String test(){
		return "-> test com sucesso <-";
	}

	public static void insertData(IngestService ingestService, Client client) {

		// Ingest
		String json = "{" + "\"user\":\"jhon\"," + "\"postDate\":\"2017-10-30\","
				+ "\"message\":\"trying out Elasticsearch\"" + "}";

		// ingest single record
		ingestService.ingest("tweet", json);

		// Ingest
		String json1 = "{" + "\"user\":\"shyam\"," + "\"postDate\":\"2017-10-31\","
				+ "\"message\":\"inserting in to Elasticsearch\"" + "}";

		String json2 = "{" + "\"user\":\"arun\"," + "\"postDate\":\"2017-10-30\","
				+ "\"message\":\"trying out Elasticsearch\"" + "}";

		// ingest batch of records
		ingestService.ingest("tweet", Arrays.asList(json, json1, json2));

		// Ingest
		String json3 = "{" + "\"name\":\"shyam\"," + "\"job\":\"Admin\"," + "\"location\":\"India\"" + "}";

		String json4 = "{" + "\"name\":\"revanth\"," + "\"job\":\"assiant\"," + "\"location\":\"Meana\"" + "}";

		// ingest batch of records
		ingestService.ingest("test", "data", Arrays.asList(json3, json4));
	}
	public static void app()  throws Exception {
		{
			ESTransportClient esTransportClient = new ESTransportClient();
			Client client = esTransportClient.getClient(
					"elasticsearch_my_cluster_leo", "127.0.0.1", 9300).get();

			CountService countService = new CountService(client);
			DataService dataService = new DataService(client);
			IngestService ingestService = new IngestService(client);
			DeleteService deleteService = new DeleteService(client);

			// count
			System.out.println("\ngetMatchAllQueryCount from ES::: " + countService.getMatchAllQueryCount());
			System.out.println("\ngetBoolQueryCount from ES::: " + countService.getBoolQueryCount());
			System.out.println("\ngetPhraseQueryCount from ES::: " + countService.getPhraseQueryCount());

			// Data
			System.out.println("\ngetMatchAllQueryData from ES::: ");
			dataService.getMatchAllQueryData().forEach(item -> System.out.println(item));

			System.out.println("\ngetBoolQueryData from ES::: ");
			dataService.getBoolQueryData().forEach(item -> System.out.println(item));

			System.out.println("\ngetPhraseQueryData from ES::: ");
			dataService.getPhraseQueryData().forEach(item -> System.out.println(item));



			// insert some sample data
			insertData(ingestService, client);

			// delete one record by id
			deleteService.delete("AV9yv83OVcmKyiS7Uos9");

			// delete record by termQuery
			System.out.println("No of records deleted = " +
					deleteService.deleteByTermQuery("arun"));

			// delete record by matchQuery
			System.out.println("No of records deleted = " + deleteService.deleteByMatchQuery("jhon"));

			// count
			System.out.println("\ngetMatchAllQueryCount from ES::: " + countService.getMatchAllQueryCount());
			System.out.println("\ngetBoolQueryCount from ES::: " + countService.getBoolQueryCount());
			System.out.println("\ngetPhraseQueryCount from ES::: " + countService.getPhraseQueryCount());

			// Data
			System.out.println("\ngetMatchAllQueryData from ES::: ");
			dataService.getMatchAllQueryData().forEach(item -> System.out.println(item));

			System.out.println("\ngetBoolQueryData from ES::: ");
			dataService.getBoolQueryData().forEach(item -> System.out.println(item));

			System.out.println("\ngetPhraseQueryData from ES::: ");
			dataService.getPhraseQueryData().forEach(item -> System.out.println(item));

		}
	}

}
