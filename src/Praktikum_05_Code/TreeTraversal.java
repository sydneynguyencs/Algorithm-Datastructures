package Praktikum_05_Code;

import java.util.*;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;
    private TreeNode<T> node;
    private List<TreeNode<T>> queue;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
        queue = new ArrayList<>();
    }

    public void inorder(Visitor<T> vis) {
        // to be done
        node = root;
        inorder(node, vis);
    }

    private void inorder(TreeNode<T> node, Visitor<T> vis) {
        if(node != null) {
            inorder(node.left, vis);
            vis.visit(node.element);
            inorder(node.right, vis);
        }
    }

    public void preorder(Visitor<T> vis) {
        // to be done
        node = root;
        preorder(node, vis);
    }

    private void preorder(TreeNode<T> node, Visitor<T> vis) {
        if(node!=null) {
            vis.visit(node.element);
            preorder(node.left, vis);
            preorder(node.right, vis);
        }
    }

    public void postorder(Visitor<T> vis) {
        // to be done
        node = root;
        postorder(node, vis);
    }

    private void postorder(TreeNode<T> node, Visitor<T> vis) {
        if(node!=null) {
            postorder(node.left, vis);
            postorder(node.right, vis);
            vis.visit(node.element);
        }
    }

    //Verwenden Sie für die levelOrder Methode die Queue Implementation des JDKs
    @Override
    public void levelorder(Visitor<T> vis) {
        node = root;
        if(node!=null) {
            queue.add(node);
            while(!queue.isEmpty()) {
                //dequeue
                node = queue.remove(0);
                vis.visit(node.element);
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
        }
    }

    @Override
    public void interval(Comparable<T> min, Comparable<T> max, Visitor<T> v) {
        interval(min, max, v, root);
    }

    private void interval(Comparable<T> min, Comparable<T> max, Visitor<T> v, TreeNode<T> node) {
        if(node==null) {
            return;
        }
        boolean withinMin = min.compareTo(node.element)<=0;
        boolean withinMax = max.compareTo(node.element)>=0;
        if(withinMin) {
            interval(min, max, v, node.left);
        }
        if(withinMax) {
            interval(min, max, v, node.right);
        }
        if(withinMin && withinMax) {
            v.visit(node.element);
            System.out.println(node.element.toString());
        }
    }

}
