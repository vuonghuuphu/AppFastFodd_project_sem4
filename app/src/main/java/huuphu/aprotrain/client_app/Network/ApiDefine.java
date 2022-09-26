package huuphu.aprotrain.client_app.Network;


import java.util.List;

import huuphu.aprotrain.client_app.Model.Account;
import huuphu.aprotrain.client_app.Model.Cart;
import huuphu.aprotrain.client_app.Model.Category;
import huuphu.aprotrain.client_app.Model.Comments;
import huuphu.aprotrain.client_app.Model.Customers;
import huuphu.aprotrain.client_app.Model.Id;
import huuphu.aprotrain.client_app.Model.ListOrder;
import huuphu.aprotrain.client_app.Model.OrderItem;
import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.Model.Replies;
import huuphu.aprotrain.client_app.Model.Request.Account_cus;
import huuphu.aprotrain.client_app.Model.Request.Cart_res;
import huuphu.aprotrain.client_app.Model.Request.Comment_res;
import huuphu.aprotrain.client_app.Model.Request.LoginRequest;
import huuphu.aprotrain.client_app.Model.Request.Oder_res;
import huuphu.aprotrain.client_app.Model.Request.Order_res;
import huuphu.aprotrain.client_app.Model.Request.Rep_res;
import huuphu.aprotrain.client_app.Model.Request.add_star;
import huuphu.aprotrain.client_app.Model.Response.Account_cus_rp;
import huuphu.aprotrain.client_app.Model.Response.LoginResponse;
import huuphu.aprotrain.client_app.Model.Response.RegisterResponse;
import huuphu.aprotrain.client_app.Model.Slide;
import huuphu.aprotrain.client_app.Model.Star;
import huuphu.aprotrain.client_app.Model.UserRes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiDefine {
    String URL = "https://api-exam4.herokuapp.com/api/v1/";

    @POST("auth/login")
    Call<LoginResponse> apiLogin (@Body LoginRequest request);

    @POST("auth/register")
    Call<RegisterResponse> apiRegister (@Body LoginRequest request);

    @GET("categories")
    Call<List<Category>>getlistCategories();

    @GET("products")
    Call<List<ProductItem>>getlistProducts();

    @GET("products/{id}")
    Call<ProductItem> getProductDetail(@Path("id") String id);

    @GET("auth/user")
    Call<UserRes> getUser();

    @GET("shopping-cart/get-all")
    Call<List<Cart>> getCart();

    @POST("shopping-cart")
    Call<Cart> AddCart (@Body Cart_res cart_res);

    @GET("orders")
    Call<ListOrder> getOrder();

    @GET("orders/{id}")
    Call<OrderItem> getOrderDetail(@Path("id") String id);

    @GET("ratings")
    Call<List<Star>> getStar();

    @GET("comments")
    Call<List<Comments>> getComments();

    @GET("customers")
    Call<List<Customers>> getCustomers();

    @GET("replies")
    Call<List<Replies>> getReplies();

    @POST("cartitems")
    Call<Boolean> DeleteCart (@Body Id id);

    @POST("comments")
    Call<Comment_res> AddComment(@Body Comment_res request);

    @POST("replies")
    Call<Replies> AddRep (@Body Rep_res request);

    @POST("orders")
    Call<OrderItem> AddOrder (@Body Oder_res request);

    @GET("products/get-page")
    Call<Product> getPrductCate (@Query("categoryId") String id);

    @POST("customers")
    Call<Customers> addCustomers(@Body Account_cus request);

    @PUT("customers/{id}")
    Call<Account_cus_rp> EditCustomers(@Path("id") String id,@Body Account_cus_rp request);

    @POST("ratings")
    Call<Star> addStar(@Body add_star request);

    @GET("slides")
    Call<List<Slide>> getSlide ();

    @PUT("orders/{id}")
    Call<OrderItem> EditOrder (@Path("id") String id,@Body Order_res request);
}
