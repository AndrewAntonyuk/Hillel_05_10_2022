import java.util.NoSuchElementException;

public class Tree<E extends Comparable<E>> {
    private Node<E> rootNode;

    public void insert(E e) {
        goBackWithRebalancing(add(e));
    }

    public E remove(E e) {
        Node<E> nodeForDelete = getNode(e);
        Node<E> nodeForReconnect;

        if (nodeForDelete == null) {
            throw new NoSuchElementException("Element " + e.toString() + " not found");
        } else {
            E data = nodeForDelete.getData();

            if (nodeForDelete.getRightNode() == null && nodeForDelete.getLeftNode() == null) {
                nodeForReconnect = killNode(nodeForDelete);
            } else if (nodeForDelete.getRightNode() == null) {
                nodeForReconnect = setChildAsParent(nodeForDelete, nodeForDelete.getLeftNode());
            } else if (nodeForDelete.getLeftNode() == null) {
                nodeForReconnect = setChildAsParent(nodeForDelete, nodeForDelete.getRightNode());
            } else {
                Node<E> tempNode = getInorderSuccessor(nodeForDelete.getRightNode());
                nodeForReconnect = replace(nodeForDelete, tempNode);
            }

            goBackWithRebalancing(nodeForReconnect);

            return data;
        }
    }

    private Node<E> add(E e) {
        Node<E> currentNode = rootNode;
        Node<E> newNode = new Node<>(e);

        if (currentNode == null) {
            rootNode = newNode;
            return newNode;
        }

        while (true) {
            if (currentNode.getData().compareTo(e) > 0) {
                if (currentNode.getLeftNode() != null) {
                    currentNode = currentNode.getLeftNode();
                } else {
                    currentNode.setLeftNode(newNode);
                    newNode.setParentsNode(currentNode);
                    return newNode;
                }
            } else if (currentNode.getData().compareTo(e) < 0) {
                if (currentNode.getRightNode() != null) {
                    currentNode = currentNode.getRightNode();
                } else {
                    currentNode.setRightNode(newNode);
                    newNode.setParentsNode(currentNode);
                    return newNode;
                }
            } else {
                throw new IllegalArgumentException("The element " + e + " has already existed");
            }
        }
    }

    private void goBackWithRebalancing(final Node<E> node) {
        Node<E> currentNode = node;

        while (currentNode != null) {
            updateHeight(currentNode);

            if (Math.abs(getBalanceFactor(currentNode)) > 1) {
                rebalancing(currentNode);
            }

            currentNode = currentNode.getParentsNode();
        }
    }

    private void rebalancing(Node<E> node) {
        int currentBalanceFactor = getBalanceFactor(node);
        int leftBalanceFactor = getBalanceFactor(node.getLeftNode());
        int rightBalanceFactor = getBalanceFactor(node.getRightNode());

        if (currentBalanceFactor < -1 && rightBalanceFactor < 0) {
            leftRotation(node);
            return;
        }

        if (currentBalanceFactor > 1 && leftBalanceFactor > 0) {
            rightRotation(node);
            return;
        }

        if (currentBalanceFactor < -1 && rightBalanceFactor > 0) {
            rightLeftRotation(node);
            return;
        }

        if (currentBalanceFactor > 1 && leftBalanceFactor < 0) {
            leftRightRotation(node);
        }
    }

    private void leftRotation(Node<E> node) {
        Node<E> rightNode = node.getRightNode();

        node.setRightNode(null);

        if (rightNode.getLeftNode() == null) {
            rightNode.setLeftNode(node);
        } else {
            Node<E> tempNode = rightNode.getLeftNode();
            rightNode.setLeftNode(node);
            node.setRightNode(tempNode);
            tempNode.setParentsNode(node);
        }

        reconnectParentNode(node, rightNode);

        updateHeight(node);
        updateHeight(rightNode);
    }

    private void rightRotation(Node<E> node) {
        Node<E> leftNode = node.getLeftNode();

        node.setLeftNode(null);

        if (leftNode.getRightNode() == null) {
            leftNode.setRightNode(node);
        } else {
            Node<E> tempNode = leftNode.getRightNode();
            leftNode.setRightNode(node);
            node.setLeftNode(tempNode);
            tempNode.setParentsNode(node);
        }

        reconnectParentNode(node, leftNode);

        updateHeight(node);
        updateHeight(leftNode);
    }

    private void rightLeftRotation(Node<E> node) {
        Node<E> rightNode = node.getRightNode();

        rightRotation(rightNode);
        leftRotation(node);
    }

    private void leftRightRotation(Node<E> node) {
        Node<E> leftNode = node.getLeftNode();

        leftRotation(leftNode);
        rightRotation(node);
    }

