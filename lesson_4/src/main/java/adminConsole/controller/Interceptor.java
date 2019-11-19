package adminConsole.controller;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Interceptor {
    @AroundInvoke
    public Object printLog(InvocationContext ctx) throws Exception{
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Вызван метод " +
                ctx.getMethod().getName());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        return ctx.proceed();
    }
}

