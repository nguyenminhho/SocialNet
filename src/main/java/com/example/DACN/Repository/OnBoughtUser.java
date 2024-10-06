package com.example.DACN.Repository;

import com.example.DACN.Dto.Response.OnBoundEmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "outbound-user", url = "https://www.googleapis.com")
public interface OnBoughtUser {
    @GetMapping(value = "/oauth2/v1/userinfo")
    OnBoundEmailResponse getUSerInfo(@RequestParam("alt") String alt,
                                     @RequestParam("access_token") String accessToken);
}