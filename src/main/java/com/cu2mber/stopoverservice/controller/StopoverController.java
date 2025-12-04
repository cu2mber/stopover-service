package com.cu2mber.stopoverservice.controller;

import com.cu2mber.stopoverservice.dto.command.StopoverCreateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateCommand;
import com.cu2mber.stopoverservice.dto.command.StopoverUpdateOrderCommand;
import com.cu2mber.stopoverservice.dto.request.StopoverCreateRequest;
import com.cu2mber.stopoverservice.dto.response.StopoverResponse;
import com.cu2mber.stopoverservice.dto.request.StopoverUpdateOrderRequest;
import com.cu2mber.stopoverservice.dto.request.StopoverUpdateRequest;
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
    public ResponseEntity<StopoverResponse> createStopover(@Valid @RequestBody StopoverCreateRequest request) {

        // 생성자 ID로 지자체 멤버 서비스 조회
        StopoverCreateCommand command = new StopoverCreateCommand(
                1L, // todo: 지자체 멤버 ID
                request.stopoverName()
        );
        StopoverResponse response = stopoverService.create(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{no}")
    public ResponseEntity<StopoverResponse> getStopover(@PathVariable("no") Long no) {
        StopoverResponse response = stopoverService.getStopover(no);

        return ResponseEntity.ok(response);
    }

    // 지자체용
    @GetMapping
    public ResponseEntity<List<StopoverResponse>> getStopoversForLocal() {
        // todo: 지자체 멤버 ID
        List<StopoverResponse> responseList = stopoverService.getStopoverList(1L);

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{no}")
    public ResponseEntity<StopoverResponse> updateStopover(@PathVariable("no") Long no, @Valid @RequestBody StopoverUpdateRequest request) {

        StopoverUpdateCommand command = new StopoverUpdateCommand(
                no,
                1L, // todo: 지자체 멤버 ID
                request.stopoverName()
        );

        StopoverResponse response =  stopoverService.update(command);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/order")
    public ResponseEntity<StopoverResponse> updateOrder(@Valid @RequestBody List<StopoverUpdateOrderRequest> request) {

        StopoverUpdateOrderCommand command = new StopoverUpdateOrderCommand(
                1L,
                request.stream()
                        .map(r -> new StopoverUpdateOrderCommand.UpdateOrderInfo(
                                r.stopoverNo(),
                                r.stopoverSequence()
                        )).toList()
        );
        stopoverService.updateOrder(command);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deleteStopover(@PathVariable("no") Long no){
        stopoverService.delete(no);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/locals/{localNo}")
    public ResponseEntity<Void> deleteStopovers(@PathVariable("localNo") Long memberLocalNo) {
        stopoverService.deleteAll(memberLocalNo);

        return ResponseEntity.ok().build();
    }
}
