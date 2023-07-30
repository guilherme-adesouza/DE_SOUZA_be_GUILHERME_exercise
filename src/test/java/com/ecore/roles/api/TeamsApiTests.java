package com.ecore.roles.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.ecore.roles.client.model.Team;
import com.ecore.roles.utils.RestAssuredHelper;

import static com.ecore.roles.utils.RestAssuredHelper.getTeams;
import static com.ecore.roles.utils.RestAssuredHelper.getTeamsById;
import static com.ecore.roles.utils.MockUtils.mockGetTeams;
import static com.ecore.roles.utils.MockUtils.mockGetTeamById;
import static com.ecore.roles.utils.TestData.ORDINARY_CORAL_LYNX_TEAM;
import static com.ecore.roles.utils.TestData.TEAM_LIQUID;
import static com.ecore.roles.utils.TestData.TEAM_LIQUID_UUID;
import static com.ecore.roles.utils.TestData.TEAMS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamsApiTests {

    private final RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @LocalServerPort
    private int port;

    @Autowired
    public TeamsApiTests(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        RestAssuredHelper.setUp(port);
    }

    @Test
    void shouldGetTeams() {
        mockGetTeams(mockServer, TEAMS());

        Team[] teams = getTeams()
                .statusCode(200)
                .extract().as(Team[].class);

        assertThat(teams).contains(TEAM_LIQUID())
                .contains(ORDINARY_CORAL_LYNX_TEAM());
    }

    @Test
    void shouldGetTeamById() {
        mockGetTeamById(mockServer, TEAM_LIQUID_UUID, TEAM_LIQUID());

        Team team = getTeamsById(TEAM_LIQUID_UUID)
                .statusCode(200)
                .extract().as(Team.class);

        assertThat(team).isEqualTo(TEAM_LIQUID());
    }

}
