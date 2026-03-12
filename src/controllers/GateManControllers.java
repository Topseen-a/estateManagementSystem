package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import services.GateAccessService;

@Controller
public class GateManControllers {

    @Autowired
    GateAccessService gateAccessService;
}
