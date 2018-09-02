package com.example.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.example.elasticsearch.dao.HotelDao;
import com.example.elasticsearch.document.Hotel;

@Service
public class HotelService {

	@Autowired
	HotelDao hotelDao;

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	/**
	 * term 查询
	 */
	public Iterable<Hotel> termQuery() {

		TermQueryBuilder builder = new TermQueryBuilder("business_area_name", "北京");

		Iterable<Hotel> hotels = hotelDao.search(builder);

		return hotels;
	}

	/**
	 * terms 查询
	 */
	public Iterable<Hotel> termsQuery() {
		TermsQueryBuilder builder = new TermsQueryBuilder("business_area_name", "北京", "南京", "天津");

		Iterable<Hotel> hotels = hotelDao.search(builder);

		return hotels;
	}

	/**
	 * 分页查询
	 */
	public Iterable<Hotel> pageQuery() {
		TermsQueryBuilder builder = new TermsQueryBuilder("business_area_name", "北京");
		int pageNum = 1;
		int pageSize = 15;
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return hotelDao.search(builder, pageable);
	}

	/**
	 * 分页及排序
	 */
	public Iterable<Hotel> pageAndSortQuery() {
		TermsQueryBuilder builder = new TermsQueryBuilder("business_area_name", "北京");

		String orderBy = "create_time";
		Sort sort = new Sort(Direction.DESC, orderBy);

		int pageNum = 1;				//作用是什么？？
		int pageSize = 15;
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);

		return hotelDao.search(builder, pageable);
	}

	/**
	 * match 查询
	 */
	public Iterable<Hotel> matchQuery() {
		MatchQueryBuilder builder = new MatchQueryBuilder("business_area_name", "上海");
		return hotelDao.search(builder);
	}

	/**
	 * 高亮显示
	 */
	public Iterable<Hotel> hightQuery() {

		int pageNum = 1;
		int pageSize = 15;
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.termQuery("hotel_name", "北京"))
				.withHighlightFields(new HighlightBuilder.Field("hotel_name")).withPageable(pageable).build();
		
		Page<Hotel> page = elasticsearchTemplate.queryForPage(searchQuery, Hotel.class, new SearchResultMapper() {
			
			@Override
			public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				
				List<Hotel> hotels = new ArrayList<>();
				SearchHits hits = response.getHits();
				
				for (SearchHit searchHit : hits) {
					if (hits.getHits().length <= 0) {
						return null;
					}
					Hotel hotel = new Hotel();
					String highLightMessage = searchHit.getHighlightFields().get("hotel_name").fragments()[0].toString();
					hotel.setHotel_id(searchHit.getId());
					hotel.setHotel_name(highLightMessage);
					hotels.add(hotel);
				}
				if (hotels.size() > 0) {
					return new AggregatedPageImpl<T>((List<T>) hotels);
				}
				return null;
			}
		});
		return page;
	}
	
	/**
	 * multi-match查询
	 */
	public Iterable<Hotel> multiMatchQuery() {
		int pageNum = 1;
		int pageSize = 15;
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("北京", "hotel_name", "business_area_name");
		return hotelDao.search(queryBuilder, pageable);
	}
	
	/**
	 * 拼接在某属性的 set方法
	 * 
	 * @param fieldName
	 * @return String
	 */
//	private static String parSetName(String fieldName) {
//		if (null == fieldName || "".equals(fieldName)) {
//			return null;
//		}
//		int startIndex = 0;
//		if (fieldName.charAt(0) == '_')
//			startIndex = 1;
//		return "set" + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
//				+ fieldName.substring(startIndex + 1);
//	}
}

