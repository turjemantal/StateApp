package com.example.ep.myapplication.Activitys.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ep.myapplication.Activitys.Activitys.MainActivity;
import com.example.ep.myapplication.Activitys.Adapters.RecyclerItemClickListener;
import com.example.ep.myapplication.Activitys.Adapters.StateAdapter;
import com.example.ep.myapplication.Activitys.Adapters.myAdapter;
import com.example.ep.myapplication.Activitys.Model.State;
import com.example.ep.myapplication.Activitys.Services.DataService;
import com.example.ep.myapplication.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mainFirstFragment.OnFirstFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mainFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mainFirstFragment extends Fragment {  // first fragment - listview with all states
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private StateAdapter theAdapter;
    private myAdapter myAdapter1;
    private ListView theListView;
    private ArrayList<State> allstates;
    RecyclerView recycleView;
    private String names[]={"yahel","yahel","tal","yahel","yahel"};
    private int image[]={R.drawable.kermit,R.drawable.gonzo,R.drawable.fozzie,R.drawable.scooter,R.drawable.pigy};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFirstFragmentInteractionListener mListener;

    public mainFirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mainFirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static mainFirstFragment newInstance(String param1, String param2) {
        mainFirstFragment fragment = new mainFirstFragment();
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

        final DataService ds = new DataService();
        final View v = inflater.inflate(R.layout.fragment_main_first, container, false);

        try {
            allstates = ds.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      // allstates =  ds.getMyStates();


        // theAdapter = new StateAdapter(getActivity(),allstates);



        //theListView = (ListView) v.findViewById(R.id.ListViewsir);
        recycleView=v.findViewById(R.id.recycleView100);

        myAdapter1=new myAdapter(getContext(),allstates);
        recycleView.setAdapter(myAdapter1);
        recycleView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // theListView.setOnTouchListener(sd);

        //theListView.setTextFilterEnabled(true);
        EditText inputSearch = (EditText) v.findViewById(R.id.inputSearch);

        // Adding items to listview

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

                    myAdapter1 = new myAdapter(getActivity(), myAdapter1.custumeFilter(allstates, cs.toString()));
                    recycleView.setAdapter(myAdapter1);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

//        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
//                view.startAnimation(hyperspaceJumpAnimation);
//
//                State s = (State) parent.getAdapter().getItem(position);
//                MainActivity ma =(MainActivity) getActivity();
//                ma.LoadSecFragment(s);
//
//            }
//        });
        recycleView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recycleView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.i("Hey","this"+position);
                        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                        view.startAnimation(hyperspaceJumpAnimation);
                        State s =myAdapter1.getAllstatesIn().get(position);
                        MainActivity ma =(MainActivity) getActivity();
                        ma.LoadSecFragment(s);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFirstFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFirstFragmentInteractionListener) {
            mListener = (OnFirstFragmentInteractionListener) context;
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
    public interface OnFirstFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFirstFragmentInteraction(Uri uri);
    }
}