    private void reconnectParentNode(Node<E> source, Node<E> destination) {
        Node<E> parentNode = source.getParentsNode();
        source.setParentsNode(null);

        if (parentNode == null) {
            destination.setParentsNode(null);
            rootNode = destination;
        } else {
            destination.setParentsNode(parentNode);
            if (source.equals(parentNode.getLeftNode())) {
                parentNode.setLeftNode(destination);
            } else {
                parentNode.setRightNode(destination);
            }
        }

        source.setParentsNode(destination);
    }

    private void updateHeight(Node<E> node) {
        int newHeight = Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())) + 1;

        node.setHeight(newHeight);
    }

    private int getHeight(Node<E> node) {
        return (node == null ? 0 : node.getHeight());
    }

    private int getBalanceFactor(Node<E> node) {
        return (node == null ? 0 : getHeight(node.getLeftNode()) - getHeight(node.getRightNode()));
    }

    private Node<E> getNode(E e) {
        Node<E> currentNode = rootNode;

        if (currentNode == null) {
            return null;
        }

        while (true) {
            if (currentNode.getData().compareTo(e) == 0) {
                return currentNode;
            } else if (currentNode.getData().compareTo(e) > 0) {
                if (currentNode.getLeftNode() != null) {
                    currentNode = currentNode.getLeftNode();
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRightNode() != null) {
                    currentNode = currentNode.getRightNode();
                } else {
                    return null;
                }
            }
        }
    }

    private Node<E> killNode(Node<E> node) {
        Node<E> parentNode = node.getParentsNode();

        if (parentNode != null) {
            if (parentNode.getRightNode().equals(node)) {
                parentNode.setRightNode(null);
            } else {
                parentNode.setLeftNode(null);
            }
        } else {
            rootNode = null;
        }

        node = null;

        return parentNode;
    }

    private Node<E> replace(Node<E> oldNode, Node<E> newNode) {
        Node<E> parent = oldNode.getParentsNode();
        Node<E> left = oldNode.getLeftNode();
        Node<E> right = oldNode.getRightNode();

        newNode.setRightNode(right);
        newNode.setLeftNode(left);

        if (parent == null) {
            rootNode = newNode;
        } else {
            if (oldNode.equals(parent.getLeftNode())) {
                parent.setLeftNode(newNode);
            } else {
                parent.setRightNode(newNode);
            }
        }

        if (left != null) {
            left.setParentsNode(newNode);
        }

        if (right != null) {
            right.setParentsNode(newNode);
        }

        oldNode = null;

        return newNode;
    }

    private Node<E> setChildAsParent(Node<E> parent, Node<E> child) {
        Node<E> grandParent = parent.getParentsNode();

        if (grandParent == null) {
            rootNode = child;
        } else {
            if (parent.equals(grandParent.getLeftNode())) {
                grandParent.setLeftNode(child);
            } else {
                grandParent.setRightNode(child);
            }
        }
        child.setParentsNode(grandParent);
        parent = null;

        return child;
    }

    private Node<E> getInorderSuccessor(Node<E> node) {
        Node<E> currentNode = node;

        while (currentNode.getLeftNode() != null) {
            currentNode = currentNode.getLeftNode();
        }

        return disconnect(currentNode);
    }

    private Node<E> disconnect(Node<E> node) {
        Node<E> parentNode = node.getParentsNode();

        if (parentNode != null) {
            if (parentNode.getRightNode().equals(node)) {
                parentNode.setRightNode(null);
            } else {
                parentNode.setLeftNode(null);
            }
        }

        node.setParentsNode(null);

        return node;
    }

    @Override
    public String toString() {
        return rootNode.toString();
    }

    private static class Node<E> {
        private E data;
        private Node<E> rightNode;
        private Node<E> leftNode;
        private Node<E> parentsNode;
        private int height = 0;

        public Node(E data) {
            this.data = data;
        }

        //region Getters/Setters
        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node<E> rightNode) {
            this.rightNode = rightNode;
        }

        public Node<E> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node<E> leftNode) {
            this.leftNode = leftNode;
        }

        public Node<E> getParentsNode() {
            return parentsNode;
        }

        public void setParentsNode(Node<E> parentsNode) {
            this.parentsNode = parentsNode;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
        //endregion

        @Override
        public String toString() {
            return "Node{" +
                    "(" + height + ")" +
                    "value=" + data +
                    ", left=" + (leftNode == null ? "No" : leftNode) +
                    ", right=" + (rightNode == null ? "No" : rightNode) +
                    "}";
        }
    }
}
