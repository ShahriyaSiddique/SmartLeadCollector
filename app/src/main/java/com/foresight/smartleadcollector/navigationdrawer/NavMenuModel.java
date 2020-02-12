package com.foresight.smartleadcollector.navigationdrawer;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miki on 7/8/17.
 */

public class NavMenuModel {
    public String menuTitle;
    public List<SubMenuModel> subMenu;
    public Fragment fragment;

    public NavMenuModel(String menuTitle, Fragment fragment) {
        this.menuTitle = menuTitle;
        this.fragment = fragment;
        this.subMenu = new ArrayList<>();
    }

/*    public NavMenuModel(String menuTitle, int menuIconDrawable, Fragment fragment) {
        this.menuTitle = menuTitle;
        this.menuIconDrawable = menuIconDrawable;
        this.fragment = fragment;
        this.subMenu = new ArrayList<>();
    }*/

   /* public NavMenuModel(String menuTitle, int menuIconDrawable, ArrayList<SubMenuModel> subMenu) {
        this.menuTitle = menuTitle;
        this.menuIconDrawable = menuIconDrawable;
        this.subMenu = new ArrayList<>();
        this.subMenu.addAll(subMenu);
    }*/

    public NavMenuModel(String menuTitle, ArrayList<SubMenuModel> subMenu) {
        this.menuTitle = menuTitle;
        this.subMenu = new ArrayList<>();
        this.subMenu.addAll(subMenu);
    }

    public static class SubMenuModel {
        public String subMenuTitle;
        public Fragment fragment;

        public SubMenuModel(String subMenuTitle, Fragment fragment) {
            this.subMenuTitle = subMenuTitle;
            this.fragment = fragment;
        }
    }
}
