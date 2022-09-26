package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.Comments;
import huuphu.aprotrain.client_app.Model.Customers;
import huuphu.aprotrain.client_app.Model.Replies;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Replies> repliesList;

    public RepAdapter(Activity activity, List<Replies> repliesList) {
        this.activity = activity;
        this.repliesList = repliesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = activity.getLayoutInflater().inflate(R.layout.item_rep,parent,false);
        ProductHolder holder = new ProductHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        Replies model = repliesList.get(position);
        productHolder.tv_comments1.setText(model.getContent());
        ApiManager.getService().getCustomers().enqueue(new Callback<List<Customers>>() {
            @Override
            public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                for (Customers c:response.body()) {
                    if (Objects.equals(c.id_account, model.getId_account())){
                        productHolder.tv_comments_user1.setText(c.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Customers>> call, Throwable t) {

            }
        });


    }
    @Override
    public int getItemCount() {
        return repliesList.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tv_comments_user1,tv_comments1;
        public ProductHolder (@NonNull View itemview){
            super(itemview);
            tv_comments1 = itemview.findViewById(R.id.tv_comments1);
            tv_comments_user1 = itemview.findViewById(R.id.tv_comments_user1);
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