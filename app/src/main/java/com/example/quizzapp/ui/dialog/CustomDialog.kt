package com.example.quizzapp.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.quizzapp.R
import com.example.quizzapp.databinding.LayoutCustomDialogBinding
import com.example.quizzapp.databinding.LayoutItemQuestioncategoryBinding

class CustomDialog : DialogFragment() {

    private var _binding: LayoutCustomDialogBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_IS_ANSWER_CORRECT = "is_answer_correct"
        fun newInstance(isAnswerCorrect: Boolean): CustomDialog {
            val dialog = CustomDialog()
            val args = Bundle().apply {
                putBoolean(ARG_IS_ANSWER_CORRECT, isAnswerCorrect)
            }
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = LayoutCustomDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val dialogView = binding.root

        val isAnswerCorrect = arguments?.getBoolean(ARG_IS_ANSWER_CORRECT) ?: false
        bindView(isAnswerCorrect)

        binding.btnChecked.setOnClickListener {
            dismiss()
        }

        return AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()
    }

    private fun bindView(answerCorrect: Boolean) {
        val imageResId = if (answerCorrect) {
            R.drawable.checked
        } else {
            R.drawable.no
        }
        binding.circleImageView.setImageResource(imageResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}