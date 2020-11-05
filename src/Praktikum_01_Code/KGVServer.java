package Praktikum_01_Code;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class KGVServer implements CommandExecutor{

    public int kgv(int m, int n) {

        return m*n/ggt(m,n);
    }

    public int ggt(int m, int n) {
        int ggt = 0;
        for(int i = 1; i <= m && i <= n; i++) {
            if(m%i == 0 && n%i == 0) {
                ggt = i;
            }
        }
        return ggt;
    }

    @Override
    public String execute(String s) {
        Scanner scanner = new Scanner(new ByteArrayInputStream(s.getBytes()));
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        return Integer.toString(kgv(a,b));
    }
}
