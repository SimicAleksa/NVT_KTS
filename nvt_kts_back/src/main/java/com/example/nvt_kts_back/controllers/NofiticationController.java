package com.example.nvt_kts_back.controllers;

/*import com.example.nvt_kts_back.DTOs.ReportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")

public class NofiticationController {

    @Autowired
    private SimpMessagingTemplate template;

    // Initialize Notifications
    //private Notifications notifications = new Notifications(0);

    @GetMapping("/notifyWaiter")
    public String getNotificationWaiter() {

        // Increment Notification by one
        //notifications.increment();

        // Push notifications to front-end
        // sad samo umjesto ovoga saljem nesto drugo
        ReportParams r = new ReportParams("pocetak", "kraj", "email");
        template.convertAndSend("/topic/notification/waiter", r);

        return "Notifications successfully sent to Angular !";
    }*/
    /*@GetMapping("/notifyCook")
    public String getNotificationCook() {

        // Increment Notification by one
        notifications.increment();

        // Push notifications to front-end
        template.convertAndSend("/topic/notification/cook", notifications);

        return "Notifications successfully sent to Angular !";
    }
    @GetMapping("/notifyBartender")
    public String getNotificationBartender() {

        // Increment Notification by one
        notifications.increment();

        // Push notifications to front-end
        template.convertAndSend("/topic/notification/bartender", notifications);

        return "Notifications successfully sent to Angular !";
    }

    @GetMapping("/notify")
    public String getNotification() {

        // Increment Notification by one
        notifications.increment();

        // Push notifications to front-end
        template.convertAndSend("/topic/notification", notifications);

        return "Notifications successfully sent to Angular !";
    }
}*/
