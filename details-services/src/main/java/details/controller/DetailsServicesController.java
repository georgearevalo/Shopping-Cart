package details.controller;

import details.model.*;
import details.service.DetailsService;
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
public class DetailsServicesController implements DetailsServicesApi {

  private final DetailsService detailsService;

  @Override
  public ResponseEntity<DetailResponse> getDetail(
    @Parameter(name = "DetailId", description = "Detail Id", required = true, in = ParameterIn.PATH) @PathVariable("DetailId") Integer detailId) {
    log.info("getDetails method called");
    log.info("DetailId: {}", detailId);
    
    return new ResponseEntity<>(detailsService.getDetail(detailId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<DetailResponse> saveDetail(DetailResponse detail) {
    log.info("saveDetail method called");
    log.info("detail: {}", detail.toString());
    
    return new ResponseEntity<>(detailsService.saveDetail(detail), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> updateDetail(DetailResponse detail) {
    log.info("updateDetail method called");
    log.info("Detail: {}", detail.toString());
    
    detailsService.updateDetail(detail);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @Override
  public ResponseEntity<Void> deleteDetail(
    @Parameter(name = "DetailId", description = "Detail Id", required = true, in = ParameterIn.PATH) @PathVariable("DetailId") Integer detailId) {
    log.info("deleteDetail method called");
    log.info("DetailId: {}", detailId);
    
    detailsService.deleteDetail(detailId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
