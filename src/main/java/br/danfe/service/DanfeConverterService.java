package br.danfe.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.graalvm.polyglot.Context;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DanfeConverterService {

    private final Context polyglot = Context.newBuilder()
            .allowAllAccess(true)
            .build();

    @PreDestroy
    public void initRubyDanfe() {
        polyglot.close();
    }

    public Mono<String> convertToPdf(Part part) {
        return part.content()
                .flatMap(data -> {
                    try {
                        return Flux.fromIterable(IOUtils.readLines(data.asInputStream(), StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        return Flux.empty();
                    }
                })
                .collectList()
                .map(list -> String.join("", list))
                .map(xml -> {
                    polyglot.eval("ruby", "require \"ruby_danfe\"");

                    String outputFileName = UUID.randomUUID().toString();

                    polyglot.getPolyglotBindings().putMember("xml", xml);
                    polyglot.getPolyglotBindings().putMember("output", outputFileName);
                    polyglot.eval("ruby",
                            "xml = Polyglot.import('xml')\n" +
                                    "output = Polyglot.import('output')\n" +
                                    "pdf = RubyDanfe.generatePDF(xml)\n" +
                                    "pdf.render_file (output + \".pdf\")");
                    return outputFileName + ".pdf";
                });
    }
}
