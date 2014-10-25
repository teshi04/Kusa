package tsur.jp.kusa;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditKusaFragment extends DialogFragment {


    MainActivityListener mListener;

    @InjectView(R.id.amount_text)
    EditText mAmountText;
    @InjectView(R.id.length_text)
    EditText mLengthText;
    @InjectView(R.id.color_text)
    EditText mColorText;

    public static EditKusaFragment newInstance() {
        EditKusaFragment f = new EditKusaFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (MainActivityListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.NoTitleDialog); // no title
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Dialog dialog = getDialog();

        assert dialog != null;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        lp.width = (int) (metrics.widthPixels * 0.9);
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_kusa, container);
        ButterKnife.inject(this, view);

        return view;
    }

    @OnClick(R.id.create_kusa)
    void createKusa() {
        String amount = mAmountText.getText().toString();
        String length = mLengthText.getText().toString();
        String color = mColorText.getText().toString();
        mListener.createdKusa(amount, length, color);
        dismiss();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}