package com.example.foodplanner.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NetworkConnection {
    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;
    private MutableLiveData<Boolean> isConnected = new MutableLiveData<>();

    public NetworkConnection(Context context){
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        checkApplicationState();
    }

    public void registerNetworkListener(){
        NetworkRequest networkRequest = new NetworkRequest.Builder().build();
        networkCallback = new ConnectivityManager.NetworkCallback(){
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                isConnected.postValue(true);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                isConnected.postValue(false);
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();
                isConnected.postValue(false);
            }
        };
        connectivityManager.registerNetworkCallback(networkRequest,networkCallback);
    }
    private void checkApplicationState() {
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        boolean connected = capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));

        isConnected.postValue(connected);
    }
    public void unregisterNetworkListener(){
        if(networkCallback!=null){
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }

    public LiveData<Boolean> getNetworkState(){
        return isConnected;
    }
}
