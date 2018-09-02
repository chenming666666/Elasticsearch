# Elasticsearch
分布式搜索和分析引擎
	
 1.Es lucene Solr 基本概念 和区别

		lucene，先进、功能强大的搜索库，直接基于lucene开发，非常复杂，api复杂（实现一些简单的功能，写大量的java代码），需要深入理解原理（各种索引结构）
	
		Solr基于lucene实现了进一步的封装处理，更简单的开箱即用。

		elasticsearch，基于lucene，隐藏复杂性，提供简单易用的restful api接口、java api接口（还有其他语言的api接口）
		  1)分布式的文档存储引擎
		  2)分布式的搜索引擎和分析引擎
		  3)分布式，支持PB级数据
				
		ES与solr的区别：
          1) Solr是提供类似webservice的接口。Es提供rest风格的接口。
          2) Solr 4.x以后支持分布式，而Es天生支持分布式，数据量越多ES搜索效率越高！
          3) Solr的数据格式是xml和json，Es是json
	      4) Solr不支持实时数据搜索，而Es可以(从索引一个文档直到这个文档能够被搜索到有一个轻微的延迟(通常是1秒))
	
2.Es的特性以及应用场景
		  
      1) 开源 
		  2) 提供了JAVA API接口。 
		  3) 提供了RESTful API接口
		  4) REST请求和应答是典型的JSON格式	
	      5) 可以作为一个大型分布式集群（数百台服务器）技术，处理PB级数据，服务大公司；也可以运行在单机上，自定义主从备份，自主切换。
		  6) 开箱即用，3分钟安装部署，非常小的耦合度。
		 
		 Es的应用核心是大数据量下的数据搜索和数据分析。
		 搜索：聊天记录的关键字搜索
		 数据分析：APP用户日活量，搜索量最高的商品等等
		 业界内典型的使用场景：
		   1) 百度百科，全文检索，高亮，搜索推荐
		   2) 搜狐新闻，用户行为日志（点击，浏览，收藏，评论）
		   3) 淘宝，搜索商品，（中文，拼音）
		   
   3.Es的安装部署过程
		
    Es最新版本为5.6.1 
		历史版本为1.x 2.x 5.x，各版本安装部署差异较大。
        
		这里主要讲基于Linux环境下的Es5.6.1版本安装。
		环境要求：jdk1.8.0_81 及以上版本。我用的1.8.0_144,JDK安装自行百度。
		cd /usr/local  (自定义的Es_Home)
		wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.6.1.tar.gz
		tar -zxvfelasticsearch-5.6.1.tar.gz
		安装完成 后台启动Es
		bin/elasticsearch &  
		
		部分报错问题：
		Error:0x00007f2cae000000, 33554432, 0) failed; error='Cannot allocate memory' (errno=12)
		Java HotSpot(TM) 64-Bit Server VM warning: INFO: os::commit_memory(0x0000000085330000, 2060255232, 0) failed; error='Cannot allocate memory' (errno=12)
		由于elasticsearch5.x默认分配jvm空间大小为2g，修改jvm空间分配，ES_HOME/config/jvm.options  -xms2g -xmx修改为 -xms512m -xmx512m
		Error:can not run elasticsearch as root 
		显然不能用root用户启动，新建用户并切换去启动。
		Error:access denied
		权限不够，用root赋予Es用户文件夹权限 chown esUser /usr/local -R
		其余报错问题这里不占篇幅 参考 http://blog.csdn.net/li_work/article/details/78113614
		
		启动成功，查看 ps -ef|grep elastic  
		
		liunx运行： curl 127.0.0.1:9200 
		返回当前IP下Es节点的状态信息：
			
		{
		  "name" : "Oj6ase4",
		  "cluster_name" : "elasticsearch",
		  "cluster_uuid" : "qbhH0ylgQp-PBARMkB_Peg",
		  "version" : {
			"number" : "5.6.1",
			"build_hash" : "667b497",
			"build_date" : "2017-09-14T19:22:05.189Z",
			"build_snapshot" : false,
			"lucene_version" : "6.6.1"
		  },
		  "tagline" : "You Know, for Search"
		}
		
		name:当前Es节点的实例名称。
		cluster_name：当前Es节点的集群名称，若启动时不指定则默认为elasticsearch；
		剩下就是Es版本的基本信息：版本号，构建时间，基于lucene的版本等。
		
		注:外网或者局域网访问Es,需在Es_Home/config/elasticsearch.yml 中放开NetWork的注释，并将值从192.168.0.1修改为0.0.0.0 保存，重启Es生效。
	
