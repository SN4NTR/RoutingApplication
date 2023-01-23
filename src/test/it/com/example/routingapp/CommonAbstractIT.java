package com.example.routingapp;

import com.example.routingapp.core.web.converter.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public abstract class CommonAbstractIT {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected RestTemplate restTemplate;

    protected <T> T convertResult(final MvcResult result, final Class<T> tClass) {
        try {
            final String json = result.getResponse().getContentAsString();
            return JsonConverter.fromJson(json, tClass);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
