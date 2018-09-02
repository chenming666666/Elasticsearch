package com.example.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.elasticsearch.document.Hotel;

public interface HotelDao extends ElasticsearchRepository<Hotel, String> {

}
