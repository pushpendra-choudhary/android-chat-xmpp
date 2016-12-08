package com.example.crypsis.mchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by crypsis on 2/12/16.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    Context mContext;
    ArrayList<ChatMessage> mChatList;


    public ChatAdapter(Context context, ArrayList<ChatMessage> msgList){
        mContext = context;
        mChatList = msgList;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbubble,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        ChatMessage message = mChatList .get(position);

        holder.mBubbleTextView.setText(message.getBody());

        if(message.isMine){
            holder.mBubbleParentLinearLayout.setGravity(Gravity.RIGHT);
            holder.mBubbleLinearLayout.setBackgroundResource(R.drawable.bubble2);
        }else{
            holder.mBubbleLinearLayout.setBackgroundResource(R.drawable.bubble1);
            holder.mBubbleParentLinearLayout.setGravity(Gravity.LEFT);
        }
    }

    @Override
    public int getItemCount() {
        return mChatList.size();
    }

    public void add(ChatMessage chatMessage) {
        mChatList.add(chatMessage);
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bubble_layout_parent) LinearLayout mBubbleParentLinearLayout;
        @BindView(R.id.bubble_layout) LinearLayout mBubbleLinearLayout;
        @BindView(R.id.message_text) TextView mBubbleTextView;
        public ChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
