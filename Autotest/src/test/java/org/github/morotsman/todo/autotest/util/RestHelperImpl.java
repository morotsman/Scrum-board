package org.github.morotsman.todo.autotest.util;


import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestHelperImpl<T> implements RestHelper<T> {

    private RestTemplate restTemplate;

    public RestHelperImpl(){
        restTemplate= new RestTemplate();
    }



    public static String getSelfRef(ResourceSupport resource){
        return resource.getLink("self").getHref();
    }

    @Override
    public ResponseEntity<String> putResource(String resourcePath) {
        return putResource(resourcePath,null);
    }

    public ResponseEntity<String> putResource(final String resourcePath, T body){
        try{
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<T> requestEntity;
            if(body == null){
                requestEntity = new HttpEntity<T>(requestHeaders);
            }else{
                requestEntity = new HttpEntity<T>(body, requestHeaders);
            }
            String resource =  "{resourcePath}";
            return restTemplate.exchange(resource,
                    HttpMethod.PUT, requestEntity, String.class,resourcePath);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<String>(e.getStatusCode());
        }
    }

    public ResponseEntity<String> postResource(final String resourcePath, T body){
        try{
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<T> requestEntity = new HttpEntity<T>(body,requestHeaders);
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
            return restTemplate.getForEntity(resourcePath, String.class);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<String>(e.getStatusCode());
        }
    }

    public ResponseEntity<String> deleteResource(final String resourcePath){
        try{
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<T> requestEntity = new HttpEntity<T>(requestHeaders);
            String resource =  "{resourcePath}";
            return restTemplate.exchange(resource,
                    HttpMethod.DELETE, requestEntity, String.class,resourcePath);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<String>(e.getStatusCode());
        }
    }




}
