package es.upm.miw.resources;

import es.upm.miw.models.User;
import es.upm.miw.resources.exceptionshandler.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/users";
    public static final String IDENTITY_ID = "/{identity}";

    @GetMapping(IDENTITY_ID)
    public User readByIdentity(@PathVariable String identity) {
        if ("none".equals(identity)) {
            throw new NotFoundException("User no encontrado");
        }
        return User.builder().identity(identity).email(identity + "@gmail.com").name(identity + "-name").build();
    }
}
