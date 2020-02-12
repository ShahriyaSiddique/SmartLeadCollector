package com.foresight.smartleadcollector.navigationdrawer;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by miki on 7/7/17.
 */

public class TitleMenu extends ExpandableGroup<SubTitle> {

    public TitleMenu(String title, List<SubTitle> items) {
        super(title, items);
    }

}
