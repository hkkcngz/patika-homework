package com.largeproject.figmadesigns.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.largeproject.figmadesigns.R;
import com.largeproject.figmadesigns.databinding.FragmentWelcomeBinding;
import com.largeproject.figmadesigns.model.CoinModel;
import com.largeproject.figmadesigns.viewmodel.BaseViewModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;
    private BaseViewModel viewModel;
    NumberFormat formatter = new DecimalFormat("##.###");


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BaseViewModel.class);
        setObserver();
        callAPI();
    }

    private void callAPI() {
        Thread thread = new Thread(() -> viewModel.getCoins("BTC,DERO,BTX,CASH2"));

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.start();
    }

    private void setObserver() {
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<CoinModel>>() {
            @Override
            public void onChanged(List<CoinModel> coinModels) {
                if (coinModels != null && coinModels.size() > 0) {
                    getCoinsData(coinModels);
                }
            }
        });
    }

    public void getCoinsData(List<CoinModel> coin) {
        binding.coinTextView1.setText(coin.get(0).getCoin());
        binding.coinTextView2.setText(coin.get(1).getCoin());
        binding.coinTextView3.setText(coin.get(2).getCoin());
        binding.coinTextView4.setText(coin.get(3).getCoin());

        binding.coinLongTextView1.setText(coin.get(0).getName());
        binding.coinLongTextView2.setText(coin.get(1).getName());
        binding.coinLongTextView3.setText(coin.get(2).getName());
        binding.coinLongTextView4.setText(coin.get(3).getName());

        binding.coinBottomPrice1.setText(String.valueOf("$" + formatter.format(coin.get(0).getPrice())));
        binding.coinBottomPrice2.setText(String.valueOf("$" + formatter.format(coin.get(1).getPrice())));
        binding.coinBottomPrice3.setText(String.valueOf("$" + formatter.format(coin.get(2).getPrice())));
        binding.coinBottomPrice4.setText(String.valueOf("$" + formatter.format(coin.get(3).getPrice())));

        binding.coinPriceText.setText(String.valueOf("$" + formatter.format(coin.get(0).getPrice())));
        binding.coinPriceText2.setText(String.valueOf("$" + formatter.format(coin.get(1).getPrice())));
        binding.coinPriceText3.setText(String.valueOf("$" + formatter.format(coin.get(2).getPrice())));
        binding.coinPriceText4.setText(String.valueOf("$" + formatter.format(coin.get(3).getPrice())));

    }
}
