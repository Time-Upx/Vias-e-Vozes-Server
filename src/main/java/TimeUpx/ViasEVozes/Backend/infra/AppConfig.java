package TimeUpx.ViasEVozes.Backend.infra;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Value("${spring.datasource.username}")
	private String datasourceUsername;

	@Value("${spring.datasource.password}")
	private String datasourcePassword;

	@Value("${spring.datasource.driver-class-name}")
	private String datasourceDriverClassName;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(datasourceUrl);
		dataSource.setUsername(datasourceUsername);
		dataSource.setPassword(datasourcePassword);
		dataSource.setDriverClassName(datasourceDriverClassName);
		return dataSource;
	}

	@Bean
	public DataSourceProperties dataSourceProperties() {
		DataSourceProperties properties = new DataSourceProperties();
		properties.setUrl(datasourceUrl);
		properties.setUsername(datasourceUsername);
		properties.setPassword(datasourcePassword);
		properties.setDriverClassName(datasourceDriverClassName);
		return properties;
	}

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String jpaDdlAuto;

	@Value("${spring.jpa.show-sql}")
	private boolean jpaShowSql;

	@Value("${spring.jpa.properties.hibernate.format_sql}")
	private String hibernateFormatSql;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Bean
	public JpaProperties jpaProperties() {
		JpaProperties jpaProperties = new JpaProperties();
		jpaProperties.setShowSql(jpaShowSql);
		Map<String, String> propertiesMap = new HashMap<>();
		propertiesMap.put("hibernate.hbm2ddl.auto", jpaDdlAuto);
		propertiesMap.put("hibernate.format_sql", hibernateFormatSql);
		propertiesMap.put("hibernate.dialect", hibernateDialect);
		jpaProperties.setProperties(propertiesMap);
		return jpaProperties;
	}

	@Value("${server.error.include-stacktrace}")
	private String serverErrorIncludeStacktrace;

	@Setter
	@Getter
	public static class ServerErrorProperties {
		private String includeStacktrace;
	}

	@Bean
	public ServerErrorProperties serverErrorProperties() {
		ServerErrorProperties properties = new ServerErrorProperties();
		properties.setIncludeStacktrace(serverErrorIncludeStacktrace);
		return properties;
	}
}
