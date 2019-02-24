package httpBasic.config;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

	@NotNull
	String name;

	@NotNull
	String pass;

	@NotEmpty
	List<String> services;
}
