package org.dd.demoapp.delegate;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Transaction;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import javax.annotation.Resource;
import javax.inject.Singleton;
import java.util.List;

@Resource
@Singleton
@RegisterMapper(DelegateDbMapper.class)
public abstract class DelegateDAO {

    @SqlUpdate("create table if not exists delegate (id bigint identity, delegate_reference varchar, name varchar, description text, logo_url varchar, webpage_url varchar)")
    abstract void createTable();

    @SqlUpdate("insert into delegate (name, delegate_reference, description, logo_url, webpage_url) values (:name, :delegate_reference, :description, :logo_url, :webpage_url)")
    abstract void insertRow(@Bind("name") String name, @Bind("delegate_reference") String delegateReference, @Bind("description") String description, @Bind("logo_url") String logoUrl, @Bind("webpage_url") String webpageUrl);

    @SqlQuery("select * from delegate")
    abstract List<Delegate> getAll();

    @SqlQuery("select * from delegate where delegate_reference = :delegate_reference")
    public abstract Delegate get(@Bind("delegate_reference") String delegateReference);

    private final String moderaternaDescription = "";
    private final String centerpartietDescription = "Alla människors lika värde och rättigheter är grunden för Centerpartiets politik. Var och en ska kunna växa som människa och ha möjlighet att förverkliga sina drömmar. Ansvar för varandra och för naturens ekosystem ska vara vägledande när samhället formas.\n" +
            "Vartannat år hålls en stämma som beslutar om Centerpartiets organisation och politiska inriktning. Mellan stämmorna, finns det en partistyrelse som är utsedd att fatta de beslut som krävs för att verksamheten ska kunna jobba snabbt under året.\n" +
            "Du som medlem i Centerpartiet, blir också en del av en krets. Vilken krets du tillhör beror på var du bor.\n" +
            "Centerpartiet har också tre systerförbund. Centerstudenter, Centerpartiets ungdomsförbund och Centerkvinnorna. Är du medlem i något av de förbunden, har du också medlemsrättigheter i Centerpartiet.";

    private final String kristdemokraternaDescription = "Kristdemokraterna arbetar för ett samhälle där fler tar ansvar och bryr sig om varandra. Våra mest prioriterade områden är barn och ungas uppväxtvillkor, valfrihet och rättvisa för familjen, värdig vård och omsorg samt ett blomstrande näringsliv.\n" +
            "Vi sätter barn och ungas uppväxtvillkor i fokus för all politik och vill göra Sverige till världens bästa land att växa upp i. Våra förslag kännetecknas av vår syn på familjens betydelse, på det civila samhällets roll och politikens gränser.\n" +
            "Vårt partinamn berättar vad vi står för – en demokrati byggd på kristen människosyn och värdegrund. En vanlig missuppfattning är att man måste vara religiöst engagerad för att vara med. Det är fel. Alla som delar våra idéer är välkomna i partiet.\n" +
            "Kristdemokraterna fick sitt första riksdagsmandat 1991. Vi har drygt 21 600 medlemmar. Partiets ordförande heter Ebba Busch Thor. Sedan valet 2014 är Kristdemokraterna ett oppositionsparti. Mellan 2006-2014 ingick partiet tillsammans med Moderaterna, Centerpartiet och Folkpartiet i den borgerliga Alliansen som styrde Sverige.\n" +
            "På europeisk nivå utgör Kristdemokraterna en del av EPP - European People´s Party - en sammanslutning av kristdemokratiska partier i Europa.";

    private final String liberalernaDescription = "<h3>Politisk frihet och demokrati</h3>\n" +
            "Varje människa har rätt till sin politiska övertygelse, sin religion och livsåskådning. Det viktigaste sättet att trygga denna frihet är demokratin. Den bygger på politisk frihet, allmän rösträtt och rättssäkerhet.\n" +
            "<h3>Ekonomisk frihet och marknadsekonomi</h3>\n" +
            "Alla ska ha rätt att välja yrke, konsumtion, bosättning och livsstil. Det viktigaste sättet att trygga denna frihet är marknadsekonomin. Den bygger på enskilt ägande, närings- och avtalsfrihet. Konsumenternas val styr produktionen. Marknadsekonomin är det enda ekonomiska system som går att förena med demokrati.\n" +
            "<h3>Rättvisa och solidaritet</h3>\n" +
            "Alla människor ska ha goda förutsättningar att utvecklas. Alla former av diskriminering måste därför bekämpas. De sämst ställdas villkor är alltid en viktig utgångspunkt för vårt arbete. I ett liberalt välfärdssamhälle bidrar stat, kommuner och frivilliga gemenskaper till alla människors trygghet och förankringar.\n" +
            "<h3>Feminism och jämställdhet</h3>\n" +
            "Liberal feminism handlar om kvinnors och mäns lika rätt till frihet, ansvar och makt. Det liberala uppdraget går ut på att frigöra individen från de begränsningar som bristande jämställdhet innebär, och bekämpa de attityder och föreställningar som ligger bakom.\n" +
            "<h3>Internationalism och internationell solidaritet</h3>\n" +
            "Liberaler är världsmedborgare. Människors rätt till frihet måste värnas oavsett var på jorden de befinner sig. Hunger och fattigdom måste bekämpas. Vi arbetar för global frihandel och en generös biståndspolitik. Vi vill stärka demokratin och marknadsekonomin i alla världens länder. Den liberala visionen är att fri rörlighet för individen ska vara en mänsklig rättighet. Alla ska tillåtas leva och verka där i världen som de själva vill.";

    private final String socialdemokraternaDescription = "";

    private final String vansterpartietDescription = "";

    private final String miljopartietDescription = "";

    private final String sverigedemokraternaDescription = "";

    @Transaction
    public void initDb() {
        createTable();
        insertRow("Centerpartiet", "c", centerpartietDescription, "/assets/images/parties/c.png", "http://www.centerpartiet.se");
        insertRow("Kristdemokraterna", "kd", kristdemokraternaDescription, "/assets/images/parties/kd.png", "https://www.kristdemokraterna.se");
        insertRow("Liberalerna", "l", liberalernaDescription, "/assets/images/parties/l.png", "https://www.liberalerna.se");
        insertRow("Miljöpartiet", "mp", miljopartietDescription, "/assets/images/parties/mp.png", "http://www.mp.se");
        insertRow("Moderaterna", "m", moderaternaDescription, "/assets/images/parties/m.png", "http://www.moderaterna.se");
        insertRow("Socialdemokraterna", "s", socialdemokraternaDescription, "/assets/images/parties/s.png", "http://www.socialdemokraterna.se");
        insertRow("Sverigedemokraterna", "sd", sverigedemokraternaDescription, "/assets/images/parties/sd.png", "https://sd.se");
        insertRow("Vänsterpartiet", "v", vansterpartietDescription, "/assets/images/parties/v.png", "http://www.vansterpartiet.se");
    }
}
