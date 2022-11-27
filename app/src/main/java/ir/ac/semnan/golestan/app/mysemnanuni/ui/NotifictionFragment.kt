package ir.ac.semnan.golestan.app.mysemnanuni.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ir.ac.semnan.golestan.app.mysemnanuni.R
import ir.ac.semnan.golestan.app.mysemnanuni.databinding.FragmentNotifictionBinding


class NotifictionFragment : Fragment() {

    private lateinit var navController:NavController

    private var _bindig:FragmentNotifictionBinding?=null
    private val binding get() =_bindig!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)

        doClick()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bindig= FragmentNotifictionBinding.inflate(inflater,container,false)
        return binding.root




    }

    private fun doClick(){
        binding.cardScore.setOnClickListener {
            val action =NotifictionFragmentDirections.actionNotifictionFragmentToScoreDetailFragment()
            navController.navigate(action)
        }
    }

}