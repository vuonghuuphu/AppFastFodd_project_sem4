package huuphu.aprotrain.client_app.View.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import huuphu.aprotrain.client_app.ChinhsachActivity;
import huuphu.aprotrain.client_app.Model.Customers;
import huuphu.aprotrain.client_app.Model.Response.Account_cus_rp;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.OderActivity;
import huuphu.aprotrain.client_app.Order_dis_Activity;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Screen.LoginActivity;
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
        TextView btn_edit_user_dl = view.findViewById(R.id.btn_edit_user_dl);
        LinearLayout linearLayout = view.findViewById(R.id.layput_user);
        LinearLayout ll_Order_diss = view.findViewById(R.id.ll_Order_diss);
        LinearLayout csbm = view.findViewById(R.id.csbm);

        Dialog dialogoload = new Dialog(view.getContext()); // Context, this, etc.
        dialogoload.setContentView(R.layout.dialogloading);
        dialogoload.show();

        btn_login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_edit_user_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dl_edit_account_in_order);
                dialog.getWindow().setLayout(view.getLayoutParams().MATCH_PARENT,view.getLayoutParams().WRAP_CONTENT);
                dialog.setCancelable(true);

                TextView dialogedit_cus,
                        ed_name_cus_dl,
                        ed_address_cus_dl,
                        ed_sdt_cus_dl,
                        ed_email_cus_dl,
                        ed_gender_cus_dl;


                dialogedit_cus = dialog.findViewById(R.id.dialogedit_cus);
                ed_address_cus_dl = dialog.findViewById(R.id.ed_address_cus_dl);
                ed_name_cus_dl = dialog.findViewById(R.id.ed_name_cus_dl);
                ed_sdt_cus_dl = dialog.findViewById(R.id.ed_sdt_cus_dl);
                ed_email_cus_dl = dialog.findViewById(R.id.ed_email_cus_dl);
                ed_gender_cus_dl = dialog.findViewById(R.id.ed_gender_cus_dl);

                ApiManager.getService().getCustomers().enqueue(new Callback<List<Customers>>() {
                    @Override
                    public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                        List<Customers> customers = response.body();
                        for (int i = 0; i < customers.size(); i++) {
                            if (customers.get(i).id_account.equals(Constants.idUser)){
                                ed_name_cus_dl.setText(customers.get(i).getName());
                                ed_address_cus_dl.setText(customers.get(i).getAddress());
                                ed_email_cus_dl.setText(customers.get(i).getEmail());
                                if (customers.get(i).getGender() == null){
                                    ed_gender_cus_dl.setText(" Chưa có thông tin* ");
                                }else {
                                    ed_gender_cus_dl.setText(customers.get(i).getGender());
                                }
                                ed_sdt_cus_dl.setText( customers.get(i).phone_number);
                                int idr = customers.get(i).getId();
                                String Id_account = customers.get(i).getId_account();
                                dialogedit_cus.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(ed_name_cus_dl.getText().toString().equals("") ||
                                                ed_address_cus_dl.getText().toString().equals("") ||
                                                ed_sdt_cus_dl.getText().toString().equals("")){
                                            Toast.makeText(view.getContext(), "Nhập đầy đủ các thông tin", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Account_cus_rp account_cus_rp = new Account_cus_rp(
                                                    idr,
                                                    Id_account,
                                                    ed_name_cus_dl.getText().toString(),
                                                    ed_gender_cus_dl.getText().toString(),
                                                    ed_email_cus_dl.getText().toString(),
                                                    ed_address_cus_dl.getText().toString(),
                                                    ed_sdt_cus_dl.getText().toString());
                                            ApiManager.getService().EditCustomers(""+idr,account_cus_rp) .enqueue(new Callback<Account_cus_rp>() {
                                                @Override
                                                public void onResponse(Call<Account_cus_rp> call, Response<Account_cus_rp> response) {
                                                    Constants.name_cus = ed_name_cus_dl.getText().toString();
                                                    Constants.sdt_cus = ed_address_cus_dl.getText().toString();
                                                    Constants.address_cus =  ed_sdt_cus_dl.getText().toString();
                                                    dialog.cancel();
                                                }

                                                @Override
                                                public void onFailure(Call<Account_cus_rp> call, Throwable t) {

                                                }
                                            });

                                            tv_Name.setText(account_cus_rp.getName());
                                            tv_Address.setText("Địa chỉ giao hàng: "+ account_cus_rp.getAddress());
                                            tv_Id.setText("Mã tài khoản: " + (int)account_cus_rp.getId());
                                            tv_Email.setText("Địa chỉ Email: "+ account_cus_rp.getEmail());
                                            if (account_cus_rp.getGender() == null){
                                                tv_Gender.setText("Giới tính: Chưa có thông tin* ");
                                            }else {
                                                tv_Gender.setText("Giới tính: " + account_cus_rp.getGender());
                                            }
                                            tv_Phone.setText("Số điện thoại: " + account_cus_rp.getPhone_number());
                                            dialog.cancel();
                                        }

                                    }
                                });
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Customers>> call, Throwable t) {

                    }
                });

                dialog.show();
            }
        });


        ll_Order_diss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Order_dis_Activity.class);
                startActivity(intent);
            }
        });

        csbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ChinhsachActivity.class);
                startActivity(intent);
            }
        });

        if (Constants.token == null){
            btn_login_user.setVisibility(View.VISIBLE);
            tv_Name.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            dialogoload.cancel();
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
                dialogoload.cancel();
            }

            @Override
            public void onFailure(Call<List<Customers>> call, Throwable t) {

            }
        });
        }



        return view;
    }
}