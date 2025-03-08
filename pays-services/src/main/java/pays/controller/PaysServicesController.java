package pays.controller;

import pays.model.*;
import pays.service.PaysService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PaysServicesController implements PaysServicesApi {

  private final PaysService paysService;

  @Override
  public ResponseEntity<PayResponse> getPay(
    @Parameter(name = "PayId", description = "Pay Id", required = true, in = ParameterIn.PATH) @PathVariable("PayId") Integer payId) {
    log.info("getPays method called");
    log.info("PayId: {}", payId);
    
    return new ResponseEntity<>(paysService.getPay(payId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<PayResponse> savePay(PayResponse pay) {
    log.info("savePay method called");
    log.info("pay: {}", pay.toString());
    
    return new ResponseEntity<>(paysService.savePay(pay), HttpStatus.OK);
  }
}
