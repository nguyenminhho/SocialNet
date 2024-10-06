package com.example.DACN.Dto.Request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GGTokenRequest {
    private String code;
//    private String client_id;
//    private String client_secret;
//    private String redirect_uris;
//    private String grant_type;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String grantType;

}
