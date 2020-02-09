package elasticsearch.util;

import java.net.InetAddress;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * @author ooleon
 *
 */
public class ESTransportClient {
	private final static Logger logger = Logger.getLogger(ESTransportClient.class);

	public Optional<Client> getClient(String clusterName, String host, int port) {
		try {
			Settings settings = Settings.builder().put("cluster.name", clusterName).put("client.transport.sniff", true)
					.build();
			TransportClient client = new PreBuiltTransportClient(settings);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
			return Optional.of(client);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while initializing TransportClient :" + e.getMessage(), e);
			return Optional.empty();
			
		}
	}

}

/*
org.springframework.beans.factory.UnsatisfiedDependencyException: 
Error creating bean with name 'application': 
Unsatisfied dependency expressed through field 'es'; 
nested exception is 
org.springframework.beans.factory.UnsatisfiedDependencyException: 
Error creating bean with name 'elasticsearchTemplate' defined in class path resource
 [org/springframework/boot/autoconfigure/data/elasticsearch/ElasticsearchDataAutoConfiguration.class]: 
Unsatisfied dependency expressed through method 'elasticsearchTemplate' 
parameter 0; nested exception is 
org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'client' defined in class path resource
 [com/mkyong/EsConfig.class]: Bean instantiation via factory method failed; 
nested exception is org.springframework.beans.BeanInstantiationException: 
Failed to instantiate [org.elasticsearch.client.Client]: 
Factory method 'client' threw exception; nested exception is
 java.lang.NoClassDefFoundError: org/elasticsearch/transport/Netty3Plugin
 */
