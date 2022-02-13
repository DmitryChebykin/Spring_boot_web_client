package com.example.admitad.tokenModel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@Builder
@ToString
public class TokenData {
    private String authToken;

    private Integer expiresIn;

    private String refreshToken;

    private Date receivedDate;
}
