package com.mfq.foodle.moofi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfq.foodle.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {


    private final TextView mChatText;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        mChatText = itemView.findViewById(R.id.chatText);
    }

    public void bind(Assistant assistant) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mChatText.setText(assistant.getChatText());
        if (assistant.getFrom() == Assistant.FROM_ASSISTANT) {
            mChatText.setBackgroundResource(R.drawable.assistant_chat_shape);
            params.gravity = Gravity.START;
            mChatText.setLayoutParams(params);

        } else {
            mChatText.setBackgroundResource(R.drawable.user_chat_shape);
            params.gravity = Gravity.END;
            mChatText.setLayoutParams(params);

        }
    }


}