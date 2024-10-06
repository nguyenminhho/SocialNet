package com.example.DACN.Repository;

import com.example.DACN.Dto.Request.GGTokenRequest;
import com.example.DACN.Dto.Response.GGTokenResponse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient(name = "outbound-identity", url = "https://oauth2.googleapis.com")
public interface LoginWithGG {
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    GGTokenResponse exchangeToken(@QueryMap GGTokenRequest request);
}