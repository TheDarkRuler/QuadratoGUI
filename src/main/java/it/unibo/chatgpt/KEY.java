package it.unibo.chatgpt;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KEY implements EventHandler<KeyEvent>{

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            System.out.println("ciao");
        } else {
            System.out.println("ciao");
        }
    }
}
