import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Katsed {
    public static void main(String[] args) {
        // 1. tund
        // loo Tund objekt
        Tund esimeneTund = new Tund();
        // lisa väärtused
        esimeneTund.tund = "2";
        esimeneTund.algus = "08:30";
        esimeneTund.lopp = "10:00";
        esimeneTund.aine = "Mobiilirakenduse kasutajaliidese disain";
        esimeneTund.grupp = "VS18";
        esimeneTund.opetaja = "Ly Otsa";
        esimeneTund.ruum = "Kopli A - A418 (arvutiklass)";

        // 2. tund
        // loo Tund objekt
        Tund teineTund = new Tund();
        // lisa väärtused
        teineTund.tund = "3";
        teineTund.algus = "10:15";
        teineTund.lopp = "11:45";
        teineTund.aine = "Mobiilirakenduse kasutajaliidese disain";
        teineTund.grupp = "VS18";
        teineTund.opetaja = "Ly Otsa";
        teineTund.ruum = "Kopli A - A418 (arvutiklass)";

        // 3. tund
        // loo Tund objekt
        Tund kolmasTund = new Tund();
        // lisa väärtused
        kolmasTund.tund = "4";
        kolmasTund.algus = "11:55";
        kolmasTund.lopp = "14:00";
        kolmasTund.aine = "JAVA programmeerimine";
        kolmasTund.grupp = "VS18";
        kolmasTund.opetaja = "Anna Karutina";
        kolmasTund.ruum = "Kopli A - A411 (arvutiklass)";

        // 4. tund
        // loo Tund objekt
        Tund neljasTund = new Tund();
        // lisa väärtused
        neljasTund.tund = "5";
        neljasTund.algus = "14:10";
        neljasTund.lopp = "15:40";
        neljasTund.aine = "JAVA programmeerimine";
        neljasTund.grupp = "VS18";
        neljasTund.opetaja = "Anna Karutina";
        neljasTund.ruum = "Kopli A - A411 (arvutiklass)";

        // 5. tund
        // loo Tund objekt
        Tund viiesTund = new Tund();
        // lisa väärtused
        viiesTund.tund = "6";
        viiesTund.algus = "15:45";
        viiesTund.lopp = "17:15";
        viiesTund.aine = "JAVA programmeerimine";
        viiesTund.grupp = "VS18";
        viiesTund.opetaja = "Anna Karutina";
        viiesTund.ruum = "Kopli A - A411 (arvutiklass)";

        // koostame nimekiri tundidest, mis võivad toimuda päeval
        ArrayList<Tund> tunnid = new ArrayList<Tund>();
        tunnid.add(esimeneTund);
        tunnid.add(teineTund);
        tunnid.add(kolmasTund);
        tunnid.add(neljasTund);
        tunnid.add(viiesTund);

        // loome tunniplaan tundide nimekirja abil
        Tunniplaan vs18 = new Tunniplaan();
//        vs18.nadal = "2019-11-18";
//        vs18.tunnid = new HashMap<String, List<Tund>>();
//        vs18.tunnid.put("2019-11-18", tunnid);

        // kontrollime tunniplaani andmed
//        System.out.println(vs18);

        // loome JSON andmestik loodud tüübide põhjal
        Gson g = new Gson();
        String vs18JSON = g.toJson(vs18);
//        System.out.println(vs18JSON);

        // tunniplaani json lugemine
        try {
            URL url = new URL("https://tkhk.siseveeb.ee/veebilehe_andmed/tunniplaan/tunniplaan?nadal=18.11.2019&grupp=1282");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = "";
            String result = "";
            while (null != (str = br.readLine())) {
                result += str;
            }
            // siin kohal olemas vajalikud andmed
            // kasutame need tunniplaani ehitamiseks
            Gson tunniplaaniJSON = new Gson();
            // loeme andmed meie loodud struktuuri sisse
            vs18 = tunniplaaniJSON.fromJson(result, Tunniplaan.class);
            for (Map.Entry<String, List<Tund>> element: vs18.tunnid.entrySet()){
                // näitame kuupäevad
                System.out.println(element.getKey());
                // näitame antud kuupäeva tunnid
                for (Tund tund: element.getValue()) {
                    System.out.println(tund);
                    System.out.println("------------------");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }
}