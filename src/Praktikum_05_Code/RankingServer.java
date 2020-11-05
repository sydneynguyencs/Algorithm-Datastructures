package Praktikum_05_Code;

import java.text.ParseException;
import java.util.Scanner;

public class RankingServer implements CommandExecutor{
    private Tree<Competitor_ToDo> avlTree;
    private Tree<Competitor_ToDo> tree;

    public RankingServer() {
        avlTree = new AVLSearchTree<>();
        tree = new SortedBinaryTree<>();
    }

    public Tree<Competitor_ToDo> getTree(String args, Tree tree) throws ParseException {
        Scanner sc = new Scanner(args).useDelimiter("\n");
        while(sc.hasNext()) {
            Scanner sc1 = new Scanner(sc.next()).useDelimiter(";");
            int startNr = sc1.nextInt();
            String name = sc1.next();
            int jg = sc1.nextInt();
            String country = sc1.next();
            String time = sc1.next();
            Competitor_ToDo competitor = new Competitor_ToDo(startNr, name, jg, country, time);
            tree.add(competitor);
        }
        //set rankings
        RankTraversal rankTraversal = new RankTraversal();
        tree.traversal().inorder(rankTraversal);
        return tree;
    }

    @Override
    public String execute(String command) throws Exception {
        Tree<Competitor_ToDo> treeAVL = getTree(command, avlTree);
        Tree<Competitor_ToDo> treeSort = getTree(command, tree);
        //return treeAVL.printTree();
        //return treeSort.printTree();
        PrintTraversal printTraversal = new PrintTraversal();
        treeAVL.traversal().inorder(printTraversal);
        return printTraversal.s.toString()+"\nheight AVL: "+treeAVL.height()+"\nheight sorted binaray tree: "+treeSort.height();
    }

    public static class RankTraversal implements Visitor<Competitor_ToDo> {
        private int rank = 1;
        @Override
        public void visit(Competitor_ToDo c) {
            c.setRank(rank);
            rank++;
        }
    }

    public static class PrintTraversal implements Visitor<Competitor_ToDo> {
        private StringBuilder s = new StringBuilder();
        @Override
        public void visit(Competitor_ToDo c) {
            s.append(c.toString());
            s.append("\n");
        }
    }

}