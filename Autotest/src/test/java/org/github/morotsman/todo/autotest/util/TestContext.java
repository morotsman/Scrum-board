package org.github.morotsman.todo.autotest.util;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private Map<String, RestHelper> helpers = new HashMap<String, RestHelper>();

    private static TestContext theContext = new TestContext();

    private int counter = 0;

    private TestContext(){}

    public static TestContext instance(){
        return theContext;
    }

    public TestContext addResource(String resource, RestHelperImpl helperImpl){
        RestHelper proxy = (RestHelper) Proxy.newProxyInstance(RestHelper.class.getClassLoader(),
                new Class<?>[]{RestHelper.class},
                new ResourceInvocationHandler(helperImpl));



        helpers.put(resource, proxy);
        System.out.println("Adding***************** " + counter + " " + helpers.size());
        counter++;
        return this;
    }

    public <T> RestHelper<T> getResource(String resource){
        return helpers.get(resource);
    }

    private class ResourceInvocationHandler implements InvocationHandler {
        private Object realResource;



        public ResourceInvocationHandler(Object realResource) {
            this.realResource = realResource;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            String name = method.getName();
            System.out.println(name);
            return method.invoke(realResource, args);
        }
    }

}
