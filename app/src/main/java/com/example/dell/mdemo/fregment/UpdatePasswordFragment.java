package com.example.dell.mdemo.fregment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dell.mdemo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdatePasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdatePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdatePasswordFragment extends Fragment {

    public Button button_update;
    String email;

    private AutoCompleteTextView mEmailView_login;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UpdatePasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdatePasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdatePasswordFragment newInstance(String param1, String param2) {
        UpdatePasswordFragment fragment = new UpdatePasswordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final FrameLayout fl = (FrameLayout) inflater.inflate(R.layout.fragment_update_password, container, false);

        mEmailView_login = (AutoCompleteTextView)fl.findViewById(R.id.et_email_on_update_password);
        populateAutoComplete();

        button_update=(Button)fl.findViewById(R.id.botton_update_pass);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify_inputs();
            }
        });

        return fl;
    }// end On createView


    private void verify_inputs() {
       /* if (mAuthTask != null)
        {
            return;
        } */

        // Reset errors.
        mEmailView_login.setError(null);

        // Store values at the time of the login attempt.
        email = mEmailView_login.getText().toString();

        boolean cancel = false;
        View focusView = null;



        // Check for a valid email address.
        if (TextUtils.isEmpty(email))
        {
            mEmailView_login.setError(getString(R.string.error_field_required));
            focusView = mEmailView_login;
            cancel = true;
        } else if (!isEmailValid(email))
        {
            mEmailView_login.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView_login;
            cancel = true;
        }

        if (cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else
        {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            sent_Email();

            //mAuthTask = new UserLoginTask(email, password);
            // mAuthTask.execute((Void) null);
        }
    }//end verify inputs




    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }


    private void populateAutoComplete() {

    }




    public void sent_Email()

    {
        FirebaseAuth auth = FirebaseAuth.getInstance();




        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),"Email sent..",Toast.LENGTH_LONG).show();
                            Log.d("nemil", "Email sent.");

                        }
                    }
                });





        /*
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Log.d("new password",new_password+"1");
        if (user != null) {
            user.updatePassword(new_password)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {


                                Toast.makeText(getActivity(),"Password updated successfuly..",Toast.LENGTH_LONG).show();
                                Log.d("nikhil_new_pass", "User password updated.");

                                android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
                                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                                WeddingDetailFragment fragment = new WeddingDetailFragment();
                                fragmentTransaction.replace(R.id.fragment_container, fragment);
                                fragmentTransaction.addToBackStack("f7");
                                fragmentTransaction.commit();
                            }
                        }
                    });
        }  */
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
