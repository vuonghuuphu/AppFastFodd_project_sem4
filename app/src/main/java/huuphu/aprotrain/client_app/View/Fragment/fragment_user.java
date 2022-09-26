package huuphu.aprotrain.client_app.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import huuphu.aprotrain.client_app.Model.Customers;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.Order_dis_Activity;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.data.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_user extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_user, container, false);

        TextView tv_Name = view.findViewById(R.id.tv_account_nameAccount);
        TextView tv_Address = view.findViewById(R.id.tv_account_address);
        TextView tv_Email = view.findViewById(R.id.tv_account_email);
        TextView tv_Gender = view.findViewById(R.id.tv_account_gender);
        TextView tv_Phone = view.findViewById(R.id.tv_account_phone);
        TextView tv_Id = view.findViewById(R.id.tv_account_id);
        TextView btn_login_user = view.findViewById(R.id.btn_login_user);
        LinearLayout linearLayout = view.findViewById(R.id.layput_user);
        LinearLayout ll_Order_diss = view.findViewById(R.id.ll_Order_diss);
        ll_Order_diss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Order_dis_Activity.class);
                startActivity(intent);
            }
        });
        if (Constants.token == null){
            btn_login_user.setVisibility(View.VISIBLE);
            tv_Name.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
        }else {
            btn_login_user.setVisibility(View.GONE);
            tv_Name.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
        ApiManager.getService().getCustomers().enqueue(new Callback<List<Customers>>() {
            @Override
            public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                List<Customers> customers = response.body();
                for (int i = 0; i < customers.size(); i++) {
                   if (customers.get(i).id_account.equals(Constants.idUser)){
                       tv_Name.setText(customers.get(i).getName());
                       tv_Address.setText("Địa chỉ giao hàng: "+ customers.get(i).getAddress());
                       tv_Id.setText("Mã tài khoản: " + customers.get(i).getId());
                       tv_Email.setText("Địa chỉ Email: "+ customers.get(i).getEmail());
                       if (customers.get(i).getGender() == null){
                           tv_Gender.setText("Giới tính: Chưa có thông tin* ");
                       }else {
                           tv_Gender.setText("Giới tính: " + customers.get(i).getGender());
                       }
                       tv_Phone.setText("Số điện thoại: " + customers.get(i).phone_number);
                   }
                }
            }

            @Override
            public void onFailure(Call<List<Customers>> call, Throwable t) {

            }
        });
        }



        return view;
    }
}