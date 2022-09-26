package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Fragment.fragment_home;

public class ProductOtherAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<ProductItem> productList;

    public ProductOtherAdapter(Activity activity, List<ProductItem> productList) {
        this.activity = activity;
        this.productList = productList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = activity.getLayoutInflater().inflate(R.layout.item_list_product_ho,parent,false);
        ProductOtherAdapter.ProductHolder holder = new ProductOtherAdapter.ProductHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductOtherAdapter.ProductHolder productHolder = (ProductOtherAdapter.ProductHolder) holder;
        ProductItem model = productList.get(position);
        productHolder.tv_Name.setText(model.getName());
        productHolder.tv_Luotmua.setText(""+ (int) model.getQty()+"");
        productHolder.tv_Price.setText(""+model.getUnit_price()+"");
        Picasso.get()
                .load(model.getThumbnail())
                .resize(100, 100)
                .centerCrop()
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