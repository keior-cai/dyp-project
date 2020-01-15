//package com.sise.ccj.config.mysql;
//
//import com.github.pagehelper.PageHelper;
//import com.sise.ccj.config.page.Dialect;
//import com.sise.ccj.config.page.PageHelperWrapper;
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.Data;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Data
//@Configuration
//@EnableTransactionManagement
//@MapperScan(basePackages = {"com.sise.ccj.mapper"})
//@ConfigurationProperties(prefix = "spring.datasource")
//public class MyBatisConfig {
//
//	private String url;
//
//	private String userName;
//
//	private String password;
//
//	private Boolean autoCommit;
//
//	private Integer maximumPoolSize;
//
//
//    /**
//     * alias所在包，用;隔开
//     */
//    private static final String ALIAS = "com.sise.ccj.pojo" ;
//
//    /**
//     * mapper文件所在文件夹
//     */
//    private static final String MAPPER_LOCATION = "classpath:mappings/*Mapper.xml";
//
//
//
//	/**
//	     * 数据源
//	 * @return 数据源
//	 */
//	@Bean
//	public DataSource dataSource() {
//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl(url);
//		config.setUsername(userName);
//		config.setPassword(password);
//		config.setAutoCommit(autoCommit);
//		config.setMaximumPoolSize(maximumPoolSize);
//		return new HikariDataSource(config);
//	}
//
//	/**
//	 * SqlSession工厂
//	 * @param dataSource 数据源
//	 * @return SqlSession工厂
//	 * @throws Exception 防止必要信息缺失
//	 */
//	@Bean
//	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
//		sfb.setDataSource(dataSource);
//		sfb.setTypeAliasesPackage(ALIAS);
//		// 开启驼峰命名
//		sfb.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//
//		sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//		PageHelper pageHelper = PageHelperWrapper.newInstance().setDialect(Dialect.MYSQL)
//				.setRowBoundsWithCount(true).setPageSizeZero(true).get();
//		sfb.setPlugins(new Interceptor[]{pageHelper});
//		return sfb.getObject();
//	}
//
//	/**
//	* 事务管理器
//	* @param dataSource 需要被管理的数据源
//	* @return 事务管理器
//	*/
//	@Bean
//	public PlatformTransactionManager transactionManager(DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}
//
//}
