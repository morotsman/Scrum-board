package org.github.morotsman.todo.autotest.util;


import org.springframework.http.ResponseEntity;

public interface RestHelper<T> {

    public ResponseEntity<T> putResource(final String resourcePath, T body);

    public ResponseEntity<T> postResource(final String resourcePath, T body);

    public ResponseEntity<T> getResource(String resourcePath);

    public ResponseEntity<T> deleteResource(final String resourcePath);
}
