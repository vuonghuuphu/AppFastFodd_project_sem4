package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import huuphu.aprotrain.client_app.Model.CartItem;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Onclick.CartListener;

public class Product_order_Adapter extends RecyclerView.Adapter {

    private Activity fragment;
    private List<CartItem> cartItems;

    public Product_order_Adapter(Activity fragment, List<CartItem> cartItems) {
        this.fragment = fragment;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = fragment.getLayoutInflater().inflate(R.layout.item_product_oder,parent,false);
        ProductHolder holder = new ProductHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        CartItem model = cartItems.get(position);
        productHolder.tv_Name.setText(model.getProductName());
        String Price = NumberFormat.getNumberInstance(Locale.US).format(model.getUnitPrice() * model.getQuantity());
        productHolder.tv_Price.setText(Price);
        productHolder.tv_Luotmua.setText("Số lượng: "+(int) model.getQuantity());
        Picasso.get()
                .load(model.getProductThumbnail())
                .resize(200, 200)
                .centerCrop()
                .into(productHolder.iv_Image);
    }
    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tv_Name;
        TextView tv_Price;
        TextView tv_Luotmua;
        ImageView iv_Image;


        public ProductHolder (@NonNull View itemview){
            super(itemview);
            tv_Name = itemview.findViewById(R.id.tv_name_product_oder);
            tv_Price = itemview.findViewById(R.id.tv_price_product_oder);
            tv_Luotmua = itemview.findViewById(R.id.tv_giagoc_product_oder);
            iv_Image = itemview.findViewById(R.id.iv_item_product_oder);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}