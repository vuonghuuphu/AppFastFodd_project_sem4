package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Fragment.fragment_home;
import huuphu.aprotrain.client_app.View.Onclick.ProductOnclick;

public class ProductNewAdapter extends RecyclerView.Adapter {

    private Fragment fragment;
    private List<ProductItem> productList;
    ProductOnclick productOnclick;

    public ProductNewAdapter(fragment_home activity, List<ProductItem> productList) {
        this.fragment = activity;
        this.productList = productList;
    }

    public void setProductOnclick(ProductOnclick productOnclick){
        this.productOnclick = productOnclick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = fragment.getLayoutInflater().inflate(R.layout.item_list_product,parent,false);
        ProductHolder holder = new ProductHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        ProductItem model = productList.get(position);
        productHolder.tv_Name.setText(model.getName());
        String Price_un = NumberFormat.getNumberInstance(Locale.US).format(model.getPromotion_price());
        String Price = NumberFormat.getNumberInstance(Locale.US).format(model.getUnit_price());
        if (model.getPromotion_price() == 0){
            productHolder.tv_Luotmua.setVisibility(View.GONE);
            productHolder.tv_Price.setText(Price+" vn??");
        }else {
            productHolder.tv_Luotmua.setText(Price + " vn??");
            productHolder.tv_Luotmua.setPaintFlags(productHolder.tv_Luotmua.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            productHolder.tv_Price.setText(Price_un+" vn??");
        }
        Picasso.get()
                .load(model.getThumbnail())
                .resize(610, 622)
                .into(productHolder.iv_Image);
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tv_Name;
        TextView tv_Price;
        TextView tv_Luotmua;
        ImageView iv_Image;


        public ProductHolder (@NonNull View itemview){
            super(itemview);
            tv_Name = itemview.findViewById(R.id.tv_name_product);
            tv_Price = itemview.findViewById(R.id.tv_price);
            tv_Luotmua = itemview.findViewById(R.id.tv_luotmua);
            iv_Image = itemview.findViewById(R.id.iv_item_listproduct);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productOnclick.onClickitem(getAdapterPosition());
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
}