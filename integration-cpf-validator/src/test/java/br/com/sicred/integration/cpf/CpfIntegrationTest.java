package br.com.sicred.integration.cpf;


import br.com.sicred.integration.cpf.config.WebClientCpfIntegration;
import br.com.sicred.integration.cpf.stub.CpfIntegrationBodyStub;
import br.com.sicred.integration.cpf.stub.CpfIntegrationRequestStub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CpfIntegration.class, WebClientCpfIntegration.class})
@TestPropertySource(locations = "/application.properties", properties = { "api.cpf.validation.url = ${server.url}" })
@MockServerTest(value = "server.url=http://localhost:${mockServerPort}")
public class CpfIntegrationTest {

    @Autowired
    CpfIntegration cpfIntegration;

    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule()
                .addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .addSerializer(new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE))
                .addSerializer(NumberSerializer.bigDecimalAsStringSerializer())
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE))
                .addDeserializer(LocalDateTime.class,
                        new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE))
                .addDeserializer(BigDecimal.class, new NumberDeserializers.BigDecimalDeserializer());

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
    MockServerClient mockServerClient;

    @Test
    void mustReturn200ABLE_TO_VOTE() throws JsonProcessingException {
        final var body = CpfIntegrationBodyStub.createReturnAbleToVote();
        final var request = CpfIntegrationRequestStub.createRequest();
        final var httpRequest = request("/users/".concat(request.getCpf()))
                .withMethod("GET")
                .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .withHeader(HttpHeaders.ACCEPT, "application/json");

        final var httpResponse = response()
                .withStatusCode(200)
                .withContentType(MediaType.JSON_UTF_8)
                .withBody(objectMapper().writeValueAsString(body));

        mockServerClient.when(httpRequest).respond(httpResponse);

        StepVerifier.create(cpfIntegration.doVerification(request))
                .assertNext(response -> Assertions.assertEquals(body, response))
                .verifyComplete();
    }

    @Test
    void mustReturn200UABLE_TO_VOTE() throws JsonProcessingException {
        final var body = CpfIntegrationBodyStub.createReturnUAbleToVote();
        final var request = CpfIntegrationRequestStub.createRequest();
        final var httpRequest = request("/users/".concat(request.getCpf()))
                .withMethod("GET")
                .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .withHeader(HttpHeaders.ACCEPT, "application/json");

        final var httpResponse = response()
                .withStatusCode(200)
                .withContentType(MediaType.JSON_UTF_8)
                .withBody(objectMapper().writeValueAsString(body));

        mockServerClient.when(httpRequest).respond(httpResponse);

        StepVerifier.create(cpfIntegration.doVerification(request))
                .assertNext(response -> Assertions.assertEquals(body, response))
                .verifyComplete();
    }
}
