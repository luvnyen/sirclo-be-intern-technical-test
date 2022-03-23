import java.util.*;

class Cart {
    private Map<String, Integer> cartItems;

    public Cart() {
        this.cartItems = new HashMap<>();
    }

    public void tambahProduk(String kodeProduk, int kuantitas) {
        if (cartItems.containsKey(kodeProduk)) {
            cartItems.put(kodeProduk, cartItems.get(kodeProduk) + kuantitas);
        } else {
            cartItems.put(kodeProduk, kuantitas);
        }
    }

    public void hapusProduk(String kodeProduk) {
        cartItems.remove(kodeProduk);
    }

    public void tampilkanCart() {
        for (String kodeProduk : cartItems.keySet()) {
            System.out.println(kodeProduk + " (" + cartItems.get(kodeProduk) + ")");
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Cart keranjang = new Cart();

        keranjang.tambahProduk("Pisang Hijau", 2);

        keranjang.tambahProduk("Semangka Kuning", 3);
        
        keranjang.tambahProduk("Apel Merah", 1);
        keranjang.tambahProduk("Apel Merah", 4);
        keranjang.tambahProduk("Apel Merah", 2);
        
        keranjang.hapusProduk("Semangka Kuning");
        
        keranjang.hapusProduk("Semangka Merah");
        
        keranjang.tampilkanCart();
    }
}
