package org.example.genre.Genre.event.repository.impl;

import org.example.genre.Genre.event.repository.api.GenreEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.kafka.core.KafkaTemplate;

@Repository
public class GenreEventRestRepository implements GenreEventRepository {
    private final RestTemplate restTemplate;

    private KafkaTemplate<String, String> kafkaTemplate;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    public GenreEventRestRepository(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient, KafkaTemplate<String, String> kafkaTemplate) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void delete(String name) {
//        String uri = this.loadBalancerClient.choose("movies").getUri().toString();

//        this.restTemplate.delete(uri + "/api/genres/{name}", name);

        System.out.println("Sending delete-genre event to Kafka");
        this.kafkaTemplate.send("delete-genre", name);
    }

    @Override
    public void save(String name) {
        //      String uri = this.loadBalancerClient.choose("movies").getUri().toString();
        //    this.restTemplate.put(uri +"/api/genres/{name}", null, name); }

        System.out.println("Sending create-genre event to Kafka");
        this.kafkaTemplate.send("create-genre", name);
    }
}
