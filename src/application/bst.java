package application;

import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;

public class bst {
    private class Node {
        Üyelik üye;
        Node left, right;

        Node(Üyelik üye) {
            this.üye = üye;
        }
    }

    private Node root;

    //  Üye ekleme
    public void ekle(Üyelik üye) {
        root = ekleRec(root, üye);
    }

    private Node ekleRec(Node root, Üyelik üye) {
        if (root == null) return new Node(üye);

        int cmp = üye.getAdSoyad().compareToIgnoreCase(root.üye.getAdSoyad());
        if (cmp <= 0) {
            root.left = ekleRec(root.left, üye);
        } else {
            root.right = ekleRec(root.right, üye);
        }
        return root;
    }

    //  Üye silme
    public void sil(String adSoyad) {
        root = silRec(root, adSoyad);
    }

    private Node silRec(Node root, String adSoyad) {
        if (root == null) return null;

        int cmp = adSoyad.compareToIgnoreCase(root.üye.getAdSoyad());
        if (cmp < 0) {
            root.left = silRec(root.left, adSoyad);
        } else if (cmp > 0) {
            root.right = silRec(root.right, adSoyad);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Node minSag = enKucuk(root.right);
            root.üye = minSag.üye;
            root.right = silRec(root.right, minSag.üye.getAdSoyad());
        }
        return root;
    }

    private Node enKucuk(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // BST içindeki üyeleri alfabetik sırayla konsola yazdırır (sadece test amaçlı).
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.üye);
            inorderRec(root.right);
        }
    }

    // BST içindeki üyeleri alfabetik sırayla GUI'deki TextArea'ya yazdırır.
    public void inorderYazdir(TextArea textArea) {
        inorderRecYazdir(root, textArea);
    }

    private void inorderRecYazdir(Node root, TextArea area) {
        if (root != null) {
            inorderRecYazdir(root.left, area);
            area.appendText(root.üye.toString() + "\n\n");
            inorderRecYazdir(root.right, area);
        }
    }

    // BST içindeki üyeleri alfabetik sırayla bir listeye ekler ve döndürür.
    public List<Üyelik> inOrderListe() {
        List<Üyelik> liste = new ArrayList<>();
        inorderListele(root, liste);
        return liste;
    }
    // Verilen düğümden başlayarak BST'yi inorder (alfabetik) şekilde dolaşır ve üyeleri listeye ekler.
    private void inorderListele(Node node, List<Üyelik> liste) {
        if (node != null) {
            inorderListele(node.left, liste);
            liste.add(node.üye);
            inorderListele(node.right, liste);
        }
    }

    //  Ağacı temizle (yenidenYukle için)
    public void temizle() {
        root = null;
    }
}