4.Es的基本教程
		
		
		集群：包含多个节点，每个节点属于哪个集群是通过一个配置（集群名称，默认是elasticsearch）来决定的
			  节点只能通过指定某个集群的名字，来加入这个集群。
			  
		节点：名称（默认是随机分配的），节点名称很重要（在执行运维管理操作的时候），默认会去加入一个名称为“elasticsearch”的集群，
		如果直接启动一堆节点，那么它们会自动组成一个elasticsearch集群，当然一个节点也可以组成一个elasticsearch集群
		
		索引(index)，类型(type) 和文档(document)
		比较直观的说法是和典型的数据库进行对比:
			Es            DB
			index	      database      每一个索引对应数据库里的一个库
			type          table         每一个文档对应数据库里的一个表
			document      record 		每一个文档对应到表里的一条数据
			区别在于在数据库表里存储某条数据时， 数据所包含的字段 必须等同于 表里所有的字段，多出字段会报错。
			文档在插入type时可以多出来一些字段，多出的字段会在文档里自动添加映射。
			举例：
			DB: insert User(id,name) values(1,'zhangsan','男') 就会报错，因为user表里没有sex字段去存储 男。存储失败！
			ES: 就会在索引的mapping里自动添加上一个field字段，并定义为String类型，并添加数据成功。	  
			
			document通常用JSON数据结构表示，
			每个index下的type中，都可以去存储多个document。一个document里面有多个field，每个field就是一个数据字段
			
		分片：当一个索引上存储上的数据超出了当前节点硬件存储的限制，Es允许索引上的数据进行化成多份，每份叫做一个分片，分片可以存储到当前集群上的
			  其他节点上，通过分片可以水平分割/扩展你的内容容量，允许用户在分片之上进行分布式的、并行的操作，进而提高性能/吞吐量
		
		复制：任何一个服务器随时可能故障或宕机，此时分片可能就会丢失，
			  因此可以为每个分片创建多个复制的副本。副本可以在分片故障时提供备用服务，保证数据不丢失，
			  多个副本还可以提升搜索操作的吞吐量和性能。
			  分片（建立索引时一次设置，不能修改，默认5个），
			  复制（随时修改数量，默认1个），默认每个索引10个shard，5个分片，5个备份副本，最小的高可用配置，是2台服务器
		
		现在开始和es restful接口的一些基本通信：
		
		/_cat Es提供的一些系统级的API查询restful 接口，返回均为json格式数据
			curl 127.0.0.1:9200/_cat/health?v              检查集群的健康状态
			curl 127.0.0.1:9200/_cat/nodes?v               获得节集群中的节点列表
			curl 127.0.0.1:9200/_cat/indices?v             检查所有的索引信息
			curl 127.0.0.1:9200/customer/_mapping?pretty   查看customer索引的当前mappping配置，返回当前索引Mapping，
														       当新增的文档中存在mapping不存在的字段时，会自动根据字段类型映射到mapping中。
			
		基本增删改查语句：
			curl -XPUT 127.0.0.1:9200/customer?pretty      创建一个名字叫做customer的索引，参数pretty意思为返回的json格式化一下。
			curl -XPUT 127.0.0.1:9200/customer/message/1?pretty -d '
			{
			  "name": "zhang san"
			}'
			给索引customer下类型为message中存入一条文档，文档字段名为name,值为 zhangsan，id指定为1，若不指定，则Es随机分配一个Id,并返回
			curl 127.0.0.1:9200/customer/message/1?pretty   获取customer下message里id为1的文档信息。
			curl 127.0.0.1:9200/customer/_search?pretty     获取customer索引下所有文档。
			curl -XDELETE 127.0.0.1:9200/customer?pretty    删除customer索引
			curl -XPOST 127.0.0.1:9200/customer/external/1/_update?pretty' -d '
			{
			  "doc": { "name": "Jane Doe" }
			}'       修改语句
		
		批处理语句 _bulk
			 curl -XPOST 127.0.0.1:9200/customer/external/_bulk?pretty -d '
			{"update":{"_id":"1"}}
			{"doc": { "name": "lisi" } }
			{"delete":{"_id":"2"}}
			首先更新了id为1的name字段，然后执行了删除id=2的文档。
			bulk API按顺序执行这些动作。如果其中一个动作因为某些原因失败了，将会继续处理它后面的动作。
			当bulk API返回时，它将提供每个动作的状态（按照同样的顺序），所以你能够看到某个动作成功与否。
		
		进阶DSL(多条件搜索语句)：
			Elasticsearch提供一种JSON风格的特定领域语言，利用它你可以执行查询称为查询DSL。
			具体方法参照SQL 基本就懂了。
			curl -XPOST 127.0.01:9200/customer/_search?pretty -d '
				{
				  "query": { "match_all": {} }
				}'
			解析：select * from customer ;	
			
			curl -XPOST 127.0.01:9200/customer/_search?pretty -d '
			{
			  "query": { "match_all": {} },
			  "size": 1
			}'
			解析：select * from customer limit 1;	注意，如果没有指定size的值，那么它默认就是10。
			
			curl -XPOST 127.0.01:9200/customer/_search?pretty -d '
			{
			  "query": { "match_all": {} },
			  "from": 10,
			  "size": 10
			}'
			解析：select * from customer limit 10,10;		
			
			curl -XPOST 127.0.01:9200/customer/_search?pretty -d '
			{
			  "query": { "match_all": {} },
			  "sort": { "balance": { "order": "desc" } }
			}'
			解析：select * from customer order by balance desc limit 0,10;	
			
			curl -XPOST 127.0.01:9200/customer/_search?pretty -d '
			{
			  "query": {
				"bool": {
				  "must": [
					{ "match": { "address": "mill" } },
					{ "match": { "address": "lane" } }
				  ]
				}
			  }
			}'
			解析：select * from customer where address like "%mill%" and address like "%lane%";
			{
  "query": {
    "match": {"name": "liu" }
  }, 
    "from":0,
    "size":10
}
			curl -XPOST 127.0.01:9200/customer/_search?pretty -d '
				{
				  "query": {
					"bool": {
					  "must": [
						{ "match": { "age": "40" } }
					  ],
					  "must_not": [
						{ "match": { "state": "ID" } }
					  ]
					}
				  }
				}'
			解析：select * from customer where age =40 and state not in ("ID");
			
			curl -XPOST 127.0.01:9200/customer/_search?pretty -d '
				{
				  "size": 0,
				  "aggs": {
					"group_by_state": {
					  "terms": {
						"field": "state"
					  }
					}
				  }
				}'
			解析：SELECT COUNT(*) from customer GROUP BY state ORDER BY COUNT(*) DESC
		
   5.JAVA整合 Es API
		
    Java结合Es有两种方式
		一种是直接发送http请求Es暴露对外的restful接口,类似工具：url  httpClient等等 都可以实现。
	    一种是用Es提供的Jar包进行调用实现。
		
		这里主要讲一下JAVA整合 Es API
		首先在项目中导入需要的maven依赖
			<dependency>
              <groupId>org.elasticsearch</groupId>
              <artifactId>elasticsearch</artifactId>
              <version>5.3.1</version>
          </dependency>
			<dependency>
				  <groupId>org.elasticsearch.client</groupId>
				  <artifactId>transport</artifactId>
				  <version>5.3.1</version>
			</dependency>
		
		结合SpringCloud在项目的application.properties中定义好需要的配置变量，或者直接在config中统一配置管理
		也可以在自己的代码中写死，测试练习用
			es.cluster_name=elasticsearch
			es.cluster_server_ip=47.93.6.131
			es.index_name=customer
			es.url=http://47.93.6.131:9200
		
		Java Code:
			@Value("${es.cluster_name}")
			private String cluster_name;// 实例名称
			@Value("${es.cluster_server_ip}")
			private String cluster_serverip;// elasticSearch服务器ip
			@Value("${es.index_name}")
			private String indexname;// 索引名称
			

			/**
			 * 返回一个到ElasticSearch的连接客户端
			 * 
			 * @return
			 */
			private TransportClient getClient() {
				Settings settings = Settings.builder().put("cluster.name", cluster_name).build();// 设置集群名称
				TransportClient client = new PreBuiltTransportClient(settings);// 创建client
				try {
					client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(cluster_serverip), 9300));// 增加地址和端口
				} catch (UnknownHostException e) {
					e.printStackTrace();
					logger.error("ElasticSearch连接失败！");
				}

				return client;
			}
		这就创建了一个最基本的TransportClient，原理是利用IP地址和端口，集群名字，作为一个无存储的节点加入该集群，加入后就获取到了其他节点的信息
		并通过指定的API进行通信！
		
		参考了部分网上的资料，自己封装了一套API，
		查询，搜索还是建议用url请求方式，快一些。
		插入等操作用API好一些。

		代码已上传 github 
		地址：https://github.com/likaile/elasticSearcheDemo
		欢迎加星
					
