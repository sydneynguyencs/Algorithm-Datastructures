package Praktikum_04_Code;

public class SnowflakeServer implements CommandExecutor{
    private String s;
    public SnowflakeServer() {
        s="";
    }

    public void koch(Turtle turtle, int n, double distance) {
        makeSnowflake(turtle, n - 1, distance);
        turtle.turn(120);
        makeSnowflake(turtle, n - 1, distance);
        turtle.turn(120);
        makeSnowflake(turtle, n - 1, distance);
    }

    public void makeSnowflake(Turtle turtle, int n, double distance) {
        if(n == 0) {
            turtle.move(distance);
        } else {
            distance /= 3.0;
            makeSnowflake(turtle, n - 1, distance);
            turtle.turn(-60);
            makeSnowflake(turtle, n - 1, distance);
            turtle.turn(120);
            makeSnowflake(turtle, n - 1, distance);
            turtle.turn(-60);
            makeSnowflake(turtle, n - 1, distance);
        }
    }

    @Override
    public String execute(String command) throws Exception {
        Turtle turtle = new Turtle();
        turtle.reset(0.1,0.1);
        int n = Integer.parseInt(command);
        koch(turtle, n, 10.0/Math.pow(3.0,n));
        return turtle.getTrace();

    }
}
