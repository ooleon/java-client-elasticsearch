### Docker Image
FROM ooleon/maven:alpine as mvnstage



ENV APT_KEY_DONT_WARN_ON_DANGEROUS_USAGE=1
ENV DEBIAN_FRONTEND=noninteractive
ENV STACK=7.17.6
#ENV STACK=7.16.2
#ENV STACK=5.6.16


# elasticsearch
# RUN -p 9200:9200 -p 9300:9300 docker.elastic.co/elasticsearch/elasticsearch:5.6.16

# test elasticsearch
# RUN curl -u elastic http://127.0.0.1:9200/_cat/health

# Run the Docker image ESMainTest
# RUN mvn test -Dtest=ESMainTest test
WORKDIR /home/runner/work/app
# WORKDIR /home/runner/work/

# RUN mkdir app

# RUN chmod 777 app
LABEL maintainer "https://github.com/ooleon"

RUN pwd

RUN ls -l

# RUN cd /home/runner/work/app

COPY . .

RUN mvn -e -B dependency:resolve
RUN mvn -e -B package

CMD [ "mvn", "test", "-Dtest=ESMainTest", "test" ]
#FROM ooleon/eclipse-temurin:latest

#RUN wget --progress=bar:force -O elasticsearch-5.6.16.tar.gz https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.6.16.tar.gz

# RUN wget -O elasticsearch.tar.gz https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.17.6-linux-x86_64.tar.gz

# RUN  tar -xzf elasticsearch.tar.gz
#WORKDIR /home/runner/work/java-client-elasticsearch/java-client-elasticsearch/elasticsearch-$STACK/bin/

# RUN elasticsearch &
RUN ls -l

RUN pwd

RUN ls -lR

# RUN mvn test -Dtest=ESMainTest test

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
