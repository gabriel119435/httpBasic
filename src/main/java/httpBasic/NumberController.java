package httpBasic;

import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberController {

	@GetMapping(value = "/random", produces = MediaType.ALL_VALUE)
	public String getRandom() {
		return "random number is " + new Random().nextInt(10);
	}

	@GetMapping(value = "/seriousInfo", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getSeriousInfo() {
		return "Hello world";
	}
}
