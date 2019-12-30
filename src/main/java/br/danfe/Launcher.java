package br.danfe;

import org.graalvm.polyglot.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        try (Context context = Context.create()) {
            context.eval("ruby", "puts 'Hello, world!'");
        }

        SpringApplication.run(Launcher.class);
    }
}
