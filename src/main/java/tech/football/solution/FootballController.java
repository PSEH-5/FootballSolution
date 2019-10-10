package tech.football.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FootballController {

	@Autowired
	private FootballProperties configProp;


	@RequestMapping("/standings")
	public ResponseEntity getStandings(@RequestParam(value = "countryId", required = false) String countryId,
			@RequestParam(value = "leagueId", required = false) String leagueId,
			@RequestParam(value = "teamId", required = false) String teamId) {
		if (countryId == null && leagueId == null && teamId == null) {
			return new ResponseEntity<String>("provide input", HttpStatus.BAD_REQUEST);
		} else if (leagueId != null) {

			final String uri = configProp.getConfigValue("football.api.standings.url")+leagueId+"&"+configProp.getConfigValue("football.api.key.url");

			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);

			return new ResponseEntity<String>(result, HttpStatus.OK);
		} else {
			return null;
		}

	}
}