package es.upm.miw.resources;

import es.upm.miw.data.models.User;
import es.upm.miw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/users";
    public static final String IDENTITY_ID = "/{identity}";

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(IDENTITY_ID)
    public User readByIdentity(@PathVariable String identity) {
        return this.userService.readByIdentity(identity);
    }
}
