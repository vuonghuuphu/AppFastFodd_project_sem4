package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import huuphu.aprotrain.client_app.Model.OrderItem;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.Model.Request.add_star;
import huuphu.aprotrain.client_app.Model.Star;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.OrderDetailActivity;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Onclick.OrderListener;
import huuphu.aprotrain.client_app.data.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_detail_Adapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<ProductItem> productItems;
    private int check;

    public Order_detail_Adapter(Activity activity, List<ProductItem> productItems, int check) {
        this.activity = activity;
        this.productItems = productItems;
        this.check = check;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = activity.getLayoutInflater().inflate(R.layout.item_list_oder_detail,parent,false);
        ProductHolder holder = new ProductHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        ProductItem model = productItems.get(position);
        productHolder.tv_Name.setText(model.getName());
        Picasso.get()
                .load(model.getThumbnail())
                .resize(80, 100)
                .centerCrop()
                .into(productHolder.iv_Image);
        String Price = NumberFormat.getNumberInstance(Locale.US).format(productItems.get(position).getUnit_price());
        productHolder.tv_Price.setText("Giá : " +Price+" vnđ");
productHolder.btn_add_ratting.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dl_add_star);
        dialog.getWindow().setLayout(view.getLayoutParams().MATCH_PARENT,view.getLayoutParams().WRAP_CONTENT);
        dialog.setCancelable(false);

        ImageView star1 = dialog.findViewById(R.id.iv_ratting_star1);
        ImageView star2 = dialog.findViewById(R.id.iv_ratting_star2);
        ImageView star3 = dialog.findViewById(R.id.iv_ratting_star3);
        ImageView star4 = dialog.findViewById(R.id.iv_ratting_star4);
        ImageView star5 = dialog.findViewById(R.id.iv_ratting_star5);
        Button iv_ratting_add_star = dialog.findViewById(R.id.iv_ratting_add_star);

//        star1.setVisibility(holder.itemView.GONE);
//        star2.setVisibility(holder.itemView.GONE);
//        star3.setVisibility(holder.itemView.GONE);
//        star4.setVisibility(holder.itemView.GONE);
//        star5.setVisibility(holder.itemView.GONE);

        final int[] checkStar = {0};

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStar[0] = 1;
                star1.setImageResource(R.drawable.star);
                star2.setImageResource(R.drawable.star_dis);
                star3.setImageResource(R.drawable.star_dis);
                star4.setImageResource(R.drawable.star_dis);
                star5.setImageResource(R.drawable.star_dis);
            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStar[0] = 2;
                star1.setImageResource(R.drawable.star);
                star2.setImageResource(R.drawable.star);
                star3.setImageResource(R.drawable.star_dis);
                star4.setImageResource(R.drawable.star_dis);
                star5.setImageResource(R.drawable.star_dis);
            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStar[0] = 3;
                star1.setImageResource(R.drawable.star);
                star2.setImageResource(R.drawable.star);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star_dis);
                star5.setImageResource(R.drawable.star_dis);
            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStar[0] = 4;
                star1.setImageResource(R.drawable.star);
                star2.setImageResource(R.drawable.star);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star_dis);
            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStar[0] = 5;
                star1.setImageResource(R.drawable.star);
                star2.setImageResource(R.drawable.star);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
            }
        });



        iv_ratting_add_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_star add_star = new add_star(checkStar[0],model.getId(), Constants.idUser);
                ApiManager.getService().addStar(add_star).enqueue(new Callback<Star>() {
                    @Override
                    public void onResponse(Call<Star> call, Response<Star> response) {
                        if (response.code() == 201){
                            Toast.makeText(holder.itemView.getContext(), "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                        }




                    }

                    @Override
                    public void onFailure(Call<Star> call, Throwable t) {

                    }
                });
                dialog.cancel();
            }
        });



        dialog.show();
    }
});
    }
    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tv_Name,btn_add_ratting;
        TextView tv_Price;
        ImageView iv_Image;


        public ProductHolder (@NonNull View itemview){
            super(itemview);
            tv_Name = itemview.findViewById(R.id.tv_id_lst_order_name);
            tv_Price = itemview.findViewById(R.id.tv_price_lst_order_price);
            iv_Image = itemview.findViewById(R.id.iv_lt_order_detail);
            btn_add_ratting = itemview.findViewById(R.id.btn_add_ratting);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            if (check != 1){
                btn_add_ratting.setVisibility(itemview.GONE);
            }
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