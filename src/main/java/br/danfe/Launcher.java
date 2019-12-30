package br.danfe;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.boot.SpringApplication;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("rubyHelloWorld started");
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value array = polyglot.eval("ruby", "[1,2,42,4]");
        int result = array.getArrayElement(2).asInt();
        System.out.println("result {}" + result);
        SpringApplication.run(Launcher.class);
    }
}
