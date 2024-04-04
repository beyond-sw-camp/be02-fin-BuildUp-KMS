//package com.example.bootshelf.config.es;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//
//
//@Configuration
//public class ElasticSearchConfig extends AbstractElasticsearchConfiguration{
//    @Value("${spring.data.elasticsearch.url}")
//    String url;
//
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo(url)
//                .build();
//        return RestClients.create(clientConfiguration).rest();
//    }
//}
