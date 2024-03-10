package br.com.sicred.integration.cpf.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.util.unit.DataSize;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebClientCpfIntegration {
    @Value("${api.cpf.validation.url}")
    private String API_CPF_URL;
    private static final int BYTE_COUNT = (int) DataSize.ofMegabytes(1).toBytes();

    @Bean
    public WebClient webClientIntegration() {
        var httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(codecs -> {
                            codecs.defaultCodecs().jackson2JsonEncoder(
                                    new Jackson2JsonEncoder(buildCustomObjectMapper(), MediaType.APPLICATION_JSON));
                            codecs.defaultCodecs().maxInMemorySize(BYTE_COUNT);
                        })
                        .build())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .baseUrl(API_CPF_URL)
                .build();
    }

    private ObjectMapper buildCustomObjectMapper() {
        final var objectMapper = new ObjectMapper();
        final var simpleModule = new SimpleModule()
                .addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .addSerializer(new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE))
                .addSerializer(NumberSerializer.bigDecimalAsStringSerializer())
                .addDeserializer(BigDecimal.class, new NumberDeserializers.BigDecimalDeserializer());

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
