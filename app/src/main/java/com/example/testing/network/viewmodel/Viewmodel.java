package com.example.testing.network.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testing.modelclass.CharacterDetailsResponse;
import com.example.testing.modelclass.CharacterResponse;
import com.example.testing.modelclass.LoginResponse;
import com.example.testing.network.Repository;

public class Viewmodel extends ViewModel {

    Repository repository = new Repository();
    MutableLiveData<CharacterResponse> characterResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<CharacterDetailsResponse> characterDetailsResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> failureResponseMutableLiveData = new MutableLiveData<>();

    public void pageload(String pageno) {

        repository.page( pageno,new Repository.ResponseInterface() {
            @Override
            public void deployResponse(CharacterResponse response) {
                characterResponseMutableLiveData.setValue(response);

            }

            @Override
            public void failureResponse(String response) {
                failureResponseMutableLiveData.setValue(response);
            }
        });
    }

    public void details(String id) {

        repository.details(id, new Repository.ResponseInterfacesec() {
            @Override
            public void deployResponse(CharacterDetailsResponse response) {
                characterDetailsResponseMutableLiveData.setValue(response);

            }

            @Override
            public void failureResponse(String response) {
                failureResponseMutableLiveData.setValue(response);
            }
        });
    }


    public MutableLiveData<CharacterResponse> getCharacterResponseMutableLiveData() {
        return characterResponseMutableLiveData;
    }

    public MutableLiveData<CharacterDetailsResponse> getCharacterDetailsResponseMutableLiveData() {
        return characterDetailsResponseMutableLiveData;
    }

    public MutableLiveData<String> getFailureResponseMutableLiveData() {
        return failureResponseMutableLiveData;
    }
}
