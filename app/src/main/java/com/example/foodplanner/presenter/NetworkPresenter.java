package com.example.foodplanner.presenter;

import androidx.lifecycle.LifecycleOwner;

import com.example.foodplanner.view.NetworkInterface;

public class NetworkPresenter {
    private NetworkConnection networkConnection;
    private NetworkInterface networkInterface;

    public NetworkPresenter(NetworkInterface networkInterface , NetworkConnection networkConnection){
        this.networkInterface=networkInterface;
        this.networkConnection=networkConnection;
    }

    public void startListening(){
        networkConnection.registerNetworkListener();
        networkConnection.getNetworkState().observe((LifecycleOwner) networkInterface, isConnected -> {
            if(isConnected){
                networkInterface.onNetworkAvaliable();
            }else{
                networkInterface.onNetworkLost();
            }
        });
    }

    public void stopListening(){
        networkConnection.unregisterNetworkListener();
    }
}
