package com.example.vaadinproject.view;

import com.example.vaadinproject.model.RequestAcc;
import com.example.vaadinproject.repo.NotificationRepository;
import com.example.vaadinproject.repo.RequestAccRepository;
import com.example.vaadinproject.service.RequestAccService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSelectionModel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Route("findAll")

public class GetAccView extends VerticalLayout {

    private final Grid<RequestAcc> requestAccGrid;

    @Autowired
    private RequestAccService requestAccService;
    @Autowired
    private RequestAccRepository requestAccRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public GetAccView() {
        TextField textField = new TextField("Введите имя");
        TextField textFieldLastName = new TextField("Введите Фамилию");

        requestAccGrid = new Grid<>(RequestAcc.class);

        Button button = new Button("Найти");
        Button buttonDel = new Button("Удалить");

        button.setEnabled(false);



        add(textField, textFieldLastName, button,buttonDel, requestAccGrid);
    }



}