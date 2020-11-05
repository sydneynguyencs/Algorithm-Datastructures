package Praktikum_01_Code;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class XMLServerTest {

    XMLServer bs;

    @Before
    public void setUp() throws Exception {
        bs = new XMLServer();
    }
    private void test(String s, boolean b) {
        assertEquals(s,bs.checkWellformed(s),b);
    }

    @Test
    public void testWellformed() {
        test("<b seq=1> </b>",true);
        test("<b seq=1> </b seq=1>",false);
        test("<b> </c>",false);
        test("<b> <c> </c> </b>",true);
        test("<b> <c> </b> </c>",false);
        test("<a> <b> </b> <c> </c> </a>",true);
        test("<b >nogap</b>",true);
        test("<b>nogap</c>",false);
        test("<b type>\n" + "</b>\n" + "<root url/>\n",true);
        test("<b type>\n" + "</b>\n" + "<root url>\n",false);
        test("", true);

    }


    

}
