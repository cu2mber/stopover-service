package com.oeso.stopoverservice.repository;

public interface CustomStopoverRepository {

    boolean existsByLocalAndStopover(int localNo, String stopoverName);
}
