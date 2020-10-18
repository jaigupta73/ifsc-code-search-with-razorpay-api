package com.bankinfo.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bankinfo.databinding.DetailsFragmentBinding
import com.bankinfo.networkcall.api.ApiHelper
import com.bankinfo.networkcall.api.RetrofitBuilder
import com.bankinfo.networkcall.models.ifscinfoModel.IfscResponse
import com.bankinfo.ui.base.ViewModelFactory
import com.bankinfo.ui.main.viewmodel.MainViewModel

class DetailsFragment : Fragment() {
    var detailsFragmentBinding: DetailsFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailsFragmentBinding = DetailsFragmentBinding.inflate(inflater, container, false)
        return detailsFragmentBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        displayBankDetails(arguments?.getSerializable("detailsFragmentArgs") as IfscResponse)
    }


    private fun displayBankDetails(ifscResponse: IfscResponse) {
        detailsFragmentBinding?.ifscCodeTxt?.text = ifscResponse.IFSC
        detailsFragmentBinding?.addressTxt?.text = ifscResponse.ADDRESS
        detailsFragmentBinding?.stateTxt?.text = ifscResponse.STATE
        detailsFragmentBinding?.branchTxt?.text = ifscResponse.BRANCH

        detailsFragmentBinding?.micrCodeTxt?.text = ifscResponse.MICR
        detailsFragmentBinding?.cityTxt?.text = ifscResponse.CITY
        detailsFragmentBinding?.contactTxt?.text = ifscResponse.CONTACT
    }

}