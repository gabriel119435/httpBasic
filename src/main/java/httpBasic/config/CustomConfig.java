package httpBasic.config;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component

@Getter
@Setter
@ToString
@NoArgsConstructor

@Validated

@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

	@NotEmpty
	private List<User> users = new ArrayList<>();

}
