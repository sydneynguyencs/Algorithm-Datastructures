package Praktikum_08_Code;

import java.awt.*;

public class GraphicsServer implements CommandExecutor{

    @Override
    public String execute(String command) throws Exception {
        ServerGraphics serverGraphics = new ServerGraphics();
        double rectX1 = 0.1;
        double rectY1 = 0.1;

        double triX1 = rectX1+0.1;
        double triY1 = rectY1+0.1;
        double triX2 = rectX1 + 0.6 - 0.1;
        double height = (triX2 - triX1)/2 / Math.cos(1.0472); //60 deg in rad

        serverGraphics.setColor(Color.RED);
        serverGraphics.drawRect(rectX1,rectY1,0.6,height + 0.16);

        serverGraphics.setColor(Color.black);
        serverGraphics.drawLine(triX1,triY1,triX2,triY1);
        serverGraphics.drawLine(triX1,triY1,triX1+(triX2 - triX1)/2, triY1+height);
        serverGraphics.drawLine(triX2,triY1,triX1+(triX2 - triX1)/2, triY1+height);
        return serverGraphics.getTrace();
    }
}
