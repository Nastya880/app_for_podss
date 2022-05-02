package com.application;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class LoginActivity extends ParentNavigationActivity {

    //Переменная для редактирования поля ввода номера телефона
    public static EditText phoneEditText;

    /**
     * создание активити - экран авторизации
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Отображение меню-гамбургера
        onCreateOption(savedInstanceState);
        phoneEditText = findViewById(R.id.editTextPhone);

        //Отслеживание изменений при вводе номера телефона - создание маски +7() ХХХ-ХХ-ХХ
        phoneEditText.addTextChangedListener(new TextWatcher() {
            //Флаг: пользователь стирает или вводит новый символ
            private boolean backspacingFlag = false;
            //Блокировка повторноко вызов afterTextChanges() после того, как  заменен текст EditText
            private boolean editedFlag = false;
            //Положение курсора для восстановления после изменения
            private int cursorComplement;

            /**
             * методы интерфейса TextWatcher()
             * @param s
             * @param start
             * @param count
             * @param after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Сохранение курсора относительно конца строки в EditText перед редактированием
                cursorComplement = s.length() - phoneEditText.getSelectionStart();
                //Проверка: пользователь вводит или стирает символ
                if (count > after) {
                    backspacingFlag = true;
                } else {
                    backspacingFlag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                //Всегда работа с необработанной строкой, содержащей только цифры,
                //Т.К. используется маска
                String phone = string.replaceAll("[^\\d]", "");

                /* Если текст был только что отредактирован, то :afterTextChanged не вызывается
                   Нужно проверить флаг редактирования
                   Если флаг имеет значение false, это исходная запись,
                   введенная пользователем, поэтому применяется маска */
                if (!editedFlag) {
                    /* Проверка полного введения, необходимо добавить много символов, маскирующих
                       например введено: 999999999
                       отображение: (999) 999-999 */
                    if (phone.length() >= 9 && !backspacingFlag) {
                        // Редактирование, значит в следующем вызове этой обработки - игнорирование
                        editedFlag = true;
                        // Сочетание необработанных цифр и с маской по мере необходимости
                        String ans = "+7(" + phone.substring(1, 4) + ") " + phone.substring(4, 7) + "-" + phone.substring(7, 9) + "-" + phone.substring(9);
                        phoneEditText.setText(ans);
                        // Возвращение курсора в исходное положение относительно конца строки
                        phoneEditText.setSelection(phoneEditText.getText().length() - cursorComplement);

                        /* Конец на самом простом случае, когда требуется только одна маска
                           например введено: 99999
                           маска: (999) 99 */
                    } else if (phone.length() >= 4 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = "+7(" + phone.substring(1, 4) + ") " + phone.substring(4);
                        phoneEditText.setText(ans);
                        phoneEditText.setSelection(phoneEditText.getText().length() - cursorComplement);
                    }
                    //Поле отредактирвоано
                } else {
                    editedFlag = false;
                }
            }
        });
    }

    /**
     * создание диалогового окна
     * @param message сообщение в окне
     * @param title заголовок окна
     */
    public void showAlertDialog(String message, String title)
    {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle(title);
        adb.setMessage(message);
        adb.setNeutralButton(R.string.ok, null);
        adb.create();
        adb.show();
    }

    /**
     * обработка нажатия кнопки "Войти"
     * переход на активити с добавлением информации о мероприятии
     * @param view
     */
    public void click(View view) {
        if (phoneEditText.length() != 17)
            showAlertDialog("Введите корректный номер телефона", "ОШИБКА");
        else {
            //установка флага авторизованного пользователя
            MainActivity.authFlag = true;
            Intent intent = new Intent(this, AddEventActivity.class);
            startActivity(intent);
        }
    }
}