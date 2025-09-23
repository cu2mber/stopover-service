package com.cu2mber.stopoverservice.controller;

import com.cu2mber.stopoverservice.dto.StopoverRequest;
import com.cu2mber.stopoverservice.dto.StopoverResponse;
import com.cu2mber.stopoverservice.dto.StopoverUpdateOrderRequest;
import com.cu2mber.stopoverservice.dto.StopoverUpdateRequest;
import com.cu2mber.stopoverservice.service.StopoverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stopovers")
public class StopoverController {

    private final StopoverService stopoverService;

    @PostMapping
    public ResponseEntity<StopoverResponse> saveStopover(@Valid @RequestBody StopoverRequest request) {

        StopoverResponse response = stopoverService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{no}")
    public ResponseEntity<StopoverResponse> getStopover(@PathVariable("no") Long no) {
        StopoverResponse response = stopoverService.getStopover(no);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/locals/{localNo}")
    public ResponseEntity<List<StopoverResponse>> getStopovers(@PathVariable("localNo") int localNo) {
        List<StopoverResponse> responseList = stopoverService.getStopoverList(localNo);

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{no}")
    public ResponseEntity<StopoverResponse> updateStopover(@PathVariable("no") Long no, @Valid @RequestBody StopoverUpdateRequest request) {
        StopoverResponse response =  stopoverService.update(no, request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{no}/order")
    public ResponseEntity<StopoverResponse> updateOrder(@PathVariable("no") Long no, @Valid @RequestBody StopoverUpdateOrderRequest request) {
        StopoverResponse response = stopoverService.updateOrder(no, request);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deleteStopover(@PathVariable("no") Long no){
        stopoverService.delete(no);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/locals/{localNo}")
    public ResponseEntity<Void> deleteStopovers(@PathVariable("localNo") int localNo) {
        stopoverService.deleteAll(localNo);

        return ResponseEntity.ok().build();
    }
}