6.Es分词器

		Es中，内置了很多分词器(analyzers)，
			standard (标准分词器) 默认分词器，最无脑的一个一个词(汉字)切分，所以适用范围广，但是精准度低
			english  (英文分词)	  对英文更加智能，可以识别单数负数，大小写，过滤stopwords
			chinese  (中文分词)   Es提供给中文的一个分词器，但是效果很差。
		
		所以我们要安装第三方的一个中文分词器，来达到我们想要的效果。
		首先通过对比ik和chinese的分词效果来体现ik的好处。
		chinese分词器
			http://47.93.6.131:9200/index2/_analyze?analyzer=chinese&text=我爱你我的家&pretty 
		    返回json：
			{
				"tokens": [
				  {
				"token": "我",
				"start_offset": 0,
				"end_offset": 1,
				"type": "<IDEOGRAPHIC>",
				"position": 0
				},
				  {
				"token": "爱",
				"start_offset": 1,
				"end_offset": 2,
				"type": "<IDEOGRAPHIC>",
				"position": 1
				},
				  {
				"token": "你",
				"start_offset": 2,
				"end_offset": 3,
				"type": "<IDEOGRAPHIC>",
				"position": 2
				},
				  {
				"token": "我",
				"start_offset": 3,
				"end_offset": 4,
				"type": "<IDEOGRAPHIC>",
				"position": 3
				},
				  {
				"token": "的",
				"start_offset": 4,
				"end_offset": 5,
				"type": "<IDEOGRAPHIC>",
				"position": 4
				},
				  {
				"token": "家",
				"start_offset": 5,
				"end_offset": 6,
				"type": "<IDEOGRAPHIC>",
				"position": 5
				}
				],
				}
			可以看到和standard分词器一样很无脑的分成了一个一个的单个索引，
			
			对比ik
			http://47.93.6.131:9200/index2/_analyze?analyzer=ik_max_word&text=我爱你我的家&pretty 
			返回json：
			{
				"tokens": [
				  {
				"token": "我爱你",
				"start_offset": 0,
				"end_offset": 3,
				"type": "CN_WORD",
				"position": 0
				},
				  {
				"token": "爱你",
				"start_offset": 1,
				"end_offset": 3,
				"type": "CN_WORD",
				"position": 1
				},
				  {
				"token": "你我",
				"start_offset": 2,
				"end_offset": 4,
				"type": "CN_WORD",
				"position": 2
				},
				  {
				"token": "的",
				"start_offset": 4,
				"end_offset": 5,
				"type": "CN_CHAR",
				"position": 3
				},
				  {
				"token": "家",
				"start_offset": 5,
				"end_offset": 6,
				"type": "CN_CHAR",
				"position": 4
				}
				],
				}
			很明显 IK分词 就友好了很多。
			ik 带有两个分词器 ，这里用的是ik_max_word
			ik_max_word ：会将文本做最细粒度的拆分；尽可能多的拆分出词语 
			ik_smart：会做最粗粒度的拆分；已被分出的词语将不会再次被其它词语占有 
		
		ik的安装和使用过程。
			参考 github原文  https://github.com/medcl/elasticsearch-analysis-ik
			1.download or compile   

			optional 1 - download pre-build package from here: https://github.com/medcl/elasticsearch-analysis-ik/releases

			unzip plugin to folder your-es-root/plugins/

			optional 2 - use elasticsearch-plugin to install ( version > v5.5.1 ):

			./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v5.6.1/elasticsearch-analysis-ik-5.6.1.zip

			2.restart elasticsearch

			方法1：下载zip包，解压到 your-es-home/plugs/ik 下 
			方法2 使用es插件下载 版本要大于5.5.1  
			最后一步，重启ES
			很简单，
			因为索引的Mapping不可以删除，所以我们新建一个索引,并配置mapping进行测试。

			curl -XPUT http://localhost:9200/index1
			
			curl -XPOST http://localhost:9200/index1/message/_mapping -d'  
			{  
					"properties": {  
						"content": {  
							"type": "text",  
							"analyzer": "ik_max_word",  
							"search_analyzer": "ik_max_word"  
						}  
					}  
				  
			}'
			新建索引index1，类型message，设置字段content， 设置搜索分词器为ik。
			
			搜索关键字content 我爱你   返回 我爱你我的家 成功。
			
		pinyin分词器
			参考原文 https://github.com/medcl/elasticsearch-analysis-pinyin
			首先来看下pinyin分词器 对于我爱你我的家的分词效果
			{  
			  "tokens" : [  
				{  
				  "token" : "wo",  
				  "start_offset" : 0,  
				  "end_offset" : 1,  
				  "type" : "word",  
				  "position" : 0  
				},  
				{  
				  "token" : "ai",  
				  "start_offset" : 1,  
				  "end_offset" : 2,  
				  "type" : "word",  
				  "position" : 1  
				},  
				{  
				  "token" : "ni",  
				  "start_offset" : 2,  
				  "end_offset" : 3,  
				  "type" : "word",  
				  "position" : 2  
				},  
				{  
				  "token" : "wo",  
				  "start_offset" : 3,  
				  "end_offset" : 4,  
				  "type" : "word",  
				  "position" : 3  
				},  
				{  
				  "token" : "de",  
				  "start_offset" : 4,  
				  "end_offset" : 5,  
				  "type" : "word",  
				  "position" : 4  
				},  
				{  
				  "token" : "jia",  
				  "start_offset" : 5,  
				  "end_offset" : 6,  
				  "type" : "word",  
				  "position" : 5  
				},  
				{  
				  "token" : "wanwdj",  
				  "start_offset" : 0,  
				  "end_offset" : 6,  
				  "type" : "word",  
				  "position" : 5  
				}  
			  ]  
			}
			pinyin的分词器和IK一样 下载ZIP，解压到plugs 下 pinyin文件夹  重启。

			根据官方git地址 测试案例 整合一个索引进行 数据的拼音搜索   测试成功。
			
