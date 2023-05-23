package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mandiri.simpleviewmodel.R
import com.mandiri.simpleviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var calcVM: CalcVM

    private lateinit var normalCalcFragment: NormalCalcFragment
    private lateinit var scientificCalcFragment: ScientificCalcFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        normalCalcFragment = NormalCalcFragment()
        scientificCalcFragment = ScientificCalcFragment()

        binding.apply {
            btnNormal.setOnClickListener {
                fragmentCalls(normalCalcFragment)
            }
            btnScientific.setOnClickListener {
                fragmentCalls(scientificCalcFragment)
            }
        }

        calcVM = ViewModelProvider(this)[CalcVM::class.java]

        calcVM.input.observe(this, Observer {input->
            calcVM.output.observe(this, Observer{output->
                binding.apply {
                    tvInput.text = input
                    tvOuput.text = output
                }
            })
        })
    }

    private fun fragmentCalls(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit()
    }
}