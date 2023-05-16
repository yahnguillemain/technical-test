package com.lombardinternational.technicaltest.sprinsecurity.controller;

import com.lombardinternational.technicaltest.sprinsecurity.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Here we want to implement security using spring security with database as User Directory (cf data.sql)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(SecurityConfig.class)
public class SecuredControllerTest {

    public static final String DISABLED_USER_LOGINPWD = "disabled/disabled";
    public static final String READER_USER_LOGINPWD = "reader/reader";
    public static final String WRITER_USER_LOGINPWD = "writer/writer";
    public static final String MINOR_USER_LOGINPWD = "minor/minor";
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPublicAccess() throws Exception {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/security/public", String.class),
            equalTo("No need to be authenticated to read that"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "reader/wrongpwd", "intruder/password" })
    public void testPrivateAccessWithWrongAuth1(String loginPwd) throws Exception {
        String[] cred = loginPwd.split("/");
        ResponseEntity<String> result = restTemplate.withBasicAuth(cred[0], cred[1])
                                                    .postForEntity("/security/public", "", String.class);
        assertThat(result.getStatusCode().value(), equalTo(401));
    }

    @ParameterizedTest
    @ValueSource(strings = { "reader/wrongpwd", "intruder/password" })
    public void testPrivateAccessWithWrongAuth2(String loginPwd) throws Exception {
        String[] cred = loginPwd.split("/");
        ResponseEntity<String> result = restTemplate.withBasicAuth(cred[0], cred[1])
                                                    .getForEntity("/security/private", String.class);
        assertThat(result.getStatusCode().value(), equalTo(401));
    }

    @ParameterizedTest
    @ValueSource(strings = { DISABLED_USER_LOGINPWD })
    public void testPrivateAccessWithDisabledUser(String loginPwd) throws Exception {
        String[] cred = loginPwd.split("/");
        ResponseEntity<String> result = restTemplate.withBasicAuth(cred[0], cred[1])
                                                    .getForEntity("/security/private", String.class);
        assertThat(result.getStatusCode().value(), equalTo(401));
    }

    @ParameterizedTest
    @ValueSource(strings = { READER_USER_LOGINPWD, WRITER_USER_LOGINPWD, MINOR_USER_LOGINPWD })
    public void testPrivateAccessWithAuthAllowed1(String loginPwd) {
        String[] cred = loginPwd.split("/");
        ResponseEntity<String> response =
            restTemplate.withBasicAuth(cred[0], cred[1]).getForEntity("http://localhost:" + port + "/security/private", String.class);
        assertThat(response.getStatusCode().value(), equalTo(200));
        assertThat(response.hasBody(), equalTo(true));
        assertThat(response.getBody(), equalTo("Must be authenticated to read that"));
    }

    @ParameterizedTest
    @ValueSource(strings = { WRITER_USER_LOGINPWD, MINOR_USER_LOGINPWD })
    public void testPrivateAccessWithAuthAllowed2(String loginPwd) {
        String[] cred = loginPwd.split("/");
        ResponseEntity<String> response =
            restTemplate.withBasicAuth(cred[0], cred[1]).postForEntity("http://localhost:" + port + "/security/public", "", String.class);
        assertThat(response.getStatusCode().value(), equalTo(200));
        assertThat(response.hasBody(), equalTo(true));
        assertThat(response.getBody(), equalTo("Need to be authenticated to write that"));
    }

    @ParameterizedTest
    @ValueSource(strings = { WRITER_USER_LOGINPWD, MINOR_USER_LOGINPWD })
    public void testPrivateAccessWithAuthenticatedAndAuthorizedUser(String loginPwd) {
        String[] cred = loginPwd.split("/");
        ResponseEntity<String> response =
            restTemplate.withBasicAuth(cred[0], cred[1]).postForEntity("http://localhost:" + port + "/security/private", "", String.class);
        assertThat(response.getStatusCode().value(), equalTo(200));
        assertThat(response.hasBody(), equalTo(true));
        assertThat(response.getBody(), equalTo("Must be authenticated and has CAN_WRITE or ADMIN permission to do that"));
    }

    @ParameterizedTest
    @ValueSource(strings = { READER_USER_LOGINPWD })
    public void testPrivateAccessWithAuthenticatedButNotAuthorizedUser1(String loginPwd) {
        String[] cred = loginPwd.split("/");
        ResponseEntity<String> response =
            restTemplate.withBasicAuth(cred[0], cred[1]).postForEntity("http://localhost:" + port + "/security/private", "", String.class);
        assertThat(response.getStatusCode().value(), equalTo(403));
    }

    @ParameterizedTest
    @ValueSource(strings = { READER_USER_LOGINPWD, WRITER_USER_LOGINPWD })
    public void testPrivateAccessAgeOK(String loginPwd) {
        String[] cred = loginPwd.split("/");
        ResponseEntity<String> response =
            restTemplate.withBasicAuth(cred[0], cred[1]).getForEntity("http://localhost:" + port + "/security/private/adult_only", String.class);
        assertThat(response.getStatusCode().value(), equalTo(200));
        assertThat(response.hasBody(), equalTo(true));
        assertThat(response.getBody(), equalTo("Must be at least 18 years old to see that"));
    }

    @ParameterizedTest
    @ValueSource(strings = { MINOR_USER_LOGINPWD })
    public void testPrivateAccessAgeKO(String loginPwd) {
        String[] cred = loginPwd.split("/");
        ResponseEntity<String> response =
            restTemplate.withBasicAuth(cred[0], cred[1])
                        .getForEntity("http://localhost:" + port + "/security/private/adult_only", String.class);
        assertThat(response.getStatusCode().value(), equalTo(403));
    }
}
