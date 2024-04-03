package com.example.vaadinproject.view;


import com.example.vaadinproject.model.RequestAcc;
import com.example.vaadinproject.repo.RequestAccRepository;
import com.example.vaadinproject.service.RequestAccService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route("main")
public class RequestAccView extends VerticalLayout {

    private Grid<RequestAcc> grid;
    private Binder<RequestAcc> binder;

    @Autowired
    private RequestAccService requestAccService;

    public RequestAccView() {
        grid = new Grid<>(RequestAcc.class);

        TextField textFieldFirstName = new TextField("Firstname");
        TextField textFieldLastName = new TextField("Lastname");
        TextField numberField = new TextField("INN");
        DatePicker datePicker = new DatePicker("Дата");

        Button buttonRead = new Button("Показать");
        Button buttonCreate = new Button("Создать");

        binder = new Binder<>(RequestAcc.class);

        binder.bind(textFieldFirstName, RequestAcc::getFirstName, RequestAcc::setFirstName);
        binder.bind(textFieldLastName, RequestAcc::getLastName, RequestAcc::setLastName);

        binder.forField(numberField)
                .withConverter(new StringToLongConverter("Введите корректное значение"))
                .bind(RequestAcc::getInn, RequestAcc::setInn);
        binder.bind(datePicker, RequestAcc::getDate, RequestAcc::setDate);


        // Правило проверки для поля FirstName
        binder.forField(textFieldFirstName)
                .asRequired("Пожалуйста, введите имя")
                .withValidator(firstName -> firstName.length() >= 2, "Имя должно содержать как минимум 2 символа")
                .bind(RequestAcc::getFirstName, RequestAcc::setFirstName);

        // Правило проверки для поля LastName
        binder.forField(textFieldLastName)
                .asRequired("Пожалуйста, введите фамилию")
                .withValidator(lastName -> lastName.length() >= 2, "Фамилия должна содержать как минимум 2 символа")
                .bind(RequestAcc::getLastName, RequestAcc::setLastName);

        // Правило проверки для поля INN
        binder.forField(numberField)
                .asRequired("Пожалуйста, введите ИНН")
                .withConverter(new StringToLongConverter("Введите корректное значение ИНН"))
                .withValidator(inn -> inn >= 12, "ИНН должен содержать 10 цифр")
                .bind(RequestAcc::getInn, RequestAcc::setInn);

        // Правило проверки для поля DatePicker
        binder.forField(datePicker)
                .asRequired("Пожалуйста, выберите дату")
                .bind(RequestAcc::getDate, RequestAcc::setDate);


        buttonCreate.addClickListener(event -> {
            RequestAcc requestAcc = new RequestAcc();
            try {

                //Записываем
                binder.writeBean(requestAcc);

                //Создаем
                requestAccService.create(requestAcc);
                Notification.show("Запись создана успешно!");
            } catch (ValidationException e) {
                Notification.show("Ошибка валидации: " + e.getMessage());
            }
        });

        buttonRead.addClickListener(event -> {
            List<RequestAcc> all = requestAccService.findAll();
            grid.setItems(all);
        });

        add(numberField, textFieldFirstName, textFieldLastName, buttonCreate, datePicker, buttonRead, grid);
    }
}