package es.upm.miw.services;

import es.upm.miw.data.models.User;
import es.upm.miw.resources.exceptionshandler.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User readByIdentity(String identity) {
        if ("none".equals(identity)) {
            throw new NotFoundException("User no encontrado");
        }
        return User.builder().identity(identity).email(identity + "@gmail.com").name(identity + "-name").build();
    }
}
