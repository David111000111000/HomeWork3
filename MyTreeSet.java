import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

// Класс реализующий интерфейс Set с помощью бинарного дерева поиска
public class MyTreeSet<E extends Comparable<E>> implements Set<E> {
    public static void main(String[] args) {

        MyTreeSet<String> states = new MyTreeSet<String>();
        states.add("Germany");
        states.add("Ящеры");
        states.add("Italy");
        states.add("Spain");
        states.add("Древние русы");
        states.print();
        System.out.println("__________");
        states.remove("Ящеры");
        states.print();
        System.out.println("__________");
        System.out.println(states.contains("Древние русы"));
    }
    // Внутренний класс представляющий узел дерева
    private static class Node<E> {
        E data; // данные, хранящиеся в узле
        Node<E> left; // ссылка на левого потомка
        Node<E> right; // ссылка на правого потомка

        // Конструктор узла с данными
        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    // Метод для вывода дерева в консоль
    public void print() {
        print(root, 0); // вызываем вспомогательный метод для поддерева с корнем root и начальным отступом 0
    }

    // Вспомогательный метод для вывода поддерева с заданным корнем и отступом в консоль
    private void print(Node<E> node, int indent) {
        if (node == null) {
            return; // поддерево пустое, ничего не выводим
        }
        print(node.right, indent + 4); // выводим правое поддерево с увеличенным отступом
        for (int i = 0; i < indent; i++) {
            System.out.print(" "); // выводим пробелы в соответствии с отступом С ОТСТУПОМ БЛИН ПОСТАВЬТЕ ПЯТЬ
        }
        System.out.println(node.data); // выводим данные узла
        print(node.left, indent + 4); // выводим левое поддерево с увеличенным отступом
    }


    private Node<E> root; // корень дерева
    private int size; // размер дерева

    // Конструктор пустого дерева
    public MyTreeSet() {
        this.root = null;
        this.size = 0;
    }

    // Конструктор дерева с коллекцией элементов
    public MyTreeSet(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    // Метод для добавления элемента в дерево
    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Null elements are not allowed");
        }
        if (contains(e)) {
            return false; // элемент уже есть в дереве
        }
        root = add(root, e); // добавляем элемент в поддерево с корнем root
        size++; // увеличиваем размер дерева
        return true;
    }

    // Вспомогательный метод для добавления элемента в поддерево с заданным корнем
    private Node<E> add(Node<E> node, E e) {
        if (node == null) {
            return new Node<>(e); // создаем новый узел с данными e
        }
        int cmp = e.compareTo(node.data); // сравниваем элемент с данными узла
        if (cmp < 0) {
            node.left = add(node.left, e); // добавляем элемент в левое поддерево
        } else if (cmp > 0) {
            node.right = add(node.right, e); // добавляем элемент в правое поддерево
        }
        return node; // возвращаем корень поддерева
    }

    // Метод для проверки, содержит ли дерево заданный элемент
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("Null elements are not allowed");
        }
        try {
            @SuppressWarnings("unchecked")
            E e = (E) o; // приводим объект к типу E
            return contains(root, e); // проверяем, содержит ли поддерево с корнем root элемент e
        } catch (ClassCastException ex) {
            return false; // объект не может быть приведен к типу E
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    // Вспомогательный метод для проверки, содержит ли поддерево с заданным корнем заданный элемент
    private boolean contains(Node<E> node, E e) {
        if (node == null) {
            return false; // поддерево пустое, значит не содержит элемента
        }
        int cmp = e.compareTo(node.data); // сравниваем элемент с данными узла
        if (cmp < 0) {
            return contains(node.left, e); // ищем элемент в левом поддереве
        } else if (cmp > 0) {
            return contains(node.right, e); // ищем элемент в правом поддереве
        } else {
            return true; // элемент равен данным узла, значит найден
        }
    }

    // Метод для удаления элемента из дерева
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Null elements are not allowed");
        }
        if (!contains(o)) {
            return false; // элемента нет в дереве
        }
        try {
            @SuppressWarnings("unchecked")
            E e = (E) o; // приводим объект к типу E
            root = remove(root, e); // удаляем элемент из поддерева с корнем root
            size--; // уменьшаем размер дерева
            return true;
        } catch (ClassCastException ex) {
            return false; // объект не может быть приведен к типу E
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    // Вспомогательный метод для удаления элемента из поддерева с заданным корнем
    private Node<E> remove(Node<E> node, E e) {
        if (node == null) {
            return null; // поддерево пустое, ничего не удаляем
        }
        int cmp = e.compareTo(node.data); // сравниваем элемент с данными узла
        if (cmp < 0) {
            node.left = remove(node.left, e); // удаляем элемент из левого поддерева
        } else if (cmp > 0) {
            node.right = remove(node.right, e); // удаляем элемент из правого поддерева
        } else { // элемент равен данным узла, значит нашли узел для удаления
            if (node.left == null && node.right == null) {
                return null; // узел без потомков, просто удаляем его
            } else if (node.left == null) {
                return node.right; // узел имеет только правого потомка, заменяем его на него
            } else if (node.right == null) {
                return node.left; // узел имеет только левого потомка, заменяем его на него
            } else { // узел имеет обоих потомков, ищем минимальный элемент в правом поддереве
                Node<E> min = findMin(node.right); // находим минимальный элемент в правом поддереве
                node.data = min.data; // копируем данные минимального элемента в узел для удаления
                node.right = remove(node.right, min.data); // удаляем минимальный элемент из правого поддерева
            }
        }
        return node; // возвращаем корень поддерева
    }

    // Вспомогательный метод для поиска минимального элемента в поддереве с заданным корнем
    private Node<E> findMin(Node<E> node) {
        if (node == null) {
            return null; // поддерево пустое, минимального элемента нет
        }
        if (node.left == null) {
            return node; // узел не имеет левого потомка, значит он минимальный в поддереве
        }
        return findMin(node.left); // ищем минимальный элемент в левом поддереве
    }

    // Метод для получения размера дерева
    @Override
    public int size() {
        return size;
    }

    // Метод для проверки, пусто ли дерево
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Метод для очистки дерева от всех элементов
    @Override
    public void clear() {
        root = null; // обнуляем ссылку на корень дерева
        size = 0; // обнуляем размер дерева
    }

}