package com.estudo.yami_demo

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.OracleContainer
import org.testcontainers.utility.DockerImageName

@SpringBootTest
@ContextConfiguration(initializers = [AbstractIntegrationTest.Initializer::class])
abstract class AbstractIntegrationTest {

    companion object {
        private val oracleContainer = OracleContainer(
            DockerImageName
                .parse("container-registry.oracle.com/database/express:21.3.0-xe")
                .asCompatibleSubstituteFor("gvenzl/oracle-xe")
        ).apply {
            withEnv("ORACLE_PWD", "123456")
        }
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            oracleContainer.start()
            TestPropertyValues.of(
                "spring.datasource.url=${oracleContainer.jdbcUrl}",
            ).applyTo(configurableApplicationContext.environment)
        }
    }

}