package main.controller;

import main.view.AddRow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRowController implements ActionListener{

    private AddRow addRowView;
    private int count = 0;

    public AddRowController(AddRow addRowView){

        this.setAddRowView(addRowView);
        //this.getAddRowView().getAddButton().addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.getAddRowView().getAddButton()){
            if(!this.addRowView.getAnoTextField().getText().equals("") && !this.addRowView.getMesTextField().getText().equals("")){
                this.setCount(this.getCount() + 1);
                System.out.println(this.getCount());
            }
        }

    }

    public AddRow getAddRowView() {
        return addRowView;
    }

    public void setAddRowView(AddRow addRowView) {
        this.addRowView = addRowView;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
