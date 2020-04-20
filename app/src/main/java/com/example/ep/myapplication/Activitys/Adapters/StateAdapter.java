package com.example.ep.myapplication.Activitys.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ep.myapplication.Activitys.Activitys.MainActivity;
import com.example.ep.myapplication.Activitys.Model.State;

import com.example.ep.myapplication.R;


import java.util.ArrayList;

/**
 * Created by EP on 19/07/2017.
 */

public class StateAdapter  extends ArrayAdapter<State>  {


    public StateAdapter(Context context, ArrayList<State> values) {
        super(context, R.layout.rowlayout,values);

    }
    @Override
    public View getView (int position , View convertView , ViewGroup parent) // setting up the objects from the array on the listview
    {
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.rowlayout, parent, false);

        State state = getItem(position);
        TextView textViewName = (TextView) theView.findViewById(R.id.textView1);
        TextView textViewnativeName = (TextView) theView.findViewById(R.id.textView2);

        textViewName.setText(state.getName());
        textViewnativeName.setText(state.getNativeName());

    //    Toast.makeText(getContext(), state.getFlag(), Toast.LENGTH_LONG).show();


        return theView;

    }
    public ArrayList<State> custumeFilter(ArrayList<State> input, String word) // for search edit text - filter function
    {
        ArrayList<State> arr = new ArrayList<State>();

        for(State s : input)
        {
            if(s.getName().toLowerCase().contains(word) || s.getNativeName().toLowerCase().contains(word))
            {
                arr.add(s);
            }
        }
        return arr;
    }

}
