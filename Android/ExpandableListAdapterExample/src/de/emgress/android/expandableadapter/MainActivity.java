package de.emgress.android.expandableadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
    private static final String LOG_TAG = "ELA_DEMO";

    private List<GroupClass> groups;
    private ExpandableListView elv;
    private MyExpandableListAdapter adapter;


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
        this.adapter = new MyExpandableListAdapter();

        this.elv = (ExpandableListView)findViewById( R.id.exp_list_view );
        this.elv.setAdapter( this.adapter );
    }

    /*
     * Option Menu related classes.
     * We provide two menu items - one to add a new group with one child element
     * and a second one which deletes the first group in the list.
     * In both cases, we notify our adapter that the dataset changed so that the
     * ListView will be updated to reflect those changes.
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mi = new MenuInflater( this );
        mi.inflate( R.menu.option_menu, menu );

        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        if( item.getItemId() == R.id.add_item )
        {
            GroupClass newGroup = new GroupClass( "New Group " + System.currentTimeMillis() );
            newGroup.addChild( new ChildClass( "New Child", "Child Text: " + System.currentTimeMillis() ) );
            this.groups.add( 0, newGroup );

            this.adapter.notifyDataSetChanged();

            return true;
        }
        else if ( item.getItemId() == R.id.remove_item )
        {
            this.groups.remove( 0 );

            this.adapter.notifyDataSetChanged();

            return true;
        }
        else
        {
            return super.onMenuItemSelected(featureId, item);
        }
    }

    /**
     * The adapter uses the group List from the surrounding class which is sufficient
     * for the example. However, to achieve a higher encapsulation, one would probably
     * hide the data structure in a separate object and not expose it to the Activity.
     */
    public class MyExpandableListAdapter extends BaseExpandableListAdapter
    {

        // Local Reference to the LayoutInflater because we will need it
        // several times
        LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);


        /*
         * Group related Methods
         */

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
                // We can reuse the View
                item = (ViewGroup)convertView;
            }
            else
            {
                // We create a new View
                item = (ViewGroup)inflater.inflate( R.layout.group_item_layout, parent, false );
            }

            GroupClass group = (GroupClass)getGroup(groupPosition);

            TextView nameView = (TextView)item.findViewById( R.id.group_name );
            nameView.setText( group.getName() );

            TextView sizeView = (TextView)item.findViewById( R.id.group_size );
            sizeView.setText( "" + group.getChildren().size() );

            return item;
        }

        /*
         * Child related Methods
         */

        @Override
        public Object getChild( int groupPosition, int childPosition )
        {
            return groups.get( groupPosition ).getChild( childPosition );
        }

        @Override
        public long getChildId( int groupPosition, int childPosition )
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
                // We create a new View
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
     * Helper Method to create Demo Groups and Children
     */
    private void initDemoData()
    {
        this.groups = new ArrayList<GroupClass>();

        for( int groupCounter = 1; groupCounter <= 25; groupCounter++ )
        {
            GroupClass group = new GroupClass( "Group " + groupCounter );

            // Just a little trick to get a different number of children per group
            int numberOfChildren = (23 % groupCounter) + 2 ;

            for( int childCounter = 0; childCounter < numberOfChildren; childCounter++ )
            {
                ChildClass child = new ChildClass( "Child " + childCounter , "Text for Child " + childCounter +
                        " Text for Child " + childCounter + " Text for Child " + childCounter +
                        " Text for Child " + childCounter + " Text for Child " + childCounter);

                group.addChild( child );
            }

            this.groups.add( group );
        }
    }


}
