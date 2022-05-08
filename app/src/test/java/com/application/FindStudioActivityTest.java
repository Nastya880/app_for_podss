package com.application;

import static com.application.FindStudioActivity.studios;
import static org.junit.Assert.*;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FindStudioActivityTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        ArrayAdapter<String> adapter;
        ArrayList<String> listItems = new ArrayList<String>();

        SeekBar costSeekBar;
        TextView costTextView;
        ListView list;

        boolean flag = true;
        StudioPojo studio1 = new StudioPojo(1,"", "", "", "", 12,55.0, 55.0);

        ArrayList<StudioPojo> studios = new ArrayList<>();
studios.add(2,studio1);
        String filter = "";
    }

    @Test
    public void refreshList() {
        assertEquals(studios, "");
    }
}