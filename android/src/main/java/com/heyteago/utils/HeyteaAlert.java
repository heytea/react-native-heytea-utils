package com.heyteago.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialog;

public class HeyteaAlert {
    private AppCompatDialog mAppCompatDialog;
    private OnCancelClick mOnCancelClick;
    private OnConfirmClick mOnConfirmClick;
    private Button mBtnCancel;
    private Button mBtnConfirm;
    private TextView mTvTitle;
    private TextView mTvContent;

    public HeyteaAlert(Context context) {
        mAppCompatDialog = new AppCompatDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_heytea_alert, null, false);
        mBtnCancel = view.findViewById(R.id.btn_heytea_dialog_cancel);
        mBtnConfirm = view.findViewById(R.id.btn_heytea_dialog_confirm);
        mTvTitle = view.findViewById(R.id.tv_heytea_dialog_title);
        mTvContent = view.findViewById(R.id.tv_heytea_dialog_content);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCancelClick != null) {
                    mOnCancelClick.onClick(v);
                }
                dismiss();
            }
        });
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnConfirmClick != null) {
                    mOnConfirmClick.onClick(v);
                }
                dismiss();
            }
        });
        mAppCompatDialog.setContentView(view);
    }

    public void setTitle(String title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    public void setContent(String content) {
        if (mTvContent != null) {
            mTvContent.setText(content);
        }
    }

    public void setCancelText(String text) {
        if (mBtnCancel != null) {
            mBtnCancel.setText(text);
        }
    }

    public void setConfirmText(String text) {
        if (mBtnConfirm != null) {
            mBtnConfirm.setText(text);
        }
    }

    public void show() {
        if (TextUtils.isEmpty(mTvTitle.getText().toString())) {
            mTvTitle.setVisibility(View.GONE);
        } else {
            mTvTitle.setVisibility(View.VISIBLE);
        }
        mAppCompatDialog.show();
    }

    public void dismiss() {
        mAppCompatDialog.dismiss();
    }

    public interface OnCancelClick {
        void onClick(View view);
    }

    public interface OnConfirmClick {
        void onClick(View view);
    }

    public void setOnCancelClick(OnCancelClick onCancelClick) {
        mOnCancelClick = onCancelClick;
    }

    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        mOnConfirmClick = onConfirmClick;
    }
}
