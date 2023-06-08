//В классе Tree добавить возможность использовать любые сравниваемые типы данных
//        То есть нужно параметризовать класс T дженериком <T extends Comparable<T>>


import java.util.*;

public class Tree<T extends Comparable<T>> {

    private class Node {
        T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node root;

    /**
     * Добавляет значение в дерево.
     * @param value значение для добавления.
     */
    public void add(T value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        add(root, value);
    }

    /**
     * Вспомогательный метод для добавления значения в дерево.
     * @param current текущий узел.
     * @param value значение для добавления.
     */
    private void add(Node current, T value) {
        if (value.compareTo(current.value) < 0) {
            if (current.left == null) {
                current.left = new Node(value);
            } else {
                add(current.left, value);
            }
        } else if (value.compareTo(current.value) > 0) {
            if (current.right == null) {
                current.right = new Node(value);
            } else {
                add(current.right, value);
            }
        }
    }

    /**
     * Проверяет, содержится ли значение в дереве.
     * @param value значение для поиска.
     * @return true, если значение найдено, иначе false.
     */
    public boolean contains(T value) {
        return findNode(root, value) != null;
    }

    /**
     * Вспомогательный метод для поиска узла с заданным значением в дереве.
     * @param current текущий узел.
     * @param value значение для поиска.
     * @return узел с заданным значением или null, если узел не найден.
     */
    private Node findNode(Node current, T value) {
        if (current == null) {
            return null;
        }

        if (current.value.compareTo(value) == 0) {
            return current;
        } else if (current.value.compareTo(value) > 0) {
            return findNode(current.left, value);
        } else { // current.value.compareTo(value) < 0
            return findNode(current.right, value);
        }
    }

    /**
     * Удаляет значение из дерева.
     * @param value значение для удаления.
     */
    public void remove(T value) {
        root = removeNode(root, value);
    }

    /**
     * Вспомогательный метод для удаления значения из дерева.
     * @param current текущий узел.
     * @param value значение для удаления.
     * @return новый корень дерева после удаления значения.
     */
    private Node removeNode(Node current, T value) {
        if (current == null) {
            return null;
        }

        if (value.compareTo(current.value) < 0) {
            current.left = removeNode(current.left, value);
            return current;
        } else if (value.compareTo(current.value) > 0) {
            current.right = removeNode(current.right, value);
            return current;
        }

        if (current.left == null && current.right == null) {
            return null;
        }

        if (current.left != null && current.right == null) {
            return current.left;
        } else if (current.left == null && current.right != null) {
            return current.right;
        }

        Node smallestNodeOnTheRight = findFirst(current.right);
        T smallestValueOnTheRight = smallestNodeOnTheRight.value;
        current.value = smallestValueOnTheRight;
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    /**
     * Находит минимальное значение в дереве.
     * @return минимальное значение в дереве.
     * @throws NoSuchElementException если дерево пустое.
     */
    public T findFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return findFirst(root).value;
    }

    /**
     * Вспомогательный метод для нахождения узла с минимальным значением в дереве.
     * @param current текущий узел.
     * @return узел с минимальным значением.
     */
    private Node findFirst(Node current) {
        if (current.left != null) {
            return findFirst(current.left);
        }
        return current;
    }

    /**
     * Выполняет обход дерева в глубину (DFS).
     * @return список значений, полученных при обходе дерева в глубину.
     */
    public List<T> dfs() {
        if (root == null) {
            return List.of();
        }

        List<T> result = new ArrayList<>();
        dfs(root, result);
        return List.copyOf(result);
    }

    /**
     * Вспомогательный метод для обхода дерева в глубину (DFS).
     * @param current текущий узел.
     * @param result список для сохранения значений.
     */
    private void dfs(Node current, List<T> result) {
        if (current.left != null) {
            dfs(current.left, result);
        }
        result.add(current.value);
        if (current.right != null) {
            dfs(current.right, result);
        }
    }

    /**
     * Выполняет обход дерева в ширину (BFS).
     * @return список значений, полученных при обходе дерева в ширину.
     */
    public List<T> bfs() {
        if (root == null) {
            return List.of();
        }

        List<T> result = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node next = queue.poll();
            result.add(next.value);
            if (next.left != null) {
                queue.add(next.left);
            }
            if (next.right != null) {
                queue.add(next.right);
            }
        }
        return result;
    }
}
