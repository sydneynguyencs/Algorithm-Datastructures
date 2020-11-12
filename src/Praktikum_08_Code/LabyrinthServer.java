package Praktikum_08_Code;

import java.awt.*;
import java.util.Scanner;

public class LabyrinthServer implements CommandExecutor {
    private Graph<LabyrinthNode, Edge> graph;
    private boolean mouse;
    private ServerGraphics serverGraphics;

    public LabyrinthServer() {
        graph = new AdjListGraph<LabyrinthNode,Edge>(LabyrinthNode.class, Edge.class);
        serverGraphics = new ServerGraphics();
    }

    private void addToGraph(String command) throws Throwable {
        Scanner sc = new Scanner(command);
        mouse = false;
        serverGraphics.setColor(Color.WHITE);
        while(sc.hasNext()) {
            String from = sc.next();
            String to = sc.next();
            graph.addNode(from);
            graph.addNode(to);
            graph.addEdge(from, to, 1);
            graph.addEdge(to, from, 1);

            drawPath(serverGraphics, from, to, mouse);
        }
    }

    final double SCALE = 10;
    private void drawPath(ServerGraphics g, String from, String to, boolean mouse) {
        double scale = 10;
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0, xh1) / SCALE;
        double y0 = Math.min(yh0, yh1) / SCALE;
        double x1 = Math.max(xh0, xh1) / SCALE;
        double y1 = Math.max(yh0, yh1) / SCALE;
        double w = 1 / SCALE;
        if(mouse) {
            g.drawLine(x0 + w / 2, y0 + w / 2, x1 + w / 2, y1 + w / 2);
        }else {
            if (y0 == y1) {
                g.fillRect(x0, y0, x1 - x0 + w, w);
            }else {
                g.fillRect(x0,y0,w,y1-y0+w);
            }
        }
    }

    @Override
    public String execute(String command) throws Throwable {
        serverGraphics.setColor(Color.BLACK);
        serverGraphics.fillRect(0,0,1,1);
        addToGraph(command);
        String start = "0-6";
        String goal = "3-0";
        LabyrinthNode<Edge<LabyrinthNode>> startMouse = graph.findNode(start);
        LabyrinthNode<Edge<LabyrinthNode>> goalMouse = graph.findNode(goal);

        searchGoal(startMouse, goalMouse);
        backTrack(goalMouse);
        return serverGraphics.getTrace();
    }

    private boolean searchGoal(LabyrinthNode start, LabyrinthNode goal) {
        LabyrinthNode<Edge<LabyrinthNode>> curr = start;

        curr.setMark(true);
        if(curr == goal) {
            return true;
        }else {
            for(Edge<LabyrinthNode> e : curr.getEdges()) {
                if(!e.getDest().getMark()) {
                    if(searchGoal(e.getDest(), goal)) {
                        e.getDest().setPrev(curr);
                        return true;
                    }
                }
            }
        }
        curr.setMark(false);
        return false;
    }

    private void backTrack(LabyrinthNode node) {
        mouse = true;
        while(node.getPrev()!=null) {
            serverGraphics.setColor(Color.RED);
            drawPath(serverGraphics, node.getName(), node.getPrev().getName(), mouse);
            node = node.getPrev();
        }
    }
}
