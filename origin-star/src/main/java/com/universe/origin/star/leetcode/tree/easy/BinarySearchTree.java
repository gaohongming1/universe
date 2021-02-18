package com.universe.origin.star.leetcode.tree.easy;

/**
 * 实现二叉搜索树
 */
public class BinarySearchTree {


    static class Node<T extends Comparable<T>> {
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
            Node<Integer> root = new Node<Integer>(33);
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
            root.delete(root, 15, root);
            root.delete(root, 17, root);
            root.delete(root, 18, root);
            System.out.println("添加完成");
        }

        /**
         * 添加操作
         *
         * @param node
         * @param date
         */
        public void add(Node<T> node, T date) {
            // 放在右子树
            if (node.getDate().compareTo(date) > 0) {
                if (node.left == null) {
                    Node<T> newNode = new Node<>(date);
                    node.left = newNode;
                } else {
                    add(node.left, date);
                }
            } else {
                //放在左子树
                if (node.right == null) {
                    Node<T> newNode = new Node<>(date);
                    node.right = newNode;
                } else {
                    add(node.right, date);
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
        public void delete(Node<T> node, T date, Node<T> parent) {
            if (node.getDate().compareTo(date) != 0) {
                if (node.getDate().compareTo(date) > 0) {
                    delete(node.left, date, node);
                    return;
                } else {
                    delete(node.right, date, node);
                    return;
                }
            }
            // 情况一 要删除节点是叶子节点
            if (node.left == null && node.right == null) {
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                return;
            }

            //要删除节点有左右节点
            if (node.left != null && node.right != null) {
                Node<T> rightParent = node;
                Node<T> right = node.right;
                while (right.left != null) {
                    rightParent = right;
                    right = right.left;
                }
                // 交换
                node.setDate(right.getDate());
                if (right.right != null) {
                    node.right = right.right;
                } else {
                    rightParent.left = null;
                }
                return;
            }

            //要删除节点有左孩子或者右边孩子
            if (node.left != null) {
                parent.left = node.left;
                return;
            }
            if (node.right != null) {
                parent.right = node.right;
                return;
            }
        }
    }

}
