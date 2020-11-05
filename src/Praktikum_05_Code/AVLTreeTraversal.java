package Praktikum_05_Code;

import java.util.*;

public class AVLTreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public AVLTreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    private void inorder(TreeNode<T> node, Visitor<T> vis) {
        if (node != null) {
            inorder(node.left, vis);
            for (int i=0; i < node.count; i++) vis.visit(node.element);
            inorder(node.right, vis);
        }
    }

    public void inorder(Visitor<T> vis) {
        inorder(root, vis);
    }

    private void preorder(TreeNode<T> node, Visitor<T> vis) {
        if (node != null) {
            for (int i=0; i < node.count; i++) vis.visit(node.element);
            preorder(node.left, vis);
            preorder(node.right, vis);
        }
    }

    public void preorder(Visitor<T> vis) {
        preorder(root, vis);
    }

    private void postorder(TreeNode<T> node, Visitor<T> vis) {
        if (node != null) {
            postorder(node.left, vis);
            postorder(node.right, vis);
            for (int i=0; i < node.count; i++) vis.visit(node.element);
        }
    }

    public void postorder(Visitor<T> vis) {
        postorder(root, vis);
    }

    void levelorder(TreeNode<T> node, Visitor<T> visitor) {
        Queue<TreeNode<T>> q = new LinkedList<TreeNode<T>>();
        if (node != null) {
            q.offer(node);
        }
        while (!q.isEmpty()) {
            node = q.poll();
            for (int i=0; i < node.count; i++) visitor.visit(node.element);
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
    }

    public void levelorder(Visitor<T> vis) {
        levelorder(root,vis);
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
