package org.github.morotsman.todo.autotest.util;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class RestHelperImpl<T> implements RestHelper<T> {

    private RestTemplate restTemplate;

    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public RestHelperImpl(){
        restTemplate= new RestTemplate();
    }

    @Override
    public ResponseEntity<String> putResource(String resourcePath) {
        return putResource(resourcePath,null);
    }

    public ResponseEntity<String> putResource(final String resourcePath, T body){
        try{
            HttpEntity<T> requestEntity;
            if(body == null){
                requestEntity = new HttpEntity<T>(getLoginHeaders());
            }else{
                requestEntity = new HttpEntity<T>(body, getLoginHeaders());
            }
            String resource =  "{resourcePath}";
            return restTemplate.exchange(resource,
                    HttpMethod.PUT, requestEntity, String.class,resourcePath);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<String>(e.getStatusCode());
        }
    }

    private HttpHeaders getLoginHeaders(){
        HttpHeaders requestHeaders = new HttpHeaders();
        final String auth = USER + ":" + PASSWORD;
        final byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
        final String authHeader = "Basic " + new String(encodedAuth);
        requestHeaders.set("Authorization", authHeader);
        return requestHeaders;
    }


    public ResponseEntity<String> postResource(final String resourcePath, T body){
        try{
            HttpEntity<T> requestEntity = new HttpEntity<T>(body,getLoginHeaders());
            String resource =  "{resourcePath}";
            return restTemplate.exchange(resource,
                    HttpMethod.POST, requestEntity, String.class,resourcePath);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<String>(e.getStatusCode());
        }
    }

    @Override
    public ResponseEntity<String> postResource(String resourcePath) {
        return  postResource(resourcePath,null);
    }

    public ResponseEntity<String> getResource(String resourcePath){
        try{
            HttpEntity<T> requestEntity = new HttpEntity<T>(getLoginHeaders());
            String resource =  "{resourcePath}";
            return restTemplate.exchange(resourcePath,
                    HttpMethod.GET, requestEntity, String.class);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<String>(e.getStatusCode());
        }
    }

    public ResponseEntity<String> deleteResource(final String resourcePath){
        try{
            HttpEntity<T> requestEntity = new HttpEntity<T>(getLoginHeaders());
            String resource =  "{resourcePath}";
            return restTemplate.exchange(resource,
                    HttpMethod.DELETE, requestEntity, String.class,resourcePath);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<String>(e.getStatusCode());
        }
    }




}
