package com.example.testing.network;

import com.example.testing.modelclass.CharacterDetailsResponse;
import com.example.testing.modelclass.CharacterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    public interface ResponseInterface {
        void deployResponse(CharacterResponse response);

        void failureResponse(String response);
    }


    public interface ResponseInterfacesec {
        void deployResponse(CharacterDetailsResponse response);

        void failureResponse(String response);
    }

    public ResponseInterface responseInterface1;
    public ResponseInterfacesec responseInterfacesec;


    public void page(String pageno,ResponseInterface responseInterface1) {

        Call<CharacterResponse> call = RetrofitClient.getAPIInterface().page(pageno);

        call.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                responseInterface1.deployResponse(response.body());

            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                responseInterface1.failureResponse(t.getMessage());
            }
        });

    }

    public void details(String id,ResponseInterfacesec responseInterfacesec) {

        Call<CharacterDetailsResponse> call = RetrofitClient.getAPIInterface().details(id);

        call.enqueue(new Callback<CharacterDetailsResponse>() {
            @Override
            public void onResponse(Call<CharacterDetailsResponse> call, Response<CharacterDetailsResponse> response) {
                responseInterfacesec.deployResponse(response.body());

            }

            @Override
            public void onFailure(Call<CharacterDetailsResponse> call, Throwable t) {
                responseInterface1.failureResponse(t.getMessage());
            }
        });

    }

}
