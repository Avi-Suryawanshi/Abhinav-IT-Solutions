package com.avi.abhinavtask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.avi.abhinavtask.databinding.ActivityAddMemberBinding

class AddMemberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMemberBinding
    private lateinit var databaseHelper: MemberDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llBackButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        databaseHelper = MemberDatabaseHelper(this)

        binding.btnSubmit.setOnClickListener {
            val name = binding.etMemberName.text.toString().trim()
            val mobile = binding.etMobile.text.toString().trim()
            val role = if (binding.rbSecretary.isChecked) "Secretary" else "Member"
            val gender = when {
                binding.rbMale.isChecked -> "Male"
                binding.rbFemale.isChecked -> "Female"
                else -> "Other"
            }
            val dob = binding.etDOB.text.toString().trim()
            val doj = binding.etDOJ.text.toString().trim()
            val subscriptionFee = binding.etSubscriptionFee.text.toString().trim().toDoubleOrNull() ?: 0.0
            val depositAmount = binding.etDepositAmount.text.toString().trim().toDoubleOrNull() ?: 0.0
            val loanAmount = binding.etLoanAmount.text.toString().trim().toDoubleOrNull() ?: 0.0
            val maritalStatus = if (binding.rbMarried.isChecked) "Married" else "Unmarried"
            val dateOfMarriage = binding.etDateOfMarriage.text.toString().trim()
            val caste = binding.etCaste.text.toString().trim()
            val religion = binding.etReligion.text.toString().trim()
            val category = binding.etCategory.text.toString().trim()
            val aadhar = binding.etAadhar.text.toString().trim()

            if (name.isEmpty() || mobile.isEmpty() || dob.isEmpty() || doj.isEmpty() || aadhar.isEmpty()) {
                Toast.makeText(this, "Please fill all mandatory fields", Toast.LENGTH_SHORT).show()
            } else {
                val result = databaseHelper.insertMember(
                    name, mobile, role, gender, dob, doj, subscriptionFee, depositAmount, loanAmount,
                    maritalStatus, if (maritalStatus == "Married") dateOfMarriage else null,
                    caste, religion, category, aadhar
                )

                if (result != -1L) {
                    Toast.makeText(this, "Member added successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed to add member", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
