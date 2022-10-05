import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

    public static void preOrder( Node root ) {

        if( root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

 /* Node is defined as :
 class Node 
    int data;
    Node left;
    Node right;
    
    */

    public static Node insert(Node root,int data) throws IOException{
        if (root == null) {
            root = new Node(data);
        }
        else if (data < root.data) {
            if (root.left == null) {
                root.left = new Node(data);
            } else root.left = insert(root.left, data);
        } else {
            if (root.right == null) {
                root.right = new Node(data);
            } else
                root.right = insert(root.right, data);
        }
        return root;
    }

    public static void main(String[] args) {
        try{
            int a = 5/0;
      System.out.println('a');
        }  catch (ArithmeticException e) {

        }
    }
}