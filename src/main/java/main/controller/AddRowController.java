package main.controller;

import main.view.AddRow;

import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRowController implements ActionListener {

    private AddRow addRowView;

    public AddRowController(AddRow addRowView){

        this.addRowView = addRowView;
        this.addRowView.getAddButton().addActionListener(this);
        this.addRowView.getCancelButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.addRowView.getAddButton()){

        }

        if(e.getSource() == this.addRowView.getCancelButton()){

        }
    }
}
