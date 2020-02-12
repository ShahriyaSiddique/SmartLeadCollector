package com.foresight.smartleadcollector.data;

import com.foresight.smartleadcollector.fragment.DashboardFragment;
import com.foresight.smartleadcollector.fragment.EventAddFragment;
import com.foresight.smartleadcollector.fragment.EventEditFragment;
import com.foresight.smartleadcollector.fragment.LeadFragment;
import com.foresight.smartleadcollector.fragment.ManageTeamListFragment;
import com.foresight.smartleadcollector.fragment.ManageTeamStudyFragment;
import com.foresight.smartleadcollector.fragment.MangeTeamMakeTeamFragmentFragment;
import com.foresight.smartleadcollector.fragment.MemberListAddMemberFragment;
import com.foresight.smartleadcollector.fragment.MemberListChangeRoleFragment;
import com.foresight.smartleadcollector.fragment.MemberListFullListFragment;
import com.foresight.smartleadcollector.fragment.StatusFragment;
import com.foresight.smartleadcollector.navigationdrawer.NavMenuModel;

import java.util.ArrayList;


public class Constant {

    public static ArrayList<NavMenuModel> getMenuNavigasi(){
        ArrayList<NavMenuModel> menu = new ArrayList<>();

        menu.add(new NavMenuModel("Dasboard", new DashboardFragment() ));


        menu.add(new NavMenuModel("Event",
                new ArrayList<NavMenuModel.SubMenuModel>() {{
                    add(new NavMenuModel.SubMenuModel("Add", new EventAddFragment()));
                    add(new NavMenuModel.SubMenuModel("Edit", new EventEditFragment()));
        }}));

        menu.add(new NavMenuModel("Lead", new LeadFragment()));
        menu.add(new NavMenuModel("Status", new StatusFragment()));
        menu.add(new NavMenuModel("Manage Team",
                new ArrayList<NavMenuModel.SubMenuModel>() {{
                    add(new NavMenuModel.SubMenuModel("List", new ManageTeamListFragment()));
                    add(new NavMenuModel.SubMenuModel("Study", new ManageTeamStudyFragment()));
                    add(new NavMenuModel.SubMenuModel("Make Team", new MangeTeamMakeTeamFragmentFragment()));
                }}));
        menu.add(new NavMenuModel("Member List",
                new ArrayList<NavMenuModel.SubMenuModel>() {{
                    add(new NavMenuModel.SubMenuModel("Add Member", new MemberListAddMemberFragment()));
                    add(new NavMenuModel.SubMenuModel("Change Role", new MemberListChangeRoleFragment()));
                    add(new NavMenuModel.SubMenuModel("Full list", new MemberListFullListFragment()));
                }}));

        menu.add(new NavMenuModel("LogOut",new DashboardFragment()));


        return menu;
    }
}
