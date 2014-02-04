package org.github.morotsman.todo.autotest.util;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HateoasAwareRestHelperImpl<T extends ResourceSupport> implements HateoasAwareRestHelper<T> {

    private final RestHelper<T> subject;

    private List<String> createdResources = new ArrayList<String>();

    private ObjectMapper mapper = new ObjectMapper();

    private Class<T> responseClass;

    public HateoasAwareRestHelperImpl(RestHelper<T> subject, Class<T> responseClass) {
        this.subject = subject;
        this.responseClass = responseClass;
    }

    @Override
    public ResponseEntity<String> putResource(String resourcePath, T body) {
        ResponseEntity<String> response =  subject.putResource(resourcePath, body);
        if(response.getBody() == null){
            return response;
        }
        ResourceSupport resourceSupport = null;
        try {
            resourceSupport = mapper.readValue(response.getBody(), responseClass);
        } catch (IOException e) {
            throw new RuntimeException("Could not access the self ref.");
        }
        createdResources.add(resourceSupport.getLink("self").getHref());
        return response;
    }

    @Override
    public ResponseEntity<String> putResource(String resourcePath) {
        return putResource(resourcePath,null);
    }

    @Override
    public ResponseEntity<String> postResource(String resourcePath) {
        return postResource(resourcePath,null);
    }

    @Override
    public ResponseEntity<String> postResource(String resourcePath, T body) {
        ResponseEntity<String> response =  subject.postResource(resourcePath, body);
        if(response.getBody() == null){
            return response;
        }
        ResourceSupport resourceSupport = null;
        try {
            resourceSupport = mapper.readValue(response.getBody(), responseClass);
        } catch (IOException e) {
            throw new RuntimeException("Could not access the self ref.");
        }
        createdResources.add(resourceSupport.getLink("self").getHref());
        return response;
    }

    @Override
    public ResponseEntity<String> getResource(String resourcePath) {
        return subject.getResource(resourcePath);
    }

    @Override
    public ResponseEntity<String> deleteResource(String resourcePath) {
        createdResources.remove(resourcePath);
        return subject.deleteResource(resourcePath);
    }


    public void cleanUp(){
        for(int i = createdResources.size()-1; i > -1; i--) {
            System.out.println("Deleting: " + createdResources.get(i));
            deleteResource(createdResources.get(i));
        }
    }
}
