package com.largeproject.figmadesigns.view;

import androidx.fragment.app.Fragment;

import com.largeproject.figmadesigns.viewmodel.BaseViewModel;

import java.text.NumberFormat;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    BaseViewModel viewModel;
    NumberFormat formatter = new DecimalFormat("##.##");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BaseViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();
        viewModel.getCoins();
        Observer<List<CoinsResponseModel>> listObserver = new Observer<List<CoinsResponseModel>>() {
            @Override
            public void onChanged(List<CoinsResponseModel> coinsResponseModels) {
                getCoinsData(coinsResponseModels);
            }
        };
        viewModel.getLiveData().observe(getViewLifecycleOwner(), listObserver);
        return view;
    }


    public void getCoinsData(List<CoinsResponseModel> coinsResponseModels) {
        binding.coinTextView1.setText(coinsResponseModels.get(0).getCoin());
        binding.coinTextView2.setText(coinsResponseModels.get(1).getCoin());
        binding.coinTextView3.setText(coinsResponseModels.get(2).getCoin());
        binding.coinTextView4.setText(coinsResponseModels.get(3).getCoin());

        binding.coinLongTextView1.setText(coinsResponseModels.get(0).getName());
        binding.coinLongTextView2.setText(coinsResponseModels.get(1).getName());
        binding.coinLongTextView3.setText(coinsResponseModels.get(2).getName());
        binding.coinLongTextView4.setText(coinsResponseModels.get(3).getName());

        binding.coinBottomPrice1.setText(String.valueOf("$" + formatter.format(coinsResponseModels.get(0).getPrice())));
        binding.coinBottomPrice2.setText(String.valueOf("$" + formatter.format(coinsResponseModels.get(1).getPrice())));
        binding.coinBottomPrice3.setText(String.valueOf("$" + formatter.format(coinsResponseModels.get(2).getPrice())));
        binding.coinBottomPrice4.setText(String.valueOf("$" + formatter.format(coinsResponseModels.get(3).getPrice())));

        binding.coinPriceText.setText(String.valueOf("$" + formatter.format(coinsResponseModels.get(0).getPrice())));
        binding.coinPriceText2.setText(String.valueOf("$" + formatter.format(coinsResponseModels.get(1).getPrice())));
        binding.coinPriceText3.setText(String.valueOf("$" + formatter.format(coinsResponseModels.get(2).getPrice())));
        binding.coinPriceText4.setText(String.valueOf("$" + formatter.format(coinsResponseModels.get(3).getPrice())));

    }
}
