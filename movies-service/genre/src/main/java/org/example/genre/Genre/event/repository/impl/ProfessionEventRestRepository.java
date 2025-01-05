package org.example.genre.Genre.event.repository.impl;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.example.genre.Genre.event.repository.api.GenreEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProfessionEventRestRepository implements GenreEventRepository {
    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    public ProfessionEventRestRepository(RestTemplate restTemplate, DiscoveryClient discoveryClient, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.loadBalancerClient = loadBalancerClient;
    }

    @Override
    public void delete(String name){
        String uri = this.loadBalancerClient.choose("movies").getUri().toString();

        this.restTemplate.delete(uri + "/api/genres/{name}", name);
    }

    @Override
    public void save(String name) {
        String uri = this.loadBalancerClient.choose("movies").getUri().toString();
        this.restTemplate.put(uri +"/api/genres/{name}", null, name); }
}
