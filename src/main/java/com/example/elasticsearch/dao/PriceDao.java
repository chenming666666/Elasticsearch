package com.example.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.elasticsearch.document.Price;

public interface PriceDao extends ElasticsearchRepository<Price, String> {

}
