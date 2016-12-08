package com.example.crypsis.mchat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Chat extends Fragment {

    @BindView(R.id.messageEditText) EditText mMsgEditText;
    @BindView(R.id.msgListView) RecyclerView mMsgRecyclerView;
    public static ChatAdapter mChatAdapter;


    private String user1 = "khushi", user2 = "khushi";
    private Random random;
    public static ArrayList<ChatMessage> chatlist;
    private LinearLayoutManager layoutManager;

    public Chat() {
    }


    public static Chat newInstance() {
        Chat fragment = new Chat();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this,view);

        random = new Random();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(
                "Chats");


        chatlist = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true); // layoutManager.setReverseLayout(true);
        mMsgRecyclerView.setLayoutManager(layoutManager);
        mChatAdapter = new ChatAdapter(getActivity(),chatlist);
        mMsgRecyclerView.setAdapter(mChatAdapter);


        return view;
    }

    public void sendTextMessage(View v) {
        String message = mMsgEditText.getEditableText().toString();
        if (!message.equalsIgnoreCase("")) {
            final ChatMessage chatMessage = new ChatMessage(user1, user2,
                    message, "" + random.nextInt(1000), true);
            chatMessage.setMsgID();
            chatMessage.body = message;
            chatMessage.Date = CommonMethod.getCurrentDate();
            chatMessage.Time = CommonMethod.getCurrentTime();
            mMsgEditText.setText("");
            mChatAdapter.add(chatMessage);
            mChatAdapter.notifyDataSetChanged();
            mMsgRecyclerView.smoothScrollToPosition(mChatAdapter.getItemCount());

            MainActivity activity = ((MainActivity) getActivity());
            activity.getmService().xmpp.sendMessage(chatMessage);
        }
    }

    @OnClick(R.id.sendMessageButton)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendMessageButton:
                sendTextMessage(v);
                break;
            default:
                break;
        }
    }



  /*  // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
