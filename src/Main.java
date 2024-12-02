//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(13);
        tree.insert(17);
        tree.printTree(tree.root, -5);

        System.out.println("--------------------------\nMin: " + tree.findMin(tree.root).id +
                "\nMax: " + tree.findMax(tree.root).id);

        System.out.println("--------------------------");

        System.out.println("deleted " + tree.remove(100).id);
        tree.printTree(tree.root, -5);
        System.out.println("--------------------------");


        System.out.println("deleted " + tree.remove(17).id);
        tree.printTree(tree.root, -5);
        System.out.println("--------------------------");

        System.out.println("deleted " + tree.remove(15).id);
        tree.printTree(tree.root, -5);
        System.out.println("--------------------------");

        System.out.println("deleted " + tree.remove(10).id);
        tree.printTree(tree.root, -5);

        System.out.println("deleted " + tree.remove(5).id);
        tree.printTree(tree.root, -5);
        System.out.println("--------------------------");
    }
}

class Node {
    int id ;
    Node left;
    Node right;

    public Node(int item) {
        id = item;
    }

    int getChildren() {
        int num = 0;
        if (left != null) {num++;}
        if (right != null) {num++;}
        return num;
    }
}

class BinaryTree {
    Node root;

    Node find(int key) {
        Node curNode = root;
        while (curNode != null) {
            if (curNode.id == key) {
                return curNode;
            } else if (key < curNode.id) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
        return null;
    }

    Node findMin(Node node) {
        if (node.left == null) {return node;}
        return findMin(node.left);
    }

    Node findMax(Node node) {
        if (node.right == null) {return node;}
        return  findMax(node.right);
    }

    Node remove(int key) {
        boolean isLeft = true;
        Node parent = null;
        Node curNode = root;
        while (curNode != null && curNode.id != key) {
            parent = curNode;
            if (key < curNode.id) {
                isLeft = true;
                curNode = curNode.left;
            } else {
                isLeft = false;
                curNode = curNode.right;
            }
        }
        if (curNode == null) {return new Node(-1);}

        else if (curNode.left == null && curNode.right == null) {
            if (isLeft) {parent.left = null;}
            else {parent.right = null;}
            return curNode;
        }

        else {
            Node replacement;
            if (curNode.getChildren() == 2) {
                replacement = findSuccessor(curNode);
                remove(replacement.id);
                replacement.left = curNode.left;
                replacement.right = curNode.right;
            } else {
                if (curNode.left != null) {replacement = curNode.left;}
                else {replacement = curNode.right;}
            }
            if (parent == null) {root = replacement;}
            else if (isLeft) {parent.left = replacement;}
            else {parent.right = replacement;}
            return curNode;
        }
    }

    Node findSuccessor(Node root) {
        Node curNode = root.right;
        if (curNode == null) {return root;}
        while (curNode.left != null) {curNode = curNode.left;}
        return curNode;
    }

    void insert (int key) {
        if (root == null) {root = new Node(key); return;}
        Node curNode = root;
        while (true) {
            if (key == curNode.id) {return;}
            if (key < curNode.id) {
                if (curNode.left == null) {curNode.left = new Node(key); return;}
                curNode = curNode.left;
            } else {
                if (curNode.right == null) {curNode.right = new Node(key); return;}
                curNode = curNode.right;
            }
        }
    }

    void printTree(Node node, int space) {
        if (node == null) {return;}

        space += 5;
        printTree(node.right, space);

        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println(node.id);

        printTree(node.left, space);
    }
}
