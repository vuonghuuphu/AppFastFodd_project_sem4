package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.app.Activity;
import android.graphics.Paint;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import huuphu.aprotrain.client_app.Model.Cart;
import huuphu.aprotrain.client_app.Model.CartItem;
import huuphu.aprotrain.client_app.Model.Id;
import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.Model.Request.Cart_res;
import huuphu.aprotrain.client_app.Model.Request.Cartdata_res;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Fragment.fragment_home;
import huuphu.aprotrain.client_app.View.Onclick.CartListener;
import huuphu.aprotrain.client_app.View.Onclick.ProductOnclick;
import huuphu.aprotrain.client_app.View.Onclick.ShoppingCartOnclick;
import huuphu.aprotrain.client_app.View.Screen.CartActivity;
import huuphu.aprotrain.client_app.View.Screen.ProductdetailActivity;
import huuphu.aprotrain.client_app.data.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter {

    private Activity fragment;
    private List<CartItem> cartItems;
    CartListener cartListener;
    ShoppingCartOnclick shoppingCartOnclick;

    public CartAdapter(Activity fragment, List<CartItem> cartItems, CartListener cartListener) {
        this.fragment = fragment;
        this.cartItems = cartItems;
        this.cartListener = cartListener;
    }

    public void DeleteOnclick (ShoppingCartOnclick shoppingCartOnclick) {
        this.shoppingCartOnclick = shoppingCartOnclick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = fragment.getLayoutInflater().inflate(R.layout.item_cart,parent,false);
        ProductHolder holder = new ProductHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        CartItem model = cartItems.get(position);
        productHolder.tv_Name.setText(model.getProductName());
        String Price = NumberFormat.getNumberInstance(Locale.US).format(model.getUnitPrice());
        productHolder.tv_Price.setText(Price+" vnđ");
        productHolder.quantity.setText("" +  (int)model.getQuantity());
        Picasso.get()
                .load(model.getProductThumbnail())
                .resize(200, 200)
                .centerCrop()
                .into(productHolder.iv_Image);

        productHolder.cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = productHolder.quantity.getText().toString();
                int total = Integer.parseInt(t) + 1;
                Edit_qty(total,model.getId().productId);
                Constants.ttp = Constants.ttp + model.getUnitPrice();
                String Price = NumberFormat.getNumberInstance(Locale.US).format(Constants.ttp);
                productHolder.textView.setText(Price+ " vnđ");
                productHolder.quantity.setText(""+total);
            }
        });

        productHolder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = productHolder.quantity.getText().toString();
                int total = Integer.parseInt(t) - 1;
                if (total == 0){
                    Delete(model.getId());
                }
                Constants.ttp = Constants.ttp - model.getUnitPrice();
                Edit_qty(total,model.getId().productId);
                String Price = NumberFormat.getNumberInstance(Locale.US).format(Constants.ttp);
                productHolder.textView.setText(Price+ " vnđ");
                productHolder.quantity.setText(""+total);
            }
        });

    }
    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tv_Name;
        TextView tv_Price;
        ImageView tru,cong;
        TextView quantity;
        ImageView iv_Image,btn_delete;
        TextView textView;

        public ProductHolder (@NonNull View itemview){
            super(itemview);
            textView = fragment.findViewById(R.id.ttprice);
            tv_Name = itemview.findViewById(R.id.tv_name_product_cart);
            tv_Price = itemview.findViewById(R.id.tv_price_cart);
            tru = itemview.findViewById(R.id.tru);
            cong = itemview.findViewById(R.id.cong);
            iv_Image = itemview.findViewById(R.id.iv_item_cart);
            quantity = itemview.findViewById(R.id.quantity);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            btn_delete = itemview.findViewById(R.id.btn_delete);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shoppingCartOnclick.onClickitem(getAdapterPosition());
                }
            });
        }


        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

      private void Edit_qty (int qty , String idProduct){
          ArrayList<Cartdata_res> cartItemDTOSet = new ArrayList();
          cartItemDTOSet.add(new Cartdata_res(
                  idProduct,qty
          ));
          Cart_res cart_res = new Cart_res();
          cart_res.setCartItemDTOSet(cartItemDTOSet);
          ApiManager.getService().AddCart(cart_res).enqueue(new Callback<Cart>() {
              @Override
              public void onResponse(Call<Cart> call, Response<Cart> response) {
                  System.out.println("pay app" + response.code());
              }

              @Override
              public void onFailure(Call<Cart> call, Throwable t) {

              }
          });
      }

    private void Delete (Id id){
        ApiManager.getService().DeleteCart(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                System.out.println("pay app" + response.code());
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}