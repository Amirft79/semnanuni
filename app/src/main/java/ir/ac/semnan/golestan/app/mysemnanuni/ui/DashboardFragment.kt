package ir.ac.semnan.golestan.app.mysemnanuni.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ir.ac.semnan.golestan.app.mysemnanuni.R
import ir.ac.semnan.golestan.app.mysemnanuni.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private var _binding:FragmentDashboardBinding?=null
    private val binding get() = _binding!!

    private lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentDashboardBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
       doClicks()
    }

    private fun doClicks(){
        binding.card6.setOnClickListener {
            val action=DashboardFragmentDirections.actionDashboardFragmentToFoodFragment()
            navController.navigate(action)
        }
        binding.card7.setOnClickListener {
            val action=DashboardFragmentDirections.actionDashboardFragmentToMapFragment()
            navController.navigate(action)
        }
        binding.cardOne.setOnClickListener {
            val action=DashboardFragmentDirections.actionDashboardFragmentToTeacherFragment()
            navController.navigate(action)
        }


    }
}