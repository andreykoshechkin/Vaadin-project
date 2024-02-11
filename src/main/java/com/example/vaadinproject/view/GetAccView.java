package com.example.vaadinproject.view;

import com.example.vaadinproject.model.RequestAcc;
import com.example.vaadinproject.repo.RequestAccRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("getAcc")
public class GetAccView extends HorizontalLayout {


    private Grid<RequestAcc> requestAccGrid;

    @Autowired
    private RequestAccRepository requestAccRepository;

    public GetAccView() {
        TextField textField = new TextField("Введите имя");
        TextField textFieldLastName = new TextField("Введите Фамилию");

        requestAccGrid = new Grid<>(RequestAcc.class);

        Button button = new Button("Найти");
        button.addClickListener(event -> {
            requestAccGrid.setItems(requestAccRepository.findRequestAccByFilter(textField.getValue(), textFieldLastName.getValue()));
        });
        add(textField, textFieldLastName, button, requestAccGrid);
    }
}
