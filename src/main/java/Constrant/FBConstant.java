package Constrant;

public interface FBConstant {
    public static final String FACEBOOK_CLIENT_ID = "";
    public static final String FACEBOOK_CLIENT_SECRET = "";
    public static final String FACEBOOK_REDIRECT_URI = "http://localhost:8080/callback";
    public static final String FACEBOOK_LINK_GET_TOKEN="https://www.facebook.com/v20.0/dialog/oauth/access_token";
    public static final String FACEBOOK_LINK_GET_USER_INFO = "https://graph.facebook.com/me?fields=id,name,email,picture&access_token=";
}
