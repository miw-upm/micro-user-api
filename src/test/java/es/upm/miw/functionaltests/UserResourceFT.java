package es.upm.miw.functionaltests;

import es.upm.miw.data.models.User;
import es.upm.miw.resources.UserResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserResourceFT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testReadByIdentityOk() {
        ResponseEntity<User> response = testRestTemplate.getForEntity(
                UserResource.USERS + UserResource.IDENTITY_ID, User.class, "pepe");

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody())
                .isNotNull()
                .extracting(User::getIdentity).isEqualTo("pepe");
    }

    @Test
    void testReadByIdentityNotFound() {
        assertThat(this.testRestTemplate
                .getForEntity(UserResource.IDENTITY_ID, User.class, "none")
                .getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }
}
