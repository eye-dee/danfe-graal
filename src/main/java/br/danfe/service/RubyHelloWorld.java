package br.danfe.service;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RubyHelloWorld implements CommandLineRunner {

    @PostConstruct
    public void rubyHelloWorld() {
        log.info("rubyHelloWorld started");
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value array = polyglot.eval("ruby", "[1,2,42,4]");
        int result = array.getArrayElement(2).asInt();
        log.info("result {}", result);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("rubyHelloWorld runner started");
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value array = polyglot.eval("ruby", "[1,2,42,4]");
        int result = array.getArrayElement(2).asInt();
        log.info("runner result {}", result);
    }
}
