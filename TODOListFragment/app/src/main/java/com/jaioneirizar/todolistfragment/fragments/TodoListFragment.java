package com.jaioneirizar.todolistfragment.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.jaioneirizar.todolistfragment.R;

import com.jaioneirizar.todolistfragment.adapters.ToDoAdapter;
import com.jaioneirizar.todolistfragment.fragments.dummy.DummyContent;
import com.jaioneirizar.todolistfragment.model.ToDo;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link //OnFragmentInteractionListener}
 * interface.
 */
public class TodoListFragment extends ListFragment implements InputFragment.TODOItemListener {


    private ArrayList <ToDo> todos;
    private ArrayAdapter <ToDo> aa;
    private final String DATA= "datos";
    private ArrayList data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layaout= super.onCreateView(inflater, container, savedInstanceState);

        todos= new ArrayList<ToDo>();
        aa = new ToDoAdapter(getActivity(),R.layout.todo_list_item, todos );

        if(savedInstanceState!= null){

            ArrayList <ToDo> tmp= savedInstanceState.getParcelableArrayList(DATA);

            todos.addAll(tmp);
        }

        setListAdapter(aa);

        return layaout;
    }



    @Override
    public void addTodo(ToDo todo) {

            todos.add(0, todo);
             aa.notifyDataSetChanged();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putParcelableArrayList(DATA, todos);
        super.onSaveInstanceState(outState);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        todos.remove(position);
        aa.notifyDataSetChanged();
    }
}



