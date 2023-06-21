package com.example.firenew;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AdapterTest {

    @Test
    public void getItemCount_zeroItemTest() {
        Adapter adapter = new Adapter(new ArrayList<>());
        assertEquals(0,adapter.getItemCount());
    }

    @Test
    public void getItemCount_oneItemTest() {
        List<Note> list = new ArrayList<>();
        list.add(new Note("nama","isi"));
        Adapter adapter = new Adapter(list);
        assertEquals(1,adapter.getItemCount());
    }
}