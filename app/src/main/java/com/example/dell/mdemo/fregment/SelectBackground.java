package com.example.dell.mdemo.fregment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.dell.mdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectBackground.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectBackground#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectBackground extends Fragment {

    ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,iv10,iv11,iv12;
    String myour_name, mpartner_name,mdate_time,mwedding_msg,mlocation;
    int image_no;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectBackground() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectBackground.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectBackground newInstance(String param1, String param2) {
        SelectBackground fragment = new SelectBackground();
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

            Bundle bundle = getArguments();
//        Log.d("rbundle",bundle.toString());
            if (bundle != null) {
                // nyourname = bundle.getString("your name");
                myour_name = bundle.getString("your name");
                mpartner_name = bundle.getString("partner name");
                mdate_time = bundle.getString("dt");
                mwedding_msg = bundle.getString("msg");
                mlocation = bundle.getString("location");


                // Log.d("name",nyourname);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_select_background, container, false);

        FrameLayout fl = (FrameLayout) inflater.inflate(R.layout.fragment_select_background, container, false);

        iv1=(ImageView)fl.findViewById(R.id.iv_back1);
        iv2=(ImageView)fl.findViewById(R.id.iv_back2);
        iv3=(ImageView)fl.findViewById(R.id.iv_back3);
        iv4=(ImageView)fl.findViewById(R.id.iv_back4);
        iv5=(ImageView)fl.findViewById(R.id.iv_back5);
        iv6=(ImageView)fl.findViewById(R.id.iv_back6);
        iv7=(ImageView)fl.findViewById(R.id.iv_back7);
        iv8=(ImageView)fl.findViewById(R.id.iv_back8);
        iv9=(ImageView)fl.findViewById(R.id.iv_back9);
        iv10=(ImageView)fl.findViewById(R.id.iv_back10);
        iv11=(ImageView)fl.findViewById(R.id.iv_back11);
        iv12=(ImageView)fl.findViewById(R.id.iv_back12);







        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=1;
                fill_data();


            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=2;
                fill_data();


            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=3;
                fill_data();


            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=4;
                fill_data();


            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=5;
                fill_data();


            }
        });

        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=6;
                fill_data();


            }
        });

        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=7;
                fill_data();


            }
        });

        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=8;
                fill_data();


            }
        });

        iv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=9;
                fill_data();


            }
        });

        iv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=10;
                fill_data();


            }
        });
        iv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=11;
                fill_data();


            }
        });

        iv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_no=12;
                fill_data();


            }
        });

        return fl;

    }//end oncreate view





   public void fill_data()
   {

       SelectDesignFragment fragment = new SelectDesignFragment();
       Bundle bundle = new Bundle();
       bundle.putString("your name",myour_name);
       bundle.putString("partner name",mpartner_name);
       bundle.putString("dt",mdate_time);
       bundle.putString("msg",mwedding_msg);
       bundle.putString("location",mlocation);
       bundle.putInt("image no",image_no);


       fragment.setArguments(bundle);

       android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
       FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

       fragmentTransaction.replace(R.id.fragment_container, fragment);
       fragmentTransaction.addToBackStack("f2");
       fragmentTransaction.commit();
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
