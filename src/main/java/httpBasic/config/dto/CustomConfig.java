package httpBasic.config.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@ConfigurationProperties(prefix = "custom")
public record CustomConfig(@NotEmpty List<User> users) {
}
