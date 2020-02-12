### Docker Image
FROM docker.elastic.co/elasticsearch/elasticsearch:5.6.16

#LABEL maintainer "https://github.com/blacktop"

#RUN apk add --no-cache openjdk8-jre tini su-exec

#FROM qnib/logstash:fd22

    # ENV RUN_SERVER=true \
    #     BOOTSTRAP_CONSUL=true
    # ADD etc/yum.repos.d/elasticsearch.repo /etc/yum.repos.d/
    # RUN dnf install -y which zeromq

    # # elasticsearch
    # RUN dnf install -y elasticsearch && \
    #     sed -i '/# cluster.name:.*/a cluster.name: logstash' /etc/elasticsearch/elasticsearch.yml
    # ## Makes no sense to be done while building
    # #RUN sed -i "/# node.name:.*/a node.name: $(hostname)" /etc/elasticsearch/elasticsearch.yml
    # ADD etc/elasticsearch/elasticsearch.yml /etc/elasticsearch/
    # ADD etc/supervisord.d/elasticsearch.ini /etc/supervisord.d/elasticsearch.ini
    # # diamond collector
    # ADD etc/diamond/collectors/ElasticSearchCollector.conf /etc/diamond/collectors/ElasticSearchCollector.conf 

# elasticsearch
#RUN -p 9200:9200 -p 9300:9300 docker.elastic.co/elasticsearch/elasticsearch:5.6.16

# test elasticsearch
# RUN curl -u elastic http://127.0.0.1:9200/_cat/health

# Run the Docker image ESMainTest
# RUN mvn test -Dtest=ESMainTest test
RUN pwd

RUN mvn test -Dtest=ESMainTest test
