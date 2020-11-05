package Praktikum_03_Code;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RankingServerTest {

    private RankingServer rs;
    private List<Competitor_ToDo> list;


    @Before
    public void setUp() throws Exception {
        rs = new RankingServer();
    }

    @Test
    public void testGetList() throws ParseException {
        list = new ArrayList<>();
        list = rs.getList("3;Kiptum Daniel;1978;Reconvilier;02:11:31.1;\n");
        assertEquals(list.get(0).toString(), "1 Kiptum Daniel 1978 02:11:31.1");
        //System.out.println(list.get(0).toString());
    }

    @Test
    public void testGetList1() throws ParseException {
        list = new ArrayList<>();
        list = rs.getList( "51;Ançay Tarcis;1970;Lens;02:20:02.9;\n" + "3;Kiptum Daniel;1978;Reconvilier;02:11:31.1;\n" +
                "52;Kreienbühl Christian;1981;Rüti ZH;02:21:47.6");
        assertEquals(list.get(1).toString(), "1 Kiptum Daniel 1978 02:11:31.1");
        assertEquals(list.get(0).toString(), "2 Ançay Tarcis 1970 02:20:02.9");
        assertEquals(list.get(2).toString(), "3 Kreienbühl Christian 1981 02:21:47.6");
    }


}
