package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mandiri.simpleviewmodel.R
import com.mandiri.simpleviewmodel.databinding.FragmentNormalCalcBinding
import com.mandiri.simpleviewmodel.databinding.FragmentScientificCalcBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class ScientificCalcFragment : Fragment() {

    private lateinit var binding: FragmentScientificCalcBinding
    private lateinit var calcVM: CalcVM

    private var input : String = ""
    private var output : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calcVM = ViewModelProvider(requireActivity())[CalcVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScientificCalcBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input = calcVM.input.value.toString()
        output = calcVM.output.value.toString()

        binding.apply {
            clearAll.setOnClickListener{
                calcVM.setInput("")
                calcVM.output.value = "0"
                input  = ""
                output = "0"

                Toast.makeText(requireContext(), "test", Toast.LENGTH_SHORT).show()
            }

            backSpace.setOnClickListener{
                if(input.isNotEmpty()){
                    calcVM.input.value = input.subSequence(0, input.length-1).toString()
                    input = calcVM.input.value.toString()
                }
            }

            openingBracket.setOnClickListener {
                input = addToInputText("(")
                calcVM.input.value = input
            }
            closingBracket.setOnClickListener {
                input = addToInputText(")")
                calcVM.input.value = input
            }
            btn0.setOnClickListener {
                input = addToInputText("0")
                calcVM.input.value = input
            }
            btn1.setOnClickListener {
                input = addToInputText("1")
                calcVM.input.value = input
            }
            btn2.setOnClickListener {
                input = addToInputText("2")
                calcVM.input.value = input
            }
            btn3.setOnClickListener {
                input = addToInputText("3")
                calcVM.input.value = input
            }
            btn4.setOnClickListener {
                input = addToInputText("4")
                calcVM.input.value = input
            }
            btn5.setOnClickListener {
                input = addToInputText("5")
                calcVM.input.value = input
            }
            btn6.setOnClickListener {
                input = addToInputText("6")
                calcVM.input.value = input
            }
            btn7.setOnClickListener {
                input = addToInputText("7")
                calcVM.input.value = input
            }
            btn8.setOnClickListener {
                input = addToInputText("8")
                calcVM.input.value = input
            }
            btn9.setOnClickListener {
                input = addToInputText("9")
                calcVM.input.value = input
            }
            decimal.setOnClickListener {
                input = addToInputText(".")
                calcVM.input.value = input
            }
            devide.setOnClickListener {
                input = addToInputText("/")
                calcVM.input.value = input
            }
            multiple.setOnClickListener {
                input = addToInputText("*")
                calcVM.input.value = input
            }
            plus.setOnClickListener {
                input = addToInputText("+")
                calcVM.input.value = input
            }
            minus.setOnClickListener {
                input = addToInputText("-")
                calcVM.input.value = input
            }
            equal.setOnClickListener {
                showResult()
            }

            btnCos.setOnClickListener {
                input = addToInputText("cos")
                calcVM.input.value = input
            }

            btnSin.setOnClickListener {
                input = addToInputText("sin")
                calcVM.input.value = input
            }

            btnTan.setOnClickListener {
                input = addToInputText("tan")
                calcVM.input.value = input
            }

            pangkat.setOnClickListener {
                input = addToInputText("^")
                calcVM.input.value = input
            }
        }

    }

    private fun showResult() {
        try {
            val result = Expression(calcVM.input.value).calculate()
            if(result.isNaN()){
                output = "Error"
                calcVM.output.value = output
                Toast.makeText(requireContext(), "23", Toast.LENGTH_SHORT).show()
            } else {
                output = DecimalFormat("0.#######").format(result)
                calcVM.output.value = output
                Toast.makeText(requireContext(), "87", Toast.LENGTH_SHORT).show()
            }
        } catch (e : Exception){
            output = "Error"
            Toast.makeText(requireContext(), "testtt", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addToInputText(buttonValue:String) : String {
        var string = ""
        if(buttonValue.length > 1){
            string = "$input$buttonValue("
        } else if(buttonValue.length == 1) {
            string = "$input$buttonValue"
        }
        return string
    }

}