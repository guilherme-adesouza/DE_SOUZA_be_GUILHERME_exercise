package com.ecore.roles.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.ecore.roles.client.model.User;
import com.ecore.roles.utils.RestAssuredHelper;

import static com.ecore.roles.utils.RestAssuredHelper.getUsers;
import static com.ecore.roles.utils.RestAssuredHelper.getUserById;
import static com.ecore.roles.utils.MockUtils.mockGetUsers;
import static com.ecore.roles.utils.MockUtils.mockGetUserById;
import static com.ecore.roles.utils.TestData.GIANNI_USER;
import static com.ecore.roles.utils.TestData.HAPPY_USER;
import static com.ecore.roles.utils.TestData.HAPPY_USER_UUID;
import static com.ecore.roles.utils.TestData.USERS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersApiTests {

    private final RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @LocalServerPort
    private int port;

    @Autowired
    public UsersApiTests(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        RestAssuredHelper.setUp(port);
    }

    @Test
    void shouldGetUsers() {
        mockGetUsers(mockServer, USERS());

        User[] users = getUsers()
                .statusCode(200)
                .extract().as(User[].class);

        assertThat(users).contains(HAPPY_USER())
                .contains(GIANNI_USER());
    }

    @Test
    void shouldGetUserId() {
        mockGetUserById(mockServer, HAPPY_USER_UUID, HAPPY_USER());

        User user = getUserById(HAPPY_USER_UUID)
                .statusCode(200)
                .extract().as(User.class);

        assertThat(user).isEqualTo(HAPPY_USER());
    }

}
