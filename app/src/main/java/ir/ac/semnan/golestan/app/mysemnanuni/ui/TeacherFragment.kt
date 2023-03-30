package ir.ac.semnan.golestan.app.mysemnanuni.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ac.semnan.golestan.app.mysemnanuni.R
import ir.ac.semnan.golestan.app.mysemnanuni.databinding.FragmentTeacherBinding


class TeacherFragment : Fragment() {
    private var _Binding:FragmentTeacherBinding?=null
    private val bidning get() = _Binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _Binding= FragmentTeacherBinding.inflate(layoutInflater)
        return bidning.root
    }

}