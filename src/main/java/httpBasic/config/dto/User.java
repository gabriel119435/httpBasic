package httpBasic.config.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record User(
        @NotNull String name,
        @NotNull String pass,
        @NotEmpty List<String> services
) {
}
