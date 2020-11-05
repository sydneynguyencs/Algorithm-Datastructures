package Praktikum_07_Code;


import java.util.Scanner;

public class RouteServer implements CommandExecutor {
    private Graph<DijkstraNode,Edge> graph;
    private boolean fileRead;

    public RouteServer() {
        graph = new AdjListGraph<DijkstraNode,Edge>(DijkstraNode.class,Edge.class);
    }

    private void addtoGraph(String command) throws Throwable {
        Scanner sc = new Scanner(command);
        while(sc.hasNext()){
            String from = sc.next();
            graph.addNode(from);
            String to = sc.next();
            graph.addNode(to);
            double dist = sc.nextDouble();
            graph.addEdge(from,to,dist);
            graph.addEdge(to,from,dist);
        }
        fileRead = true;
    }

    private void dijkstra(String start, String goal){
        DijkstraNode<Edge> startNode = graph.findNode(start);
        DijkstraNode<Edge<DijkstraNode>> goalNode = graph.findNode(goal);
        java.util.Queue<DijkstraNode> pq = new java.util.PriorityQueue<DijkstraNode>();

        for(DijkstraNode d : graph.getNodes()) {
            d.setDist(Integer.MAX_VALUE);
            d.setMark(false);
            d.setPrev(null);
        }
        startNode.setDist(0);
        startNode.setMark(true);
        pq.add(startNode); //set

        while(!pq.isEmpty()) {
            DijkstraNode<Edge<DijkstraNode>> curr = pq.remove(); //find node with smallest dist
            if(curr == goalNode) return;
            curr.setMark(true); //green nodes are marked
            //check all successors of curr
            for(Edge<DijkstraNode> edge : curr.getEdges()) {
                DijkstraNode<Edge<DijkstraNode>> neighbour = edge.getDest();
                if(!neighbour.getMark()) {
                    if(!pq.contains(neighbour)) {
                        pq.add(neighbour);
                    }
                    double dist = curr.getDist() + edge.getWeight();
                    if(dist < neighbour.getDist()) {
                        neighbour.setDist(dist);
                        neighbour.setPrev(curr);
                    }
                }
            }
        }

    }


    @Override
    public String execute(String command) throws Throwable {

        /*
        if(!fileRead) {
            addtoGraph(command);
            return "Type in start and goal city: \n";
        } else {
            Scanner sc = new Scanner(command);
            String start = sc.next();
            String goal = sc.next();
            dijkstra(start, goal);
            return backTrack(goal);
        }
         */
        addtoGraph(command);
        String start = "Lugano";
        String goal = "Winterthur";
        dijkstra(start,goal);
        return backTrack(goal);
    }

    private String backTrack(String goal) {
        StringBuilder s = new StringBuilder();
        DijkstraNode node = graph.findNode(goal);
        while(node!=null) {
            s.append(node.getName()).append(" ").append(node.getDist()).append("\n");
            node = node.getPrev();
        }
        return s.toString();
    }
}
