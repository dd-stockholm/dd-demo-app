package org.dd.demoapp.riksdagen.betankande;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.dd.demoapp.riksdagen.QuestionImportItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    private final URL source;
    private final ObjectMapper mapper;

    public Parser(URL source) {
        this.source = source;
        this.mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new Jdk8Module());
    }

    public List<QuestionImportItem> parseQuestions() {

        List<QuestionImportItem> rawItems = new ArrayList<>();
        try {
            Optional<RiksdagsBetankandeData.RiksdagsDokumentLista> dokumentLista =
                Optional.ofNullable(mapper.readValue(source, RiksdagsBetankandeData.class).dokumentlista);

            dokumentLista.ifPresent(l -> rawItems.addAll(l.dokument.stream().filter(Parser::votable).collect(Collectors.toList())));

        } catch (IOException e) {
            LOGGER.error("Failed to parse Riksdags data!", e);
        }

        return rawItems;
    }

    private static boolean votable(QuestionImportItem item) {
        boolean undecided = !item.isDecided();
        boolean hasVoteDate = item.getCloseTime().isPresent();
        return undecided && hasVoteDate;
    }

    private static class RiksdagsBetankandeData {

        private RiksdagsDokumentLista dokumentlista;

        public RiksdagsDokumentLista getDokumentlista() {
            return dokumentlista;
        }

        public void setDokumentlista(RiksdagsDokumentLista dokumentlista) {
            this.dokumentlista = dokumentlista;
        }

        private static class RiksdagsDokumentLista {
            private List<QuestionImportItem> dokument;

            public List<QuestionImportItem> getDokument() {
                return dokument;
            }

            public void setDokument(List<QuestionImportItem> dokument) {
                this.dokument = dokument;
            }
        }
    }
}
