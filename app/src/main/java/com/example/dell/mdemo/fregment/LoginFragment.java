package com.example.dell.mdemo.fregment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.mdemo.Profile_Activity;
import com.example.dell.mdemo.R;
import com.example.dell.mdemo.SignupActivity;
import com.example.dell.mdemo.WeddingDetailActivity;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*
        // Set up the login form.
//        mEmailView_login = (AutoCompleteTextView)getView().findViewById(R.id.et_email_on_login_fragment);
        populateAutoComplete();

      //  mPasswordView_login = (EditText) getView().findViewById(R.id.et_password_on_login_fragment);








        //firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("nlogin1", "onAuthStateChanged:signed_in:" + user.getUid());


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
        Button mEmailSignInButton = (Button) getView().findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //verify_inputs();

                // attemptLogin();
            }
        });


        //sign up
        Button mgo_to_signup_page=(Button)getView().findViewById(R.id.botton_go_to_signup_page);
        mgo_to_signup_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i1=new Intent(LoginActivity.this,SignupActivity.class);
                //startActivity(i1);
            }
        });

        //make card

        Button mmake_card=(Button)getView().findViewById(R.id.botton_make_card_on_login_fragment);
        mmake_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                WeddingDetailFragment fragment = new WeddingDetailFragment();
                fragmentTransaction.add(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack("f2");
                fragmentTransaction.commit();
              //  Intent i1=new Intent(LoginActivity.this,WeddingDetailActivity.class);
              //  startActivity(i1);
            }
        });
*/

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);


    } // end onCreateView



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
}
