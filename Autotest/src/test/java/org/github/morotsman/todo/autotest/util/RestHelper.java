package org.github.morotsman.todo.autotest.util;


import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface RestHelper<T> {

    public ResponseEntity<String> putResource(final String resourcePath, T body);

    public ResponseEntity<String> putResource(final String resourcePath);

    public ResponseEntity<String> postResource(final String resourcePath, T body);

    public ResponseEntity<String> postResource(final String resourcePath);

    public ResponseEntity<String> getResource(String resourcePath);

    public ResponseEntity<String> deleteResource(final String resourcePath);
}
