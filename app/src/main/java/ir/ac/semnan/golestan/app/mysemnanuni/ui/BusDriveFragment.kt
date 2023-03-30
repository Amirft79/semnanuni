package ir.ac.semnan.golestan.app.mysemnanuni.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ac.semnan.golestan.app.mysemnanuni.R
import ir.ac.semnan.golestan.app.mysemnanuni.databinding.FragmentBusDriveBinding


class BusDriveFragment : Fragment() {


    private var _Bidning:FragmentBusDriveBinding?=null
    private val binding get() = _Bidning!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _Bidning= FragmentBusDriveBinding.inflate(layoutInflater)
        return binding.root
    }

}