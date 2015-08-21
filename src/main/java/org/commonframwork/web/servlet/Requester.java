package org.commonframwork.web.servlet;

/**
 * Created by huyan on 2015/8/21.
 */
public class Requester {

    private String requestMethod;
    private String requestUrl;

    public Requester(String requestMethod, String requestUrl){
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
