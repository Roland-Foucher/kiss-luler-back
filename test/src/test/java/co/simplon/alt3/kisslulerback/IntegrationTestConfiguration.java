package co.simplon.alt3.kisslulerback;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;


@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = { "/schema.sql", "/data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public abstract class IntegrationTestConfiguration {

}
