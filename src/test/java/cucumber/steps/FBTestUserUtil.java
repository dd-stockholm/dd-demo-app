package cucumber.steps;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import static org.apache.http.client.fluent.Request.Post;

public class FBTestUserUtil {

    Gson gson = new Gson();
    
    String appId;
    String appSecret;
    
    public FBTestUserUtil(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }
    
    AccessTokenResponse accessTokenResponse;
    
    private String getAccessToken() throws IOException {
        if(accessTokenResponse == null) {
            String response = Request.Get(
                    "https://graph.facebook.com/v2.5/oauth/access_token"
                    + "?client_id=" + appId
                    + "&client_secret=" + appSecret
                    + "&grant_type=client_credentials"
            ).execute().returnContent().asString();

            accessTokenResponse = gson.fromJson(response, AccessTokenResponse.class);
        }
        
        return accessTokenResponse.accessToken;
    }
    
    public FBTestUser getNewUser() throws IOException {
        String response = Post("https://graph.facebook.com/v2.5/" + appId + "/accounts/test-users")
                .addHeader("Authorization", "Bearer " + getAccessToken())
                .execute()
                .returnContent().asString();
        
        return gson.fromJson(response, FBTestUser.class);
    }
   
    public static class FBTestUser {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("access_token")
        @Expose
        public String accessToken;
        @SerializedName("login_url")
        @Expose
        public String loginUrl;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("password")
        @Expose
        public String password;
    }
    
    public static class AccessTokenResponse {

        @SerializedName("access_token")
        @Expose
        public String accessToken;
        @SerializedName("token_type")
        @Expose
        public String tokenType;
    }
}
