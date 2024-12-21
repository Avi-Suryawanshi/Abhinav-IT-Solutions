package com.avi.abhinavtask

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.avi.abhinavtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: MemberDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = MemberDatabaseHelper(this)

        binding.rvMembers.layoutManager = LinearLayoutManager(this)
        loadMembers()

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddMemberActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadMembers()
    }

    private fun loadMembers() {
        val members = databaseHelper.getAllMembers()
        if (members.isNotEmpty()) {
            binding.tvNoData.visibility = View.GONE
            binding.rvMembers.visibility = View.VISIBLE
            binding.rvMembers.adapter = MemberAdapter(members)
        } else {
            binding.tvNoData.visibility = View.VISIBLE
            binding.rvMembers.visibility = View.GONE
        }
    }

}