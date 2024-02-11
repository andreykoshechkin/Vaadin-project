package com.example.vaadinproject.view;


import com.example.vaadinproject.model.RequestAcc;
import com.example.vaadinproject.repo.RequestAccRepository;
import com.example.vaadinproject.service.RequestAccService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route("main")

public class RequestAccView extends VerticalLayout {

    private Grid<RequestAcc> grid;
    private Button button;

    @Autowired
    private RequestAccService requestAccService;

    public RequestAccView() {

        grid = new Grid<>(RequestAcc.class);

        TextField textFieldFirstName = new TextField("Firstname");
        TextField textFieldLastName = new TextField("Lastname");
        NumberField numberField = new NumberField("INN");

        button = new Button("Показать");

        button.addClickListener(event -> {
            List<RequestAcc> all = requestAccService.findAll();
            grid.setItems(all);
        });


        Binder<RequestAcc> binder = new Binder<>(RequestAcc.class);

        binder.bind(textFieldFirstName, RequestAcc::getFirstName, RequestAcc::setFirstName);


        add(numberField, textFieldFirstName, textFieldLastName, button, grid);
    }


}
