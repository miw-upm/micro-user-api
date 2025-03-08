package es.upm.miw.functionaltests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import es.upm.miw.models.User;
import es.upm.miw.resources.UserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserResourceFunctionalTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    private String baseUrl;

    @BeforeEach
    void setUp(){
        this.baseUrl = "http://localhost:" + port + UserResource.USERS;
    }

    @Test
    void testReadByIdentityOk() {
        String url = this.baseUrl + "/pepe";
        ResponseEntity<User> response = testRestTemplate.getForEntity(url, User.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);

        User user = response.getBody();
        assertThat(user).isNotNull();
        assertThat(user.getIdentity()).isEqualTo("pepe");

    }

    @Test
    void testReadByIdentityNotFound() {
        String url = this.baseUrl + "/none";
        assertThatThrownBy(() -> this.testRestTemplate.getForEntity(url, User.class))
                .isInstanceOf(NotFound.class);
    }
}
