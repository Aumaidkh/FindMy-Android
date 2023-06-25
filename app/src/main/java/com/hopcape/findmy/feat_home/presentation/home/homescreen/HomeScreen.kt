package com.hopcape.findmy.feat_home.presentation.home.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hopcape.findmy.core.utils.CustomLayoutManager
import com.hopcape.findmy.core.utils.showSnackBar
import com.hopcape.findmy.databinding.FragmentHomescreenBinding
import com.hopcape.findmy.feat_home.domain.getDummyReportedItems
import com.hopcape.findmy.feat_home.presentation.home.homescreen.adapters.LostItemAdapter
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeScreen"
/**
 * This Fragment is going to be the home screen
 * */
@AndroidEntryPoint
class HomeScreen: Fragment() {

    private val binding by lazy {
        FragmentHomescreenBinding.inflate(layoutInflater)
    }

    private val viewModel : HomeScreenViewModel by viewModels()

    private val lostItemsAdapter by lazy {
        LostItemAdapter(
            onClaimClick = {},
            onItemClick = {}
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        consumeFlows()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
    }

    private fun setupRecyclerViews(){
        binding.apply {
            // Lost Items
            rvLostItems.adapter = lostItemsAdapter
            rvLostItems.layoutManager = CustomLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
           // lostItemsAdapter.submitData(getDummyReportedItems())
        }
    }

    private fun consumeFlows(){
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when(state){
                    is HomeScreenViewState.Error -> requireActivity().showSnackBar(state.error)
                    is HomeScreenViewState.Initial -> Unit
                    is HomeScreenViewState.Loading -> Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                    is HomeScreenViewState.Success -> lostItemsAdapter.submitData(state.data)
                }
            }
        }
    }
}