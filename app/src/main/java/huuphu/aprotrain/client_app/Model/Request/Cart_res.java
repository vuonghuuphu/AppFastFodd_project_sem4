package huuphu.aprotrain.client_app.Model.Request;

import java.util.ArrayList;

import huuphu.aprotrain.client_app.Model.Id;

public class Cart_res {
    ArrayList<Cartdata_res> cartItemDTOSet = new ArrayList();

    public Cart_res() {
        this.cartItemDTOSet = cartItemDTOSet;
    }

    public ArrayList<Cartdata_res> getCartItemDTOSet() {
        return cartItemDTOSet;
    }

    public void setCartItemDTOSet(ArrayList<Cartdata_res> cartItemDTOSet) {
        this.cartItemDTOSet = cartItemDTOSet;
    }
}
