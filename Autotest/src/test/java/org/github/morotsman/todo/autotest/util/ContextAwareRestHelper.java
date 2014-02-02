package org.github.morotsman.todo.autotest.util;



public interface ContextAwareRestHelper<T> extends RestHelper<T> {

    public void cleanUp();

}
