package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection; 

public class CartBean {
    private Map<Integer, ProductBean> carrello;

    public CartBean() {
        carrello = new HashMap<>();
    }

    public Collection<ProductBean> getCarrello() {
        return carrello.values();
    }

    public ProductBean retriveByKey(int codice) {
        return carrello.get(codice);
    }

    public void addItem(ProductBean prodotto) {
        carrello.put(prodotto.getCode(), prodotto);
    }

    public void removeItem(int codice) {
        carrello.remove(codice);
    }

    public boolean isEmpty() {
        return carrello.isEmpty();
    }

    public void clear() {
        carrello.clear();
    }

    public void setLista(Collection<ProductBean> lista) {
        carrello.clear();
        for (ProductBean prodotto : lista) {
            carrello.put(prodotto.getCode(), prodotto);
        }
    }

    public void removeAllItems() {
        carrello.clear();
    }
}
