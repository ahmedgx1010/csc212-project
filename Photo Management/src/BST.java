public class BST<T> {// special BST for String keys
    BSTNode<String, T> root, current;

    public BST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        return current.data;
    }

    public boolean update(String key, T data) {
        remove_key(current.key);
        return insert(key, data);
    }

    public boolean findkey(String tkey) {
        BSTNode<String, T> p = root, q = root;

        if (empty())
            return false;
        while (p != null) {
            q = p;
            if (p.key.equals(tkey)) {
                current = p;
                return true;
            } else if (tkey.compareToIgnoreCase(p.key) < 0)
                p = p.left;
            else
                p = p.right;
        }
        current = q;
        return false;
    }

    public boolean insert(String k, T val) {
        BSTNode<String, T> p;
        if (findkey(k)) {
            /* optimization for our usecase */
            //current = q; // findkey() modified current

            return false; // key already in the BST
        }

        p = new BSTNode<String, T>(k, val);
        if (empty()) {
            root = current = p;
            return true;
        } else {
            // current is pointing to parent of the new key
            if (k.compareToIgnoreCase(current.key) < 0)
                current.left = p;
            else
                current.right = p;
            current = p;
            return true;
        }
    }

    public boolean remove_key(String tkey) {
        BooleanWrapper removed = new BooleanWrapper(false);
        BSTNode<String, T> p;
        p = remove_aux(tkey, root, removed);
        current = root = p;
        return removed.get();
    }

    private BSTNode<String, T> find_min(BSTNode<String, T> p) {
        if (p == null)
            return null;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    private BSTNode<String, T> remove_aux(String key, BSTNode<String, T> p, BooleanWrapper flag) {
        BSTNode<String, T> q, child = null;
        if (p == null)
            return null;
        if (key.compareToIgnoreCase(p.key) < 0)
            p.left = remove_aux(key, p.left, flag); // go left
        else if (key.compareToIgnoreCase(p.key) > 0)
            p.right = remove_aux(key, p.right, flag); // go right
        else { // key is found
            flag.set(true);
            if (p.left != null && p.right != null) { // two children
                q = find_min(p.right);
                p.key = q.key;
                p.data = q.data;
                p.right = remove_aux(q.key, p.right, flag);
            } else {
                if (p.right == null) // one child
                    child = p.left;
                else if (p.left == null) // one child
                    child = p.right;
                return child;
            }
        }
        return p;
    }
}

class BSTNode<K, T> {
    public K key;
    public T data;
    public BSTNode<String, T> left, right;

    public BSTNode(K k, T val) {
        key = k;
        data = val;
        left = right = null;
    }

    public BSTNode(K k, T val, BSTNode<String, T> l, BSTNode<String, T> r) {
        key = k;
        data = val;
        left = l;
        right = r;
    }
}

class BooleanWrapper {
    boolean b;

    public BooleanWrapper(boolean b) {
        set(b);
    }

    public void set(boolean b) {
        this.b = b;
    }

    public boolean get() {
        return b;
    }
}