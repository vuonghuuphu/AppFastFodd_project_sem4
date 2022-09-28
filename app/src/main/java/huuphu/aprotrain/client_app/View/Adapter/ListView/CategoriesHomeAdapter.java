package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import huuphu.aprotrain.client_app.Model.Category;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Fragment.fragment_home;
import huuphu.aprotrain.client_app.View.Onclick.CategoriesOnclick;

public class CategoriesHomeAdapter extends RecyclerView.Adapter {

    private Fragment fragment;
    private List<Category> categoryList;
    CategoriesOnclick categoriesOnclick;
    List<String> stringList = new ArrayList<>();

    public CategoriesHomeAdapter (fragment_home activity, List<Category> categoryList) {
        this.fragment = activity;
        this.categoryList = categoryList;
    }

    public void setCategoriesOnclick(CategoriesOnclick categoriesOnclick){
        this.categoriesOnclick = categoriesOnclick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = fragment.getLayoutInflater().inflate(R.layout.item_list_categories,parent,false);
        CategoryHolder hoder = new CategoryHolder(itemview);
        return hoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryHolder categoryHolder = (CategoryHolder) holder;
        Category model = categoryList.get(position);
        categoryHolder.tv_Name.setText(model.getName());
        Picasso.get()
                .load(model.getThumbnail())
                .resize(70, 70)
                .into(categoryHolder.iv_Image);
        categoryHolder.cardView.setBackgroundResource(R.drawable.border_card);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView tv_Name;
        ImageView iv_Image;
        CardView cardView;


        public CategoryHolder (@NonNull View itemview){
            super(itemview);
            cardView = itemview.findViewById(R.id.cardview_cate);
            tv_Name = itemview.findViewById(R.id.tv_name_categories);
            iv_Image = itemview.findViewById(R.id.iv_item_listcate);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categoriesOnclick.onClickitem(getAdapterPosition());
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
