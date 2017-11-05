package com.example.tiago.establishmentexample.diagoFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.contato.BrPhoneNumberFormatter;
import com.example.tiago.establishmentexample.contato.PresenterContato;
import com.example.tiago.establishmentexample.domain.Contato;
import com.example.tiago.establishmentexample.product.MVP;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;


/**
 * Created by tiago on 17/11/2016.
 */

public class DialogOrcamento extends android.support.v4.app.DialogFragment implements MVP.ViewContato{
    private EditText edtName, edtPhone, edtEmail, edtDescription;

    private TextInputLayout tilName, tilPhone, tilEmail, tilDescription;
    private Button btnConfirm, btnCancel;



    private MVP.PresenterContato presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new PresenterContato();
        presenter.setView(this);


    }


    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dialog_orcamento, container, false);

        edtName = (EditText)layout.findViewById(R.id.edt_name);
        edtPhone = (EditText)layout.findViewById(R.id.edt_phone);
        edtEmail = (EditText)layout.findViewById(R.id.edt_email);
        edtDescription = (EditText)layout.findViewById(R.id.edt_description);
        tilName = (TextInputLayout) layout.findViewById(R.id.til_name);
        tilEmail = (TextInputLayout) layout.findViewById(R.id.til_email);
        tilPhone = (TextInputLayout) layout.findViewById(R.id.til_phone);
        tilDescription = (TextInputLayout) layout.findViewById(R.id.til_description);



        btnConfirm= (Button) layout.findViewById(R.id.btn_confirm);
        btnCancel= (Button) layout.findViewById(R.id.btn_cancel);


        MaskEditTextChangedListener maskTEL = new MaskEditTextChangedListener("(##)#####-####", edtPhone);
        edtPhone.addTextChangedListener(maskTEL);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verifyErrors()){

                    Contato contato = new Contato();
                    contato.name = edtName.getText().toString();
                    contato.email = edtEmail.getText().toString();
                    contato.phoneNumber = edtPhone.getText().toString();
                    contato.description = edtDescription.getText().toString();
                    presenter.postContato(contato);
                }
            }
        });



        return layout;
    }

    private boolean verifyErrors(){
        if(isEmpty(edtName)){
            edtName.setError("Campo nome deve ser preenchido");
            return false;
        }else  if(isEmpty(edtEmail)){
            edtEmail.setError("Campo email deve ser preenchido");
            return false;
        }else if(isEmpty(edtPhone)){
            edtPhone.setError("Campo phone deve ser preenchido");
            return false;
        }
        else if(isEmpty(edtDescription)){
            edtDescription.setError("Campo mensagem deve ser preenchido");
            return false;
        }else if(! isEmailValid(edtEmail.getText().toString())){
            edtEmail.setError("Favor inserir um email válido");
            return false;
        } else{
            return  true;
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }



}
