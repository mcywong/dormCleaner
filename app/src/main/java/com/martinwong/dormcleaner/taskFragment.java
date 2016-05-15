package com.martinwong.dormcleaner;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * Created by Martin Wong on 2016-05-12.
 * taskFragment class for displaying a list of tasks to do
 */
public class taskFragment extends ListFragment implements AdapterView.OnItemClickListener{
    Intent detailSegue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.task_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.tasks, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item" + " " + position, Toast.LENGTH_SHORT).show();
        //make an intent and pass it into startActivity
        detailSegue = new Intent(taskFragment.this.getActivity(), Task_Detail_Activity.class);
        startActivity(detailSegue);
    }
}
