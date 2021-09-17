package com.liang.iotmall.search;

import com.alibaba.fastjson.JSON;
import com.liang.iotmall.search.config.IotmallElasticSearchConfig;
import lombok.Data;
import lombok.ToString;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
class IotmallSearchApplicationTests {

	@Autowired
	private RestHighLevelClient client;
@ToString
@Data
static 	class Account {

			private int account_number;
			private int balance;
			private String firstname;
			private String lastname;
			private int age;
			private String gender;
			private String address;
			private String employer;
			private String email;
			private String city;
			private String state;

		}
	@Test
	void searchData() throws IOException {
		//1.创建检索请求
		SearchRequest searchRequest = new SearchRequest();
		//2.指定索引
		searchRequest.indices("bank");
		//3.指定DSL，
		SearchSourceBuilder builder = new SearchSourceBuilder();
		//构造检索条件
		builder.query(QueryBuilders.matchQuery("address","mill"));
//		System.out.println(builder.toString());
		//按照年龄的值分布进行聚合
		TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
		builder.aggregation(ageAgg);
		//计算平均薪资
		AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
		builder.aggregation(balanceAvg);

		System.out.println("检索条件"+builder.toString());
	    searchRequest.source(builder);

	    //4.执行检索
		SearchResponse searchResponse = client.search(searchRequest, IotmallElasticSearchConfig.COMMON_OPTIONS);
		//5.分析结果
		System.out.println(searchResponse.toString());
		//5.1获取所有查到的数据
		SearchHits hits = searchResponse.getHits();
		SearchHit[] searchHits = hits.getHits();
		for (SearchHit hit : searchHits) {
			String source = hit.getSourceAsString();
			Account account = JSON.parseObject(source, Account.class);
			System.out.println("account"+account);
		}
		//5.2获取这次检索到的聚合信息
		Aggregations aggregations = searchResponse.getAggregations();
//		for (Aggregation aggregation : aggregations.asList()) {
//			System.out.println("当前聚合："+aggregation.getName());
//		}
		Terms ageAgg1 = aggregations.get("ageAgg");
		for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
			String keyAsString = bucket.getKeyAsString();
			System.out.println("年龄："+keyAsString+">>"+bucket.getDocCount());
		}

		Avg balanceAvg1 = aggregations.get("balanceAvg");
		System.out.println("平均薪资："+balanceAvg1.getValue());

	}

	/**
	 * 测试保存数据到es
	 * 更新也可以
	 * param is null
	 * @return: void
	 */
	@Test
	void indexData() throws IOException {
		IndexRequest indexRequest = new IndexRequest("user");
		indexRequest.id("1");
//		indexRequest.source("userName","zhangsan","age",18,"gender","男"); 一般不用
		User user = new User();
		user.setAge(18);
		user.setUserName("梁鹏飞");
		user.setGender("男");
		String s = JSON.toJSONString(user);   //转换成json数据
		indexRequest.source(s, XContentType.JSON);  //要保存的内容

		//执行操作
		IndexResponse index = client.index(indexRequest, IotmallElasticSearchConfig.COMMON_OPTIONS);
		//提取有用的响应数据
		System.out.println(index);

	}

	@Data
	class  User{
		private String userName;
		private String gender;
		private Integer age;
	}


//	@Test
//	void contextLoads() {
//		System.out.println(client);
//	}

}
