package httpBasic;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import httpBasic.config.CustomConfig;

@RestController
public class GeneralController {

	@Autowired
	CustomConfig config;

	@GetMapping(value = "/random", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getRandom() {
		return "random number is " + new Random().nextInt(10);
	}

	@GetMapping(value = "/seriousInfo1", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getSeriousInfo1() {
		return config.getUsers().toString();
		}

	@PreAuthorize("hasAuthority('SERVICE2')")
	@GetMapping(value = "/seriousInfo2", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getSeriousInfo2() {
		return config.getUsers().toString();
	}
}
