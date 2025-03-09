package es.upm.miw.functionaltests;

import es.upm.miw.resources.SystemResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SystemResourceFunctionalTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testReadBadge() {
        ResponseEntity<String> response = restTemplate.getForEntity(SystemResource.VERSION_BADGE, String.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody())
                .isNotNull()
                .startsWith("<svg");
    }

    @Test
    void testReadInfo() {
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody())
                .isNotNull()
                .isNotEmpty();
    }
}