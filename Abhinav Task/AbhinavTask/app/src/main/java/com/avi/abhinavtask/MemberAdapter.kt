package com.avi.abhinavtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(private val members: List<Member>) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMemberName: TextView = itemView.findViewById(R.id.tvName)
        val tvMobileNo: TextView = itemView.findViewById(R.id.tvMobileNo)
        val tvMemberRole: TextView = itemView.findViewById(R.id.tvMemberRole)
        val tvSubscriptionAmt: TextView = itemView.findViewById(R.id.tvSubscriptionAmt)
        val btnLoanAmount: Button = itemView.findViewById(R.id.btnLoanAmount)
        val btnDepositAmount: Button = itemView.findViewById(R.id.btnDepositAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_member, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = members[position]
        holder.tvMemberName.text = "Member Name: ${member.name}"
        holder.tvMobileNo.text = "Mobile No: ${member.mobile}"
        holder.tvMemberRole.text = "Member Role: ${member.role}"
        holder.tvSubscriptionAmt.text = "Subscription Amt: ${member.subscriptionFee}"
        holder.btnLoanAmount.text = "Loan Amount: ${member.loanAmount}"
        holder.btnDepositAmount.text = "Deposit Amount: ${member.depositAmount}"

    }

    override fun getItemCount(): Int = members.size
}
