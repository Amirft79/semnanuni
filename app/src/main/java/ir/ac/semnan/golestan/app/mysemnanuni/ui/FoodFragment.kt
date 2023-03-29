package ir.ac.semnan.golestan.app.mysemnanuni.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ac.semnan.golestan.app.mysemnanuni.R
import ir.ac.semnan.golestan.app.mysemnanuni.databinding.FragmentFoodBinding

class FoodFragment : Fragment() {

    private var _binding:FragmentFoodBinding?=null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentFoodBinding.inflate(layoutInflater)
        return binding.root
    }

}