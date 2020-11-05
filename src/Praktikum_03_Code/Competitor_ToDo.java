package Praktikum_03_Code;

import java.util.*;
import java.text.*;

public class Competitor_ToDo implements Comparable<Competitor_ToDo>{
    private String name;
    private String country;
    private long time;
    private int jg;
    private int startNr;
    private int rank;

    public Competitor_ToDo(int startNr, String name, int jg, String country, String time) throws ParseException {
        this.startNr = startNr;
        this.name = name;
        this.jg = jg;
        this.country = country;
        this.time = parseTime(time);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getJg() {
        return jg;
    }

    private static long parseTime(String s) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
        Date date = sdf.parse(s);
        return date.getTime();
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
        StringBuilder sb = new StringBuilder();
        sb.append(rank); sb.append(" ");
        sb.append(name); sb.append(" ");
        sb.append(jg); sb.append(" ");
        sb.append(df.format(new Date(time)));
        return sb.toString();
    }

    // A3 sortiert nach Ranking
    @Override
    public int compareTo(Competitor_ToDo o) {
        return Math.round(this.time - o.getTime());
    }

    // A4 sortiert nach name, dann jahrgang
    public static class Comparators {
        public static Comparator<Competitor_ToDo> NAME = Comparator.comparing(
                (Competitor_ToDo o) -> o.name).thenComparingInt(o -> o.jg
        );

    }

}
