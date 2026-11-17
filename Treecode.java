/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.treecode;

import java.util.Stack;

class Node {
    String value;
    Node left, right;
    public Node(String item) { 
        value = item; 
        left = right = null; 
    }
}

public class Treecode {
    
    // 1. buildTree (النسخة الأبسط)
    public static Node buildTree(String postfix) {
        Stack<Node> s = new Stack<>();
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                s.push(new Node(c + ""));
            } else {
                Node n = new Node(c + "");
                n.right = s.pop();
                n.left = s.pop();
                s.push(n);
            }
                    
        }
        return s.pop();
    }
    
    // 2. evaluate (النسخة الأبسط)
    public static int evaluate(Node root) {
        if (root.left == null && root.right == null) 
            return Integer.parseInt(root.value);
        
        int L = evaluate(root.left);
        int R = evaluate(root.right);
        
        return root.value.equals("+") ? L + R :
               root.value.equals("-") ? L - R :
               root.value.equals("*") ? L * R : L / R;
    }
    
    // 3. printInfix (النسخة الأبسط)
    public static void printInfix(Node root) {
        if (root == null) return;
        if (root.left != null) System.out.print("(");
        printInfix(root.left);
        System.out.print(root.value);
        printInfix(root.right);
        if (root.right != null) System.out.print(")");
    }
    
    public static void main(String[] args) {
        String postfix = "53+82-*";
        Node root = buildTree(postfix);
        printInfix(root);
        System.out.println("\nResult: " + evaluate(root));
    }
}
