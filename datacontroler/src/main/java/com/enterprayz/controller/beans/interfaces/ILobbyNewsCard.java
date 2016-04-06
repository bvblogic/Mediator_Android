package com.enterprayz.controller.beans.interfaces;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by con on 09.04.16.
 */
public interface ILobbyNewsCard extends ICard, Serializable {
    Date getEventDate();

    String getEventTitle();

    String getDescription();

    String getHtmlContent();
}
