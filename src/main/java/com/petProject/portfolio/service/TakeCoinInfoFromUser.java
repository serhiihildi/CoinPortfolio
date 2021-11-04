package com.petProject.portfolio.service;

import com.petProject.portfolio.model.User;

import java.math.BigDecimal;

public class TakeCoinInfoFromUser {

    private User user;

    // TODO Realise method
     void addNewTransaction(String coinName, BigDecimal coinVolume) {
         /* Проверить монету на правильность написания*/

         /* Проверить, создан ли портфель у пользователя
          * Нет:
          *     - создаем новый екземпляр и портфель
          *     - присваиваем его юзеру
          * Да:
          *     Проверяем, есть ли переданная монета у  пользователя в мапе:
          *         Нет:
          *             - создаем новый HasMap и добавляем туда первую транзакцию
          *         Да:
          *             - подтягиваем значение этой монеты и обновляем ее
          */
    }
}
