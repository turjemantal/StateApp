package com.example.ep.myapplication.Activitys.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ep.myapplication.Activitys.Activitys.MainActivity;
import com.example.ep.myapplication.Activitys.Adapters.StateAdapter;
import com.example.ep.myapplication.Activitys.Adapters.myAdapter;
import com.example.ep.myapplication.Activitys.Model.State;
import com.example.ep.myapplication.Activitys.Services.DataService;
import com.example.ep.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnSecondFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment { // second fragment - borders
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private State s;
    private StateAdapter theAdapter;
    private com.example.ep.myapplication.Activitys.Adapters.myAdapter myAdapter;
    private ListView theListView;
    private RecyclerView RecycleView2;
    private OnSecondFragmentInteractionListener mListener;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        View v = inflater.inflate(R.layout.fragment_second, container, false);

        Button b = (Button) v.findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Go_back_fragment();
            }
        });
        // Inflate the layout for this fragment
        final DataService ds = new DataService();


        Intent i = getActivity().getIntent();

        State co = (State) i.getSerializableExtra("StateOb");
        ArrayList<State> arrR = new ArrayList<>();

        for(String s : co.getBorders()){ // find all the borders for one given state

            arrR.add(ds.getNstateFromBstate(s));
        }

        if(arrR.size() == 0 )
        Toast.makeText(getActivity() , "Soory No Borders" , Toast.LENGTH_LONG).show();

        //theAdapter = new StateAdapter(getActivity(),arrR);

        myAdapter=new myAdapter(getActivity(),arrR);

        //theListView = (ListView) v.findViewById(R.id.ListViewRe);

        RecycleView2=v.findViewById(R.id.recycleView2);


        //theListView.setAdapter(theAdapter);
            RecycleView2.setAdapter(myAdapter);

        RecycleView2.setLayoutManager(new LinearLayoutManager(this.getContext()));



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSecondFragmentInteraction(uri);
        }
    }
    private void Go_back_fragment() {

        MainActivity mainActivity = (MainActivity) getActivity();

        mainActivity.GoBack();

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSecondFragmentInteractionListener) {
            mListener = (OnSecondFragmentInteractionListener) context;
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
    public interface OnSecondFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSecondFragmentInteraction(Uri uri);
    }
}
