package tests.api.environmentApi;

import lombok.Getter;

@Getter
public class AuthData {
    private String token;
    private String userId;
    private String expires;

    public AuthData(String token, String userId, String expires) {
        this.token = token;
        this.userId = userId;
        this.expires = expires;
    }
}
