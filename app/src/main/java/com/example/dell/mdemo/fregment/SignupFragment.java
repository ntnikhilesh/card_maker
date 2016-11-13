package com.example.dell.mdemo.fregment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dell.mdemo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {


    String sigup_email;
    String signup_pass;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button signup_button , button_goto_login;

    // UI references.
    private AutoCompleteTextView mEmailView_signup;
    private EditText mPasswordView_signup;





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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

        // Inflate the layout for this fragment
        //some code
        FrameLayout fl = (FrameLayout) inflater.inflate(R.layout.fragment_signup, container, false);




        mEmailView_signup = (AutoCompleteTextView)fl.findViewById(R.id.et_email_on_signup);
        // populateAutoComplete();

        mPasswordView_signup = (EditText)fl.findViewById(R.id.et_password_on_signup);

        button_goto_login=(Button)fl.findViewById(R.id.botton_go_to_login_from_signup);
        button_goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_signin1();
            }
        });



        signup_button=(Button)fl.findViewById(R.id.button_signup);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new account by passing the new user's email address and password to createUserWithEmailAndPassword:
                verify_inputs();
                //msignup();

            }
        });

        //get the shared instance of the FirebaseAuth object

        mAuth = FirebaseAuth.getInstance();


        //Set up an AuthStateListener that responds to changes in the user's sign-in state:

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    update_profile();
                    update_email();
                    send_mail();

                    Log.d("nikhil id if signup", "onAuthStateChanged:signed_in:" + user.getUid());
                    FirebaseAuth.getInstance().signOut();

                    // send to WeddingDetailFragment
                    android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    LoginFragment fragment = new LoginFragment();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.addToBackStack("f4");
                    fragmentTransaction.commit();

                    //Intent i3=new Intent(SignupActivity.this,LoginActivity.class);
                    //startActivity(i3);
                    Toast.makeText(getActivity(),"Signup successfuly....",Toast.LENGTH_LONG).show();

                } else {
                    // User is signed out
                    Log.d("nikhil id ekse signup", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

return fl;


    } // end on createView


    private void verify_inputs() {
       /* if (mAuthTask != null)
        {
            return;
        } */

        // Reset errors.
        mEmailView_signup.setError(null);
        mPasswordView_signup.setError(null);

        // Store values at the time of the login attempt.
        sigup_email=mEmailView_signup.getText().toString();
        signup_pass=mPasswordView_signup.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(signup_pass) && !isPasswordValid(signup_pass))
        {
            mPasswordView_signup.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView_signup;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(sigup_email))
        {
            mEmailView_signup.setError(getString(R.string.error_field_required));
            focusView = mEmailView_signup;
            cancel = true;
        } else if (!isEmailValid(sigup_email))
        {
            mEmailView_signup.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView_signup;
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
            msignup();

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




// Go to login page

    public void go_to_signin1()
    {
        android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        LoginFragment fragment = new LoginFragment();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack("f5");
        fragmentTransaction.commit();
    }




    //send verification mail

    public void send_mail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("nikhil_email", "Email sent.");
                        }
                    }
                });

    }

    //Update email

    public void update_email()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(sigup_email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("nikhil_email", "User email address updated.");
                        }
                    }
                });
    }


    // Update profile

    public void update_profile()
    {
        Log.d("nikhil update profile", "done");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Nikhilesh")
                .setPhotoUri(Uri.parse("http://i.imgur.com/i4f9f9I.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("nupdate_profile", "User profile updated.");
                        }
                    }
                });
    }






    public void msignup()
    {


        Log.d("nsignup data",sigup_email+""+signup_pass);

        mAuth.createUserWithEmailAndPassword(sigup_email,signup_pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("nihhil signup1", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.


                        //If the new account was created, the user is also signed in,
                        // and the AuthStateListener runs the onAuthStateChanged callback.
                        // In the callback, you can use the getCurrentUser method to get the user's account data.

                        if (!task.isSuccessful()) {
                           Toast.makeText(getActivity(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
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
     * <p/>
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
