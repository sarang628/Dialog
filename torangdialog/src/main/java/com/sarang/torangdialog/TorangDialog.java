package com.sarang.torangdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sarang.torangdialog.databinding.LayoutDialogBinding;

public class TorangDialog extends Dialog {
    private String message;
    private OnClickListener onClickListener;
    private String positiveBtnTitle;
    private LayoutDialogBinding mBinding;

    protected TorangDialog(@NonNull Context context) {
        super(context);
        mBinding = LayoutDialogBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCancelable(false);
    }

    @Override
    public void show() {
        super.show();

        if (message != null)
            mBinding.msg.setText(message);

        if (positiveBtnTitle != null)
            mBinding.btnConfirm.setText(positiveBtnTitle);

        if (onClickListener != null)
            mBinding.btnConfirm.setOnClickListener(v -> {
                onClickListener.onClick(new DialogInterface() {
                    @Override
                    public void cancel() {
                    }

                    @Override
                    public void dismiss() {
                    }
                }, DialogInterface.BUTTON_POSITIVE);
                dismiss();
            });
    }

    public static class Builder {
        Context context;
        String message;
        OnClickListener onClickListener;
        String positiveBtnTitle;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveButton(String title, OnClickListener onClickListener) {
            positiveBtnTitle = title;
            this.onClickListener = onClickListener;
            return this;
        }

        public TorangDialog build() {
            TorangDialog torangDialog = new TorangDialog(context);
            if (message != null)
                torangDialog.message = message;

            if (positiveBtnTitle != null)
                torangDialog.positiveBtnTitle = positiveBtnTitle;

            if (onClickListener != null)
                torangDialog.onClickListener = onClickListener;

            return torangDialog;
        }
    }
}
