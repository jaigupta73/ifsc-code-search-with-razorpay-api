package com.bankinfo.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bankinfo.R
import com.bankinfo.apiutils.ApiResponseStatus
import com.bankinfo.databinding.MainFragmentBinding
import com.bankinfo.networkcall.api.ApiHelper
import com.bankinfo.networkcall.api.RetrofitBuilder
import com.bankinfo.networkcall.models.ifscinfoModel.IfscResponse
import com.bankinfo.ui.base.ViewModelFactory
import com.bankinfo.ui.main.viewmodel.MainViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern


class MainFragment : Fragment(), SearchView.OnQueryTextListener {
    var binding: MainFragmentBinding? = null
    private lateinit var viewModel: MainViewModel
    private lateinit var ifscResponse: IfscResponse
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.ifscCodeTxt?.setOnClickListener {
            val bundle = bundleOf(
                "detailsFragmentArgs" to ifscResponse
            )
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment,bundle)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService), requireActivity().application)
        ).get(MainViewModel::class.java)

    }


    private fun findAndSaveBankInfo(serachText: String) {
        viewModel.getBankData(serachText).observe(this, Observer {
            it?.let { resource ->
                when (resource.apiResponseStatus) {
                    ApiResponseStatus.SUCCESS -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.ifscCodeTxt?.visibility = View.VISIBLE
                        binding?.ifscCodeTxt?.text =
                            resource.data?.IFSC + "\n" + resource.data?.ADDRESS
                        ifscResponse = resource?.data!!

                    }
                    ApiResponseStatus.ERROR -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.ifscCodeTxt?.visibility = View.VISIBLE
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                    }
                    ApiResponseStatus.LOADING -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.ifscCodeTxt?.visibility = View.GONE
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_main, menu)
        val searchItem: MenuItem = menu.findItem(R.id.app_bar_search)
        val searchView: androidx.appcompat.widget.SearchView =
            searchItem.actionView as androidx.appcompat.widget.SearchView
        val et = searchView.findViewById<View>(R.id.search_src_text) as TextView
        et.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(11))
        searchView.imeOptions = EditorInfo.IME_ACTION_SEARCH
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    fun isIfscCodeValid(ifscCode: String): Boolean {
        var regExp = "^[A-Za-z]{4}0[0-9]{6}$"
        val pattern = Pattern.compile(regExp)
        var matcher: Matcher? = null
        matcher = pattern.matcher(ifscCode)
        return matcher!!.matches()
    }

    override fun onQueryTextSubmit(text: String?): Boolean {

        if (isIfscCodeValid(text!!)) {
            findAndSaveBankInfo(text)
        } else {
            Toast.makeText(requireActivity(), "Please enter valid IFSC code", Toast.LENGTH_LONG)
                .show()
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }


}