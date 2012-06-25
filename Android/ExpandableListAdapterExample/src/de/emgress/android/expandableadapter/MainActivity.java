package de.emgress.android.expandableadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
    private static final String LOG_TAG = "ELA_DEMO";

    private List<GroupClass> groups;


    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        this.getActionBar().setTitle( "Exp. List Example" );

        /*
         * Init Demo Data
         */
        this.initDemoData();

        /*
         * Create Adapter and configure ListView
         */
        MyExpandableListAdapter adapter = new MyExpandableListAdapter();

        ExpandableListView elv = (ExpandableListView)findViewById( R.id.exp_list_view );
        elv.setAdapter( adapter );
    }


    public class MyExpandableListAdapter extends BaseExpandableListAdapter
    {
        LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        @Override
        public Object getChild( int groupPosition, int childPosition )
        {
            return groups.get( groupPosition ).getChild(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        @Override
        public int getChildrenCount( int groupPosition )
        {
            return groups.get( groupPosition ).getChildren().size();
        }

        @Override
        public View getChildView( int groupPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent )
        {
            ViewGroup item;

            if( convertView != null && convertView.getId() == R.id.child_item )
            {
                // We can reuse the view
                item = (ViewGroup)convertView;
            }
            else
            {
                item = (ViewGroup)inflater.inflate( R.layout.child_item_layout, parent, false );
            }

            ChildClass child = (ChildClass)getChild( groupPosition, childPosition );

            TextView nameView = (TextView)item.findViewById( R.id.child_name );
            nameView.setText( child.getName() );

            TextView sizeView = (TextView)item.findViewById( R.id.child_text );
            sizeView.setText( child.getText() );

            return item;

        }

        @Override
        public Object getGroup( int groupPosition )
        {
            return groups.get( groupPosition );
        }

        @Override
        public int getGroupCount()
        {
            return groups.size();
        }

        @Override
        public long getGroupId( int groupPosition )
        {
            return groupPosition;
        }

        @Override
        public View getGroupView( int groupPosition, boolean isExpanded, View convertView,
                                 ViewGroup parent )
        {
            ViewGroup item;

            if( convertView != null && convertView.getId() == R.id.group_item )
            {
                // We can reuse the view
                item = (ViewGroup)convertView;
            }
            else
            {
                item = (ViewGroup)inflater.inflate( R.layout.group_item_layout, parent, false );
            }

            GroupClass group = (GroupClass)getGroup(groupPosition);

            TextView nameView = (TextView)item.findViewById( R.id.group_name );
            nameView.setText( group.getName() );

            TextView sizeView = (TextView)item.findViewById( R.id.group_size );
            sizeView.setText( "" + group.getChildren().size() );

            return item;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            return true;
        }

        @Override
        public boolean hasStableIds()
        {
            return true;
        }

    }

    /**
     * Helper Method to create Demo Groups and Childen
     */
    private void initDemoData()
    {
        this.groups = new ArrayList<GroupClass>();

        for( int groupCounter = 1; groupCounter <= 20; groupCounter++ )
        {
            GroupClass group = new GroupClass( "Gruppe " + groupCounter );

            // Just a little trick to get a different number of children per group
            int numberOfChildren = (23 % groupCounter) + 2 ;

            for( int childCounter = 0; childCounter < numberOfChildren; childCounter++ )
            {
                ChildClass child = new ChildClass( "Kind " + childCounter , "Text für Kind " + childCounter +
                        " Text für Kind " + childCounter + " Text für Kind " + childCounter +
                        " Text für Kind " + childCounter + " Text für Kind " + childCounter);

                group.addChild( child );
            }

            this.groups.add( group );
        }
    }


}
