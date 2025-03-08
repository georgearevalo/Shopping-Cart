package details.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Primary
@Component
@ConfigurationProperties(prefix = "segurity.config")
public class SegurityProperties {

  private String username;
  private String password;
  private String roles;
}
