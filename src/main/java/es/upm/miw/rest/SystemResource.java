package es.upm.miw.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SystemResource {

    public static final String VERSION_BADGE = "/version-badge";

    @Value("${info.app.artifact}")
    private String artifact;
    @Value("${info.app.version}")
    private String version;
    @Value("${info.app.build}")
    private String build;

    @GetMapping
    public String applicationInfo() {
        return """
                {"version":"%s::%s::%s"} <br>
                /version-badge <br>
                /actuator/info <br> /actuator/health <br>
                /swagger-ui.html <br> /v3/api-docs <br>
                """.formatted(this.artifact, this.version, this.build);
    }

    @GetMapping(value = VERSION_BADGE, produces = {"image/svg+xml"})
    public byte[] generateBadge() {
        return new Badge().generateBadge("Render", "v" + version).getBytes();
    }

}
