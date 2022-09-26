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

import huuphu.aprotrain.client_app.Model.Category;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Fragment.fragment_home;

public class ImgDetailAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private String[] ImgList;

    public ImgDetailAdapter(Activity activity, String[] ImgList) {
        this.activity = activity;
        this.ImgList = ImgList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = activity.getLayoutInflater().inflate(R.layout.images_detail,parent,false);
        ImagesHolder holder = new ImagesHolder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImagesHolder imagesHolder = (ImagesHolder) holder;
        String img = ImgList[position];
        Picasso.get()
                .load(img)
                .resize(150, 150)
                .centerCrop()
                .into(imagesHolder.iv_Image);
    }

    @Override
    public int getItemCount() {
        return ImgList.length;
    }

    public class ImagesHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView iv_Image;


        public ImagesHolder (@NonNull View itemview){
            super(itemview);
            iv_Image = itemview.findViewById(R.id.imv_imagesDetail);
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