package br.danfe.service;

import javax.annotation.PostConstruct;
import org.graalvm.polyglot.Context;
import org.springframework.stereotype.Service;

@Service
public class RubyHelloWorld {

    @PostConstruct
    public void rubyHelloWorld() {
        try (Context context = Context.create()) {
            context.eval("ruby", "puts 'Hello, world!'");
        }
    }

}
