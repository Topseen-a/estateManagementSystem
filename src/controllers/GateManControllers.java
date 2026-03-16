package controllers;

import data.models.GatePass;
import data.models.Visitor;
import dtos.requests.GenerateResidentEntryCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.GateAccessService;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping
public class GateManControllers {

    private final GateAccessService gateAccessService;

    @Autowired
    public GateManControllers(GateAccessService gateAccessService) {
        this.gateAccessService = gateAccessService;
    }

    @PostMapping
    public GatePass generateGatePass(@RequestBody GenerateResidentEntryCodeRequest residentEntryCodeRequest, @RequestBody Visitor visitor) {
        LocalTime validTill = residentEntryCodeRequest.getValidTill();
        return gateAccessService.generateGatePass(residentEntryCodeRequest, visitor);
    }

    @GetMapping
    public boolean validateGatePass(@PathVariable String code) {
        return gateAccessService.validateGatePass(code);
    }

    @GetMapping
    public List<GatePass> getResidentPasses(@PathVariable String residentId) {
        return gateAccessService.getPassesByResident(residentId);
    }
}
