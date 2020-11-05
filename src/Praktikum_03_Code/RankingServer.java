package Praktikum_03_Code;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RankingServer implements CommandExecutor{

    private List<Competitor_ToDo> list;

    public RankingServer() {
        list = new ArrayList<>();
    }

    public List<Competitor_ToDo> getList(String args) throws ParseException {
        Scanner sc = new Scanner(args).useDelimiter("\n");
        while(sc.hasNext()) {
            Scanner sc1 = new Scanner(sc.next()).useDelimiter(";");
            int startNr = sc1.nextInt();
            String name = sc1.next();
            int jg = sc1.nextInt();
            String country = sc1.next();
            String time = sc1.next();
            Competitor_ToDo competitor = new Competitor_ToDo(startNr, name, jg, country, time);
            list.add(competitor);
        }

        //A3: sort list via time and set rankings
        Collections.sort(list);
        int i = 1;
        for(Competitor_ToDo c : list) {
            c.setRank(i);
            i++;
        }
        //A4: sort list via name or jg
        list.sort(Competitor_ToDo.Comparators.NAME);
        return list;
    }

    @Override
    public String execute(String command) throws Exception {
        List<Competitor_ToDo> list2 = getList(command);
        StringBuilder s = new StringBuilder();
        for(Competitor_ToDo c: list2) {
            s.append(c.toString());
            s.append("\n");
        }
        return s.toString();
    }

}
