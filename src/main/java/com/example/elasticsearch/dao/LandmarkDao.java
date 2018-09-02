package com.example.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.elasticsearch.document.Landmark;

public interface LandmarkDao  extends ElasticsearchRepository<Landmark, String>{

}
