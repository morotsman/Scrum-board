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

    private final Class clazz;

    public RestHelperImpl(Class clazz){
        restTemplate= new RestTemplate();
        this.clazz = clazz;
    }

    public static String getSelfRef(ResourceSupport resource){
        return resource.getLink("self").getHref();
    }

    public ResponseEntity<T> putResource(final String resourcePath, T body){
        try{
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<T> requestEntity;
            if(body == null){
                requestEntity = new HttpEntity(requestHeaders);
            }else{
                requestEntity = new HttpEntity(body, requestHeaders);
            }
            String resource =  "{resourcePath}";
            return restTemplate.exchange(resource,
                    HttpMethod.PUT, requestEntity, clazz,resourcePath);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<T>(e.getStatusCode());
        }
    }

    public ResponseEntity<T> postResource(final String resourcePath, T body){
        try{
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<T> requestEntity = new HttpEntity(body,requestHeaders);
            String resource =  "{resourcePath}";
            return restTemplate.exchange(resource,
                    HttpMethod.POST, requestEntity, clazz,resourcePath);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<T>(e.getStatusCode());
        }
    }

    public ResponseEntity<T> getResource(String resourcePath){
        try{
            return restTemplate.getForEntity(resourcePath, clazz);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<T>(e.getStatusCode());
        }
    }

    public ResponseEntity<T> deleteResource(final String resourcePath){
        try{
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<T> requestEntity = new HttpEntity(requestHeaders);
            String resource =  "{resourcePath}";
            return restTemplate.exchange(resource,
                    HttpMethod.DELETE, requestEntity, clazz,resourcePath);
        }catch(HttpClientErrorException e){
            return new ResponseEntity<T>(e.getStatusCode());
        }
    }


}
