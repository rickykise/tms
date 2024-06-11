package ai.fassto.tms.dataaccess.common.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.val;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(
        basePackages = {
                "ai.fassto.tms.dataaccess.parcel.lotte.repository.mybatis"
        },
        sqlSessionFactoryRef = "lotteSqlSessionFactory"
)
class LotteDbConfig {
    @Bean("lotteDataSource")
    @ConfigurationProperties(prefix = "spring.lotte.datasource.hikari")
    public DataSource fasstoDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }


    @Bean(name = "lotteSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("lotteDataSource") DataSource dataSource) throws Exception {
        val sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml")
        );
        sqlSessionFactoryBean.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml")
        );
        return sqlSessionFactoryBean.getObject();
    }
}
