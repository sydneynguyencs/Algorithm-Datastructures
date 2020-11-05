package Praktikum_04_Code;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.concurrent.Executor;

public class HanoiServer implements CommandExecutor {
    private String s;

    public HanoiServer() {
        s = "";
    }

    public String moveDisk(int n, char from, char to, char help) {
        if(n>0) {
            moveDisk(n-1, from, help, to);
            s+="move "+from+" to "+to+"\n";
            moveDisk(n-1, help, to, from);
        }
        return s;
    }

    @Override
    public String execute(String command) throws Exception {
        //int n = command.charAt(0)-48;
        int n = Integer.parseInt(command);
        return moveDisk(n, 'A', 'B', 'C');
    }
}
