package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import huuphu.aprotrain.client_app.Model.OrderItem;
import huuphu.aprotrain.client_app.OrderDetailActivity;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Onclick.OrderListener;

public class Order_Diss_Adapter extends RecyclerView.Adapter {

    private Activity fragment;
    private List<OrderItem> orderItems;
    OrderListener orderListener;

    public Order_Diss_Adapter(Activity fragment, List<OrderItem> orderItems) {
        this.fragment = fragment;
        this.orderItems = orderItems;
    }

    public Order_Diss_Adapter(OrderListener orderListener) {
        this.orderListener = orderListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = fragment.getLayoutInflater().inflate(R.layout.item_list_oder,parent,false);
        ProductHolder holder = new ProductHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        OrderItem model = orderItems.get(position);
        productHolder.tv_Id.setText("Mã: "+ model.id);
        String Price = NumberFormat.getNumberInstance(Locale.US).format(orderItems.get(position).getTotalPrice());
        productHolder.tv_Price.setText("Giá đơn hàng: " +Price+" vnđ");
        productHolder.tv_day.setText("Ngày đặt: " + model.createdAt);

        productHolder.btn_go_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderDetailActivity.class);
                intent.putExtra("id_order", model.id);
                fragment.startActivity(intent);
            }
        });

        switch (model.status){
            case "WAITING":
                productHolder.iv_Image.setImageResource(R.drawable.sandclock);
                productHolder.tv_shop.setText("");
                productHolder.tv_shop.setTextColor(Color.YELLOW);
                break;
            case "DELIVERING":
                productHolder.iv_Image.setImageResource(R.drawable.fastdelivery);
                productHolder.tv_shop.setText("");
                productHolder.tv_shop.setTextColor(Color.BLUE);
                break;
            case "CONFIRMED":
                productHolder.iv_Image.setImageResource(R.drawable.shipped);
                productHolder.tv_shop.setText("");
                productHolder.tv_shop.setTextColor(Color.GREEN);
                break;
            case "CANCELLED":
                productHolder.iv_Image.setImageResource(R.drawable.cancel);
                productHolder.tv_shop.setText("");
                productHolder.tv_shop.setTextColor(Color.RED);
                break;
        }


    }
    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tv_Id;
        TextView tv_Price;
        TextView tv_day;
        TextView tv_shop;
        TextView btn_go_detail;
        ImageView iv_Image;


        public ProductHolder (@NonNull View itemview){
            super(itemview);
            tv_Id = itemview.findViewById(R.id.tv_id_lst_order);
            tv_Price = itemview.findViewById(R.id.tv_price_lst_order);
            tv_day = itemview.findViewById(R.id.tv_day_lst_order);
            iv_Image = itemview.findViewById(R.id.iv_lt_order);
            tv_shop = itemview.findViewById(R.id.tv_shop);
            btn_go_detail = itemview.findViewById(R.id.tv_go_order_detail);
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