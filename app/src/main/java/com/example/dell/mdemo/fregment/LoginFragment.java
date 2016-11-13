package com.example.dell.mdemo.fregment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.mdemo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {


    // UI references.
    private AutoCompleteTextView mEmailView_login;
    private EditText mPasswordView_login;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    String email;
    String password;

    private Boolean exit = false;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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





//It will run once login successfull
        //firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("nlogin1", "onAuthStateChanged:signed_in:" + user.getUid());

                    Toast.makeText(getActivity(),"Login Successful....",
                            Toast.LENGTH_SHORT).show();
                    android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    WeddingDetailFragment fragment = new WeddingDetailFragment();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.addToBackStack("f2");
                    fragmentTransaction.commit();

                    //Intent i4=new Intent(LoginActivity.this,Profile_Activity.class);
                    //startActivity(i4);

                } else {

                    // Toast.makeText(LoginActivity.this,"Invalid user....",Toast.LENGTH_LONG).show();
                    // User is signed out
                    Log.d("nlogin2", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };






    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        //some code
        FrameLayout fl = (FrameLayout) inflater.inflate(R.layout.fragment_login, container, false);
        //some code


        // Set up the login form.
     mEmailView_login = (AutoCompleteTextView)fl.findViewById(R.id.et_email_on_login_fragment);
        populateAutoComplete();
        mPasswordView_login = (EditText)fl.findViewById(R.id.et_password_on_login_fragment);











        mPasswordView_login.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {



                    //attemptLogin();
                    return true;
                }
                return false;
            }
        });


        //sign in
        Button mEmailSignInButton = (Button)fl.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                verify_inputs();

                // attemptLogin();
            }
        });


        //sign up
        Button mgo_to_signup_page=(Button)fl.findViewById(R.id.botton_go_to_signup_page);
        mgo_to_signup_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                SignupFragment fragment = new SignupFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack("f2");
                fragmentTransaction.commit();
                //Intent i1=new Intent(LoginActivity.this,SignupActivity.class);
                //startActivity(i1);
            }
        });

        //make card

        Button mmake_card=(Button)fl.findViewById(R.id.botton_make_card_on_login_fragment);
        mmake_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                WeddingDetailFragment fragment = new WeddingDetailFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack("f2");
                fragmentTransaction.commit();
                //  Intent i1=new Intent(LoginActivity.this,WeddingDetailActivity.class);
                //  startActivity(i1);
            }
        });
        return fl;


    } // end onCreateView




    //close app

    public void attemptLogin1()
    {

        //String login_email= mEmailView_login.getText().toString();
        //String login_pass= mPasswordView_login.getText().toString();
        Log.d("nlogin10", "input data" + email+""+password);

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("nlogin3", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("nlogin4", "signInWithEmail:failed", task.getException());
                            Toast.makeText(getActivity(), "Invalid Email or Password",
                                    Toast.LENGTH_SHORT).show();

                            android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
                            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                            LoginFragment fragment = new LoginFragment();
                            fragmentTransaction.replace(R.id.fragment_container, fragment);
                            fragmentTransaction.addToBackStack("f5");
                            fragmentTransaction.commit();
                           // Intent i11=new Intent(LoginActivity.this,LoginActivity.class);
                           // startActivity(i11);
                        }

                        // ...
                    }
                });

    }



    private void verify_inputs() {
       /* if (mAuthTask != null)
        {
            return;
        } */

        // Reset errors.
        mEmailView_login.setError(null);
        mPasswordView_login.setError(null);

        // Store values at the time of the login attempt.
        email = mEmailView_login.getText().toString();
        password = mPasswordView_login.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password))
        {
            mPasswordView_login.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView_login;
            cancel = true;
        }

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
            attemptLogin1();

            //mAuthTask = new UserLoginTask(email, password);
            // mAuthTask.execute((Void) null);
        }
    }//end verify inputs


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }



    private void populateAutoComplete() {

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



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
