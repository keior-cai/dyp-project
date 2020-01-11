package com.sise.ccj.config.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.sise.ccj.*.dao"})
public class MyBatisConfig  implements EnvironmentAware {

//	@Autowired
	private Environment env;

    /**
     * alias所在包，用;隔开
     */
    private static final String ALIAS = "com.sise.ccj.*.model" ;

    /**
     * mapper文件所在文件夹
     */
    private static final String MAPPER_LOCATION = "classpath*:mappings/*Mapper.xml";

    @Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}


	/**
	     * 数据源
	 * @return 数据源
	 */
	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(env.getProperty("spring.datasource.url"));
		config.setUsername(env.getProperty("spring.datasource.username"));
		config.setPassword(env.getProperty("spring.datasource.password"));
		config.setAutoCommit(Boolean.valueOf(env.getProperty("spring.datasource.auto-commit")));
		config.setMaximumPoolSize(Integer.valueOf(env.getProperty("spring.datasource.maximum-pool-size")));
		return new HikariDataSource(config);
	}

	/**
	 * SqlSession工厂
	 * @param dataSource 数据源
	 * @return SqlSession工厂
	 * @throws Exception 防止必要信息缺失
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource);
		sfb.setTypeAliasesPackage(ALIAS);
		sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));

		// 插件：
		 // 1. pageHelper插件
		// TODO pageHelper以后迁移到公共组件中去
//		PageHelper pageHelper = PageHelperWrapper.newInstance().setDialect(Dialect.MYSQL)
//				.setRowBoundsWithCount(true).setPageSizeZero(true).get();

		 // 2. 后续可能有sharding插件
//		sfb.setPlugins(new Interceptor[]{pageHelper});
		return sfb.getObject();
	}

	/**
	* 事务管理器
	* @param dataSource 需要被管理的数据源
	* @return 事务管理器
	*/
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
