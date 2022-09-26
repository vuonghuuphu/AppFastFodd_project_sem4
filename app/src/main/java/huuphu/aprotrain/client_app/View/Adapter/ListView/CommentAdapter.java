package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.Account;
import huuphu.aprotrain.client_app.Model.Comments;
import huuphu.aprotrain.client_app.Model.Customers;
import huuphu.aprotrain.client_app.Model.EnumApp;
import huuphu.aprotrain.client_app.Model.OrderItem;
import huuphu.aprotrain.client_app.Model.Replies;
import huuphu.aprotrain.client_app.Model.Request.Comment_res;
import huuphu.aprotrain.client_app.Model.Request.Rep_res;
import huuphu.aprotrain.client_app.Model.UserRes;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.OrderDetailActivity;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Fragment.fragment_cart;
import huuphu.aprotrain.client_app.View.Onclick.OrderListener;
import huuphu.aprotrain.client_app.View.Screen.ProductdetailActivity;
import huuphu.aprotrain.client_app.data.Constants;
import huuphu.aprotrain.client_app.data.SharedPreferencesHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Comments> commentsList;

    public CommentAdapter(Activity activity, List<Comments> commentsList) {
        this.activity = activity;
        this.commentsList = commentsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = activity.getLayoutInflater().inflate(R.layout.item_comment,parent,false);
        ProductHolder holder = new ProductHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        Comments model = commentsList.get(position);
        ApiManager.getService().getReplies().enqueue(new Callback<List<Replies>>() {
            @Override
            public void onResponse(Call<List<Replies>> call, Response<List<Replies>> response) {
                productHolder.rcv.setVisibility(holder.itemView.GONE);
                productHolder.drop_up.setVisibility(holder.itemView.GONE);
                productHolder.btn_rep.setVisibility(holder.itemView.GONE);

                List<Replies> rl = new ArrayList<>();
                List<Replies> replies = response.body();
                for (Replies r : replies){
                    if (r.getId_comment() == model.getId()){
                        rl.add(r);
                        productHolder.icon_replly.setVisibility(holder.itemView.VISIBLE);
                    }
                }
                RepAdapter adapter = new RepAdapter(activity,rl);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext(),RecyclerView.VERTICAL,false);
                productHolder.rcv.setLayoutManager(layoutManager);
                productHolder.rcv.setAdapter(adapter);

                productHolder.btn_rep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialog = new Dialog(view.getContext());
                        dialog.setContentView(R.layout.dl_add_comment);
                        dialog.getWindow().setLayout(view.getLayoutParams().MATCH_PARENT,view.getLayoutParams().WRAP_CONTENT);
                        dialog.setCancelable(false);

                        Button btn_add = dialog.findViewById(R.id.button_send_comment);
                        Button btn_close = dialog.findViewById(R.id.button_close_comment);
                        EditText textView_comment = dialog.findViewById(R.id.textView_comment);

                        btn_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });

                        btn_add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Rep_res rep_res = new Rep_res(textView_comment.getText().toString(),
                                        model.getId(),Constants.idUser,model.getId_product());
                                ApiManager.getService().AddRep(rep_res).enqueue(new Callback<Replies>() {
                                    @Override
                                    public void onResponse(Call<Replies> call, Response<Replies> response) {
                                        rl.add(response.body());
                                        RepAdapter adapter = new RepAdapter(activity,rl);
                                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext(),RecyclerView.VERTICAL,false);
                                        productHolder.rcv.setLayoutManager(layoutManager);
                                        productHolder.rcv.setAdapter(adapter);
                                        dialog.cancel();
                                    }

                                    @Override
                                    public void onFailure(Call<Replies> call, Throwable t) {

                                    }
                                });
                            }
                        });


                        dialog.show();
                    }
                });

                productHolder.icon_replly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        productHolder.rcv.setVisibility(holder.itemView.VISIBLE);
                        productHolder.drop_up.setVisibility(holder.itemView.VISIBLE);
                        productHolder.icon_replly.setVisibility(holder.itemView.GONE);
                        productHolder.btn_rep.setVisibility(holder.itemView.VISIBLE);
                    }
                });
                productHolder.drop_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        productHolder.rcv.setVisibility(holder.itemView.GONE);
                        productHolder.drop_up.setVisibility(holder.itemView.GONE);
                        productHolder.btn_rep.setVisibility(holder.itemView.GONE);
                        productHolder.icon_replly.setVisibility(holder.itemView.VISIBLE);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Replies>> call, Throwable t) {
            }
        });
        ApiManager.getService().getCustomers().enqueue(new Callback<List<Customers>>() {
            @Override
            public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                for (Customers c:response.body()) {
                    if (Objects.equals(c.id_account, model.getId_account())){
                        productHolder.tv_comments_user.setText(c.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Customers>> call, Throwable t) {

            }
        });
     productHolder.tv_comment.setText(model.getContent());

    }
    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tv_comment,tv_comments_user,btn_rep;
        RecyclerView rcv;
        ImageView drop_up,icon_replly;
        public ProductHolder (@NonNull View itemview){
            super(itemview);
            tv_comment = itemview.findViewById(R.id.tv_comments);
            tv_comments_user = itemview.findViewById(R.id.tv_comments_user);
            rcv = itemview.findViewById(R.id.rcv_rep);
            btn_rep = itemview.findViewById(R.id.btn_rep);
            drop_up = itemview.findViewById(R.id.drop_up);
            icon_replly = itemview.findViewById(R.id.icon_replly);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
           rcv.setVisibility(itemView.GONE);
            drop_up.setVisibility(itemView.GONE);
           btn_rep.setVisibility(itemView.GONE);

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