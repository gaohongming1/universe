package com.universe.origin.star.data.tree;

public class Node<T extends Comparable<T>> {
    private T date;
    private int index;
    private Node<T> left;
    private Node<T> right;

    public Node() {
    }

    public Node(T date) {
        this.date = date;
    }

    public Node(T date, int index, Node<T> left, Node<T> right) {
        this.date = date;
        this.index = index;
        this.left = left;
        this.right = right;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "date=" + date +
                ", index=" + index +
                ", left=" + left +
                ", right=" + right +
                '}';
    }


    public static void main(String[] args) {
        Node<Integer> root = new Node<>(33);
        root.add(root, 16);
        root.add(root, 50);
        root.add(root, 13);
        root.add(root, 18);
        root.add(root, 34);
        root.add(root, 58);
        root.add(root, 15);
        root.add(root, 17);
        root.add(root, 25);
        root.add(root, 51);
        root.add(root, 66);
        root.add(root, 19);
        root.add(root, 27);
        root.add(root, 55);
        System.out.println("添加完成");


    }

    /**
     * 添加操作
     *
     * @param node
     * @param date
     */
    public void add(Node<T> node, T date) {
        if (node == null) {
            System.out.println("需要初始化头节点");
            return;
        }
        //根节点值和当前元素比较，如果小于date,则将date放入右边元素
        if (node.date.compareTo(date) < 0) {
            if (node.right != null) {
                add(node.right, date);
            } else {
                node.right = new Node<>(date);
            }
        } else {
            if (node.left != null) {
                add(node.left, date);
            } else {
                node.left = new Node<>(date);
            }
        }
    }

    /**
     * 三种情况
     * 1、要删除的节点有左右子节点 找到右子树的最小值交换，这时候删除转换为删除右子树最小值节点
     * 2、要删除的节点是叶子节点、直接删除将父节点指向null
     * 3、要删除的节点有左孩子或者右孩子  需要更新父节点，指向删除节点的子节点
     *
     * @param node
     * @param date
     */
    public void delete(Node<T> node, T date) {
        //要删除节点的父节点
        Node deleteNodeParent = null;
        Node deleteNode = node;
        while (deleteNode != null && deleteNode.date.compareTo(date) != 0) {
            deleteNodeParent = deleteNode;
            if (deleteNode.date.compareTo(date) > 0) {
                deleteNode = deleteNode.left;
            } else {
                deleteNode = deleteNode.right;
            }
        }

        if (deleteNode == null) {
            return;
        }

        //开始删除节点，判断节点是否有左右孩子
        if (deleteNode.left != null && deleteNode.right != null) {
            //记录右子树的最小节点
            Node leftMinNode = deleteNode.right;
            //记录需要移动节点的父节点
            Node leftMinNodeParent = deleteNode;

            while (leftMinNode.left != null) {
                leftMinNodeParent = leftMinNode;
                leftMinNode = leftMinNode.left;
            }
            //交换数据
            deleteNode.date = leftMinNode.date;
            //交换后要删除的节点就变成了leftMinNode
            deleteNode = leftMinNode;
            deleteNodeParent = leftMinNodeParent;
        }

        //这时候删除的节点是叶子节点或者只有一个子节点,转换为情况2、3
        //找到孩子节点
        Node child = null;
        if (deleteNode.left != null) {
            child = deleteNode.left;
        } else if (deleteNode.right != null) {
            child = deleteNode.right;
        } else {
            child = null;
        }

        //删除根节点
        if (deleteNodeParent == null) {
            node = child;
        } else if (deleteNodeParent.left == deleteNode) {
            //删除的是左节点
            deleteNodeParent.left = child;
        } else {
            deleteNodeParent.right = child;
        }
    }

    /**
     * BST查找操作
     * @param root
     * @param date
     * @return
     */
    public Node find(Node root, T date) {
        Node node = root;
        if (root == null) {
            System.out.println("需要初始化");
        }
        while (node != null) {
            if (node.date.compareTo(date) != 0) {
                if (node.date.compareTo(date) > 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 前序遍历
     * 根左右
     *
     * @param root
     */
    public void preOrderRecursion(Node root) {
        if (root != null) {
            System.out.println(root.date);
            preOrderRecursion(root.left);
            preOrderRecursion(root.right);
        }
    }

    /**
     * 中序遍历
     * 左跟右
     *
     * @param root
     */
    public void inOrderRecursion(Node root) {
        if (root != null) {
            inOrderRecursion(root.left);
            System.out.println(root.date);
            inOrderRecursion(root.right);
        }
    }

    /**
     * 后续遍历
     * 左右跟
     *
     * @param root
     */
    public void afterOrderRecursion(Node root) {
        if (root!=null){
            afterOrderRecursion(root.left);
            afterOrderRecursion(root.right);
            System.out.println(root.date);
        }
    }


}
