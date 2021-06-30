package in.sankarvinoth.ecommerce_app.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import in.sankarvinoth.ecommerce_app.util.ConnectionUtil;

@Configuration
public class DataSourceConfiguration {
	@Bean
	public JdbcTemplate jdbcTemplate() {
		DataSource jdbcTemplate = ConnectionUtil.getConnection();
		return new JdbcTemplate(jdbcTemplate);
	}
}
