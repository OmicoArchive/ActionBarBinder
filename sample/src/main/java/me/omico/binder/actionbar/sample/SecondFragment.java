package me.omico.binder.actionbar.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import me.omico.binder.actionbar.sample.databinding.SampleFragmentBinding;
import me.omico.fragment.ActionBarFragment;

/**
 * For Java user, we also could use ActionBarFragment.
 */
@SuppressLint("SetTextI18n")
public class SecondFragment extends ActionBarFragment {

    public SecondFragment() {
        super(R.layout.sample_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SampleFragmentBinding binding = SampleFragmentBinding.bind(view);
        bindToolbar(binding.topAppBar);
        binding.toolbarLayout.setTitle("Second");
        binding.text.setText(binding.topAppBar.toString());
        binding.button.setText("Second To First");
        binding.button.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_first));
        Log.d("SecondFragment", "ActionBar is showing: " + getActionBar().isShowing());
    }
}
