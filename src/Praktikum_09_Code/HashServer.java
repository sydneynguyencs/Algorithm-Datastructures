package Praktikum_09_Code;


import Praktikum_03_Code.Competitor_ToDo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashServer implements CommandExecutor{
    private Map<Integer,Competitor> map;
    private final String newLine = "\n";
    private final String semicolon = ";";

    public HashServer() {
        map = new HashMap<>();
    }

    @Override
    public String execute(String command) throws Exception {
        String output = "";
        if(command.toUpperCase().startsWith("GET")) {
            Scanner sc = new Scanner(command).skip("GET ").useDelimiter(semicolon);
            while(sc.hasNext()) {
                String name = sc.next();
                int jg = sc.nextInt();
                int hash = 1;
                hash = hash * 13 + name.hashCode();
                hash = hash * 17 + jg;

                Competitor c = map.get(hash);
                if (c == null) {
                    output = "Not found.\n";
                }
                output = String.format("%s %s -> Rank: %s %s",c.getName(),c.getJg(),c.getRank(),c.toString());
            }
        }else {
            addToHashTable(command);
            output = "Added to map.\n";
        }
        return output;
    }

    private void addToHashTable(String command) throws Exception {
        Scanner sc = new Scanner(command).useDelimiter(newLine);
        while(sc.hasNext()) {
            Scanner sc1 = new Scanner(sc.next()).useDelimiter(semicolon);
            int startNr = sc1.nextInt();
            String name = sc1.next();
            int jg = sc1.nextInt();
            String country = sc1.next();
            String time = sc1.next();
            Competitor competitor = new Competitor(startNr, name, jg, country, time);
            map.put(competitor.hashCode(),competitor);
        }
    }
}
